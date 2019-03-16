package dk.kea.blog.Models;

public class Friendship {

    private int id;
    private String usernameFrom;
    private int user1;
    private int user2;
    private boolean isFriends;
    private String friendshipDate;

    public Friendship(int id, int user1, int user2, boolean isFriends, String friendshipDate) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.isFriends = isFriends;
        this.friendshipDate = friendshipDate;
    }

    public Friendship(int user1, int user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public Friendship() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser1() {
        return user1;
    }

    public void setUser1(int user1) {
        this.user1 = user1;
    }

    public int getUser2() {
        return user2;
    }

    public void setUser2(int user2) {
        this.user2 = user2;
    }

    public boolean isFriends() {
        return isFriends;
    }

    public void setFriends(boolean friends) {
        isFriends = friends;
    }

    public String getFriendshipDate() {
        return friendshipDate;
    }

    public void setFriendshipDate(String friendshipDate) {
        this.friendshipDate = friendshipDate;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }
}
