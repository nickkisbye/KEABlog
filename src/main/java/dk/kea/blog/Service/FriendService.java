package dk.kea.blog.Service;

import dk.kea.blog.Models.Friendship;
import dk.kea.blog.Repositories.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    @Autowired
    Database db;

    public void acceptFriendRequest(int id) {
        db.acceptFriendRequest(id);
    }

    public void deleteFriendRequest(int id) {
        db.deleteFriendRequest(id);
    }

    public void addFriend(Friendship friendship) {
        db.addFriend(friendship);
    }
}
