package star.astro.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import star.astro.chat.exception.CustomException;
import star.astro.chat.exception.NotAcceptableUGCException;
import star.astro.chat.exception.UnAuthorizedException;
import star.astro.chat.service.ChatroomService;
import star.astro.chat.util.JwtUtil;
import star.astro.chat.util.UGCValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/chatrooms")
@CrossOrigin(origins="*", allowedHeaders="*")
public class ChatroomController {

    @Autowired
    private UGCValidator ugcValidator;
    @Autowired
    private ChatroomService chatroomService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("")
    @CrossOrigin(origins="*", allowedHeaders="*")
    public ResponseEntity<?> createChatroom(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        String username = (String) params.get("username");
        String chatroomName = (String) params.get("chatroomName");
        try {
            String token = request.getHeader("token");
            jwtUtil.authorize(token, username);
            ugcValidator.validateNames(username, chatroomName);
            chatroomService.createChatroom(username, chatroomName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UnAuthorizedException | NotAcceptableUGCException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/{chatroomId}/users/{username}")
    @CrossOrigin(origins="*", allowedHeaders="*")
    public ResponseEntity<?> addUser(@PathVariable String chatroomId, @PathVariable String username, HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            jwtUtil.authorize(token, username);
            chatroomService.addUser(username, chatroomId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UnAuthorizedException uae) {
            return new ResponseEntity<>(uae.getMessage(), HttpStatus.FORBIDDEN);
        } catch (CustomException ce) {
            return new ResponseEntity<>(ce.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
