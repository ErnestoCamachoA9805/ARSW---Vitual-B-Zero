package edu.escuelaing.VirtualBLab.Controllers;

import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.escuelaing.VirtualBLab.Exeptions.VLabExeptions;
import edu.escuelaing.VirtualBLab.Storage.MessageStorage;
import edu.escuelaing.VirtualBLab.Storage.UserStorage;

@RestController
@CrossOrigin
public class VlabUserController {
    private UserStorage users= UserStorage.getInstance();
    private MessageStorage messages= MessageStorage.getInstance();

    @GetMapping("/registration/{userName}")
    public ResponseEntity<Void> registration(@PathVariable String userName){
        System.out.println("register user : " + userName);
        try {
            users.addUser(userName);
        } catch (VLabExeptions e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAllUsers(){
        return users.getUsers();
    }

    @PostMapping("/ClearAllUsers")
    public void clearAllUsers(){
        messages.clearAllMessages();
        users.clearAllUsers();
    }

    @PostMapping("/ClearUser/{userName}")
    public void clearUser(@PathVariable String userName){
        System.out.println("logout for user: " + userName);
        messages.clearMessagesFromUser(userName);
        users.clearUser(userName);
    }
}


