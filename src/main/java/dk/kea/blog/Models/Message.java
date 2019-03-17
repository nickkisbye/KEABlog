package dk.kea.blog.Models;

public class Message {

    private int id;
    private String message;
    private boolean read;
    private String timestamp;
    private int senderUser;
    private int receiverUser;

    public Message(int id, String message, boolean read, String timestamp, int senderUser, int receiverUser) {
        this.id = id;
        this.message = message;
        this.read = read;
        this.timestamp = timestamp;
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
    }

    public Message(String message, int senderUser, int receiverUser) {
        this.message = message;
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
    }

    public Message() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(int senderUser) {
        this.senderUser = senderUser;
    }

    public int getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(int receiverUser) {
        this.receiverUser = receiverUser;
    }
}
