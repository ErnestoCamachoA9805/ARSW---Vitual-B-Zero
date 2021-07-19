package edu.escuelaing.VirtualBLab.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.VirtualBLab.Model.MessageModel;
import edu.escuelaing.VirtualBLab.Storage.MessageStorage;
import edu.escuelaing.VirtualBLab.Storage.UserStorage;

@RestController
@CrossOrigin
public class VlabMessageController {
    private UserStorage users= UserStorage.getInstance();
    private MessageStorage mensajes= MessageStorage.getInstance();

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{toUser}")
    public void sendMessageUser(@DestinationVariable String toUser, MessageModel message){
        System.out.println("send message: " + message + " to " + toUser);
        if(users.getUsers().contains(toUser)){
            mensajes.addMessage(message);
            simpMessagingTemplate.convertAndSend("/topic/messages/" + toUser, message);
        }else{
            System.out.println("no contiene al usuario: " + toUser);
        }
    }
}
