import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Drawer from "@material-ui/core/Drawer";
import CssBaseline from "@material-ui/core/CssBaseline";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Divider from "@material-ui/core/Divider";
import {
    Button,
    List,
    ListItem,
    Table,
    TableContainer,
    TextField,
    Popover,
    FormControl,
    InputLabel,
    Select,
    MenuItem,
} from "@material-ui/core";
import MessageBox from "../../components/message/index";
import Panels from "../../components/panels/index";
import axios from "axios";
import { useTranslation } from "react-i18next";

const appBarHeight = 80;
const menuWidth = "26%";
const inputContainerHeight = 258;

const useStyles = makeStyles(() => ({
    appBar: {
        zIndex: "1300",
        width: "100%",
        height: appBarHeight,
        backgroundColor: "LightSalmon",
        borderTop: "1px solid black",
    },
    appBarContentContainer: {
        height: appBarHeight,
        width: "100%",
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
    },
    chatroomName: {
        display: "flex",
        alignItems: "center",
    },
    activeChatButton: {
        textTransform: "none",
    },
    activeChatChatroomIdContainer: {
        padding: 10,
    },
    drawerOnLeft: {
        width: menuWidth,
        flexShrink: 0,
    },
    drawerPaperOnLeft: {
        width: menuWidth,
    },
    drawerOnRight: {
        width: `calc(100% - ${menuWidth})`,
        flexShrink: 0,
    },
    drawerPaperOnRight: {
        width: `calc(100% - ${menuWidth})`,
    },
    titleContainer: {
        height: 80,
        display: "flex",
        alignItems: "center",
        borderTop: "1px solid black",
    },
    title: {
        marginLeft: 40,
        fontSize: 40,
    },
    forTableContainer: {
        width: "100%",
        marginBottom: inputContainerHeight,
    },
    panelsContainer: {
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
    },
    privateChatCard: {
        width: "100%",
        height: 50,
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        textTransform: "none",
    },
    groupChatCard: {
        width: "100%",
        height: 50,
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        textTransform: "none",
        backgroundColor: "sandyBrown",
    },
    userInfo: {
        zIndex: 1250,
        position: "fixed",
        bottom: 40,
        left: 40,
    },
    // make sure contents are below app bar!
    toolbar: {
        height: appBarHeight,
    },
    languageSelector: {
        justifySelf: "flex-end",
    },
    messagesArea: {
        width: "100%",
        marginTop: 20,
        paddingLeft: 20,
        paddingRight: 20,
        paddingBottom: 25,
        display: "flex",
        flexDirection: "column",
    },
    inputContainer: {
        zIndex: 1300,
        position: "fixed",
        bottom: 0,
        right: 0,
        width: `calc(100% - ${menuWidth})`,
        height: inputContainerHeight,
        display: "flex",
        flexDirection: "column",
        justifyContent: "space-between",
        alignItems: "flex-end",
        paddingTop: 13,
        paddingLeft: 10,
        paddingRight: 10,
        paddingBottom: 12,
        borderTop: "1px solid lightgray",
        backgroundColor: "white",
    },
}));

const { REACT_APP_SERVER_ADDRESS } = process.env;

var stompClient = null;

export default function Chat(props) {
    const classes = useStyles();

    // i18n
    const { t, i18n } = useTranslation();

    const changeLanguage = (lng) => {
        i18n.changeLanguage(lng);
    };
    //

    const [language, setLanguage] = useState(i18n.language);
    const handleChangeLanguage = (e) => {
        let language = e.target.value;
        setLanguage(language);
        changeLanguage(language);
    };

    const username = props.user.name;
    const [chatText, setChatText] = useState("");
    const [rooms, setRooms] = useState([]);
    const [activeChat, setActiveChat] = useState({
        chatroomId: "",
        name: "",
    });
    const [currentChatroomMessages, setCurrentChatroomMessages] = useState([]);
    const [receivedMessages, setReceivedMessages] = useState([]);
    const [StompCommunicationInitialized, setStompCommunicationInitialized] = useState(false);
    const [notificationsSubscribed, setNotificationsSubscribed] = useState(false);
    const [subscribedChatrooms, setSubscribedChatrooms] = useState([]);

    const setChatrooms = () => {
        axios
            .get(REACT_APP_SERVER_ADDRESS + "/users/" + username + "/me")
            .then((response) => {
                setRooms(response.data.chatrooms);
            });
    };

    useEffect(setChatrooms, [username]); // set chatrooms after entering the chat page

    useEffect(() => {
        if (rooms === undefined || rooms.length === 0) {
            return;
        } else {
            if (rooms.length > 0) {
                if (activeChat.chatroomId === "") {
                    setActiveChat(rooms[0]);
                }
            }
            if (!StompCommunicationInitialized) {
                initializeStompCommunication();
                setStompCommunicationInitialized(true);
            } else {
                subscribeStuffs();
            }
        }
    }, [rooms]);

    const refreshChatroomMessages = () => {
        let messages = findChatMessages(activeChat.name);
        setCurrentChatroomMessages(messages);
    };

    useEffect(refreshChatroomMessages, [activeChat, receivedMessages]);

    const findChatMessages = () => {
        let messages = [];
        for (let i = 0; i < receivedMessages.length; i++) {
            let message = receivedMessages[i];
            if (
                message.sender === activeChat.name ||
                message.receiver === activeChat.name
            ) {
                messages.push(message);
            }
        }
        return messages;
    };

    const initializeStompCommunication = () => {
        const Stomp = require("stompjs");
        var SockJS = require("sockjs-client");
        SockJS = new SockJS(REACT_APP_SERVER_ADDRESS + "/chat");
        stompClient = Stomp.over(SockJS);
        stompClient.connect({ username: username }, onConnected, onError);
    };

    const subscribeStuffs = () => {
        subscribeNotifications();
        subscribeChatrooms();
    };

    const onConnected = () => {
        console.log("connected");
        subscribeStuffs();
    };

    const subscribeNotifications = () => {
        if (notificationsSubscribed) {
            return;
        }
        stompClient.subscribe("/topic/notice." + username, onNoticeReceived);
        setNotificationsSubscribed(true);
    };

    const onNoticeReceived = (ntc) => {
        let notice = JSON.parse(ntc.body);
        if (notice.type === 1) {
            setChatrooms();
        }
    };

    const subscribeChatrooms = () => {
        rooms.forEach((room) => {
            let id = room.chatroomId;
            if (subscribedChatrooms.includes(id)) {
                return;
            }
            stompClient.subscribe("/topic/chatroom." + id, onMessageReceived);
            setSubscribedChatrooms((subscribedChatrooms) => [
                ...subscribedChatrooms,
                id,
            ]);
        });
    };

    const onMessageReceived = (msg) => {
        let message = JSON.parse(msg.body);
        message.time = new Date(message.time).toString();
        if (message.sender === username) {
            message.mine = true;
        } else {
            message.mine = false;
        }
        setReceivedMessages((messages) => [...messages, message]);
    };

    const onError = (err) => {
        console.log(err);
    };

    useEffect(() => {
        var element = document.getElementById("dialogBox");
        element.scrollIntoView(false);
    }, [currentChatroomMessages]);

    const handleKeyDown = (e) => {
        if (e.key === "Enter") {
            e.preventDefault();
            sendChatMessage();
        }
    };

    async function sendChatMessage() {
        let response = await axios.get(REACT_APP_SERVER_ADDRESS + "/time");
        sendMessage(response.data.UTCTime.UnixTime);
    }

    const sendMessage = (time) => {
        setChatText("");
        let msg = chatText;
        if (msg.trim() !== "") {
            let chatroomId = activeChat.chatroomId;
            let friendName = activeChat.name;
            const message = {
                sender: username,
                receiver: friendName,
                content: msg,
                time: new Date(time),
            };
            stompClient.send(
                "/app/chatroom/" + chatroomId,
                {},
                JSON.stringify(message)
            );
        }
    };

    // for popover
    const [anchorEl, setAnchorEl] = useState(null);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const open = Boolean(anchorEl);
    //

    return (
        <div>
            <CssBaseline />
            <Drawer
                className={classes.drawerOnLeft}
                variant="permanent"
                classes={{
                    paper: classes.drawerPaperOnLeft,
                }}
                anchor="left"
            >
                <div className={classes.toolbar}>
                    <div className={classes.titleContainer}>
                        <div className={classes.title}>Chat!</div>
                    </div>
                </div>
                <Divider />
                <TableContainer className={classes.forTableContainer}>
                    <Table stickyHeader>
                        <List>
                            {rooms.map((room) => (
                                <ListItem>
                                    <Button
                                        variant="contained"
                                        color="secondary"
                                        onClick={() => setActiveChat(room)}
                                        className={
                                            room.type === 0
                                                ? classes.privateChatCard
                                                : classes.groupChatCard
                                        }
                                    >
                                        <Typography
                                            variant="h5"
                                            gutterBottom
                                            align="center"
                                        >
                                            {room.name}
                                        </Typography>
                                    </Button>
                                </ListItem>
                            ))}
                        </List>
                    </Table>
                </TableContainer>

                <div className={classes.panelsContainer}>
                    <Panels
                        username={username}
                        setChatrooms={setChatrooms}
                    ></Panels>
                </div>

                <div className={classes.userInfo}>
                    <Typography variant="h4">{username}</Typography>
                </div>
            </Drawer>

            <CssBaseline />
            <Drawer
                className={classes.drawerOnRight}
                variant="permanent"
                classes={{
                    paper: classes.drawerPaperOnRight,
                }}
                anchor="right"
            >
                <AppBar position="sticky" className={classes.appBar}>
                    <Toolbar className={classes.appBarContentContainer}>
                        <div className={classes.chatroomName}>
                            <Button
                                className={classes.activeChatButton}
                                onClick={handleClick}
                                disabled={activeChat.type === 0}
                                style={{
                                    color: "black",
                                }}
                            >
                                <Typography variant="h4" noWrap>
                                    {activeChat.name}
                                </Typography>
                            </Button>
                            <Popover
                                open={open}
                                anchorEl={anchorEl}
                                onClose={handleClose}
                                anchorOrigin={{
                                    vertical: "center",
                                    horizontal: "right",
                                }}
                            >
                                <div
                                    className={
                                        classes.activeChatChatroomIdContainer
                                    }
                                >
                                    <Typography variant="h6">
                                        {activeChat.chatroomId}
                                    </Typography>
                                </div>
                            </Popover>
                        </div>
                        <FormControl className={classes.languageSelector}>
                            <InputLabel>
                                {t("chat.languageIndicator")}
                            </InputLabel>
                            <Select
                                value={language}
                                onChange={(e) => handleChangeLanguage(e)}
                            >
                                <MenuItem value="en">English</MenuItem>
                                <MenuItem value="zh">中文</MenuItem>
                            </Select>
                        </FormControl>
                    </Toolbar>
                </AppBar>

                <TableContainer className={classes.forTableContainer}>
                    <Table stickyHeader id="dialogBox">
                        <div className={classes.messagesArea}>
                            {currentChatroomMessages.map((message) => (
                                <MessageBox
                                    username={message.sender}
                                    content={message.content}
                                    time={message.time}
                                    mine={message.mine}
                                ></MessageBox>
                            ))}
                        </div>
                    </Table>
                </TableContainer>
            </Drawer>

            <div className={classes.inputContainer}>
                <TextField
                    variant="outlined"
                    fullWidth
                    multiline
                    rows="8"
                    value={chatText}
                    onChange={(e) => setChatText(e.target.value)}
                    onKeyDown={(e) => handleKeyDown(e)}
                ></TextField>
                <Button
                    variant="outlined"
                    color="primary"
                    onClick={sendChatMessage}
                >
                    {t("chat.sendButton")}
                </Button>
            </div>
        </div>
    );
}
