package dk.kea.blog.Models;

public class Message {

    private int id;
    private String message;
    private boolean readCheck;
    private String timestamp;
    private int senderUser;
    private int receiverUser;

    public Message(int id, String message, boolean readCheck, String timestamp, int senderUser, int receiverUser) {
        this.id = id;
        this.message = message;
        this.readCheck = readCheck;
        this.timestamp = timestamp;
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

    public boolean isReadCheck() {
        return readCheck;
    }

    public void setReadCheck(boolean readCheck) {
        this.readCheck = readCheck;
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
