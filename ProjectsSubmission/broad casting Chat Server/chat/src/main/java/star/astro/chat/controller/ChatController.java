package star.astro.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import star.astro.chat.model.ChatMessage;
import star.astro.chat.model.Notification;
import star.astro.chat.service.NotificationService;

@Controller
@CrossOrigin(origins="*", allowedHeaders="*")
public class ChatController {

    @Autowired
    private NotificationService notificationService;

    @MessageMapping("/chatroom/{room}")
    @SendTo("/topic/chatroom.{room}")
    @CrossOrigin(origins="*", allowedHeaders="*")
    public ChatMessage sendPrivateMessage(@DestinationVariable String room, @Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @SubscribeMapping("/notice.{username}")
    @SendTo("/topic/notice.{username}")
    @CrossOrigin(origins="*", allowedHeaders="*")
    public Notification sendOneTimeMessage(@DestinationVariable String username) {
        return notificationService.constructSubscriptionAcknowledgeReply(username);
    }

}
