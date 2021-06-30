package edu.escuelaing.VirtualBLab.Storage;

import java.util.HashSet;
import java.util.Set;
import edu.escuelaing.VirtualBLab.Exeptions.*;

public class UserStorage {
    private static UserStorage _instance= new UserStorage();
    private Set<String> users;

    private UserStorage(){
        users= new HashSet<>();
    }

    public static synchronized UserStorage getInstance(){
        return _instance;
    }

    public Set<String> getUsers() {
        return this.users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }

    public void addUser(String userName) throws VLabExeptions{
        if(users.contains(userName)) throw new VLabExeptions(VLabExeptions.userNameTakenException);
        users.add(userName);
    }
}
