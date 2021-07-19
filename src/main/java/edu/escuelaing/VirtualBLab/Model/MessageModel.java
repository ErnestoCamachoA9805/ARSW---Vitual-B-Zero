package edu.escuelaing.VirtualBLab.Model;

public class MessageModel {
    private String message;
    private String fromLogin;
    private String toUser;

    public String getToUser() {
        return this.toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromLogin() {
        return this.fromLogin;
    }

    public void setFromLogin(String fromLogin) {
        this.fromLogin = fromLogin;
    }

    @Override
    public String toString() {
        return "{" +
            " message='" + getMessage() + "'" +
            ", fromLogin='" + getFromLogin() + "'" +
            "}";
    }
}

