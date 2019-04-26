package dk.kea.blog.Repositories;

import dk.kea.blog.Models.Friendship;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class FriendRepository {
    private Connection con;
    private PreparedStatement preparedStatement;

    public FriendRepository() {
        try {
            this.con = DriverManager.getConnection(
                    "jdbc:mysql://den1.mysql5.gear.host:3306/blogapp",
                    "blogapp",
                    "Qm1bh3_u_YYJ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFriend(Friendship friendship) {
        String query = "INSERT INTO friends (`fk_userIdOne`, `fk_userIdTwo`, `isFriends`) VALUES (?, ?, ?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, friendship.getUser1());
            preparedStatement.setInt(2, friendship.getUser2());
            preparedStatement.setBoolean(3, false);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getFriendRequests(int id) {
        String query = "SELECT * FROM friends " +
                "INNER JOIN users ON friends.fk_userIdOne = users.id " +
                "WHERE fk_userIdTwo=? AND !isFriends";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getFriends(int id) {
        String query = "SELECT * FROM friends" +
                " INNER JOIN users as u1 on fk_userIdOne = u1.id" +
                " INNER JOIN users as u2 on fk_userIdTwo = u2.id" +
                " WHERE (u1.id=? OR u2.id=?) AND isFriends ";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void acceptFriendRequest(int id) {
        String query = "UPDATE friends SET isFriends = true WHERE id = ?";

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFriendRequest(int id) {
        String query = "DELETE FROM friends WHERE id = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFriendsFromDeletedUser(int id) {
        String query = "DELETE FROM friends WHERE fk_userIdOne=? OR fk_userIdTwo=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet checkForFriends(int id, int sid) {
        String query = "SELECT * FROM friends WHERE (fk_userIdOne=? AND fk_userIdTwo=?) OR (fk_userIdOne=? AND fk_userIdTwo=?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, sid);
            preparedStatement.setInt(3, sid);
            preparedStatement.setInt(4, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet findFriendById(int id) {
        String query = "SELECT * FROM friends" +
                " INNER JOIN users as u1 on fk_userIdOne = u1.id" +
                " INNER JOIN users as u2 on fk_userIdTwo = u2.id" +
                " WHERE fk_userIdTwo = " + id;
        try {
            preparedStatement = con.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
