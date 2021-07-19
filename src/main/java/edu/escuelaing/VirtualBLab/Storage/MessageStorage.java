package edu.escuelaing.VirtualBLab.Storage;

import java.util.HashSet;
import java.util.Set;

import edu.escuelaing.VirtualBLab.Model.MessageModel;

public class MessageStorage {
    private static MessageStorage _instance= new MessageStorage();
    private HashSet<MessageModel> messages= new HashSet<>();

    private MessageStorage(){
        messages= new HashSet<>();
    }

    public static synchronized MessageStorage getInstance(){
        return _instance;
    }

    public Set<MessageModel> getMessages() {
        return this.messages;
    }

    public void setMessages(Set<MessageModel> messages) {
        this.messages = (HashSet<MessageModel>) messages;
    }

    public void clearAllMessages(){
        messages.clear();
    }

    public void clearMessagesFromUser(String userName){
        for(MessageModel message: messages){
            if (message.getToUser().equals(userName)){
                messages.remove(message);
            }
        }
    }
    
    public void addMessage(MessageModel message){
        messages.add(message);
    }
}

