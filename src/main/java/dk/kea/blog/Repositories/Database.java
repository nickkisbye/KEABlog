package dk.kea.blog.Repositories;

import dk.kea.blog.Models.Blog;
import dk.kea.blog.Models.Friendship;
import dk.kea.blog.Models.Message;
import dk.kea.blog.Models.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class Database {

    private Connection con;
    private PreparedStatement preparedStatement;

    public Database() {
        try {
            this.con = DriverManager.getConnection(
                    "jdbc:mysql://den1.mysql5.gear.host/blogapp",
                    "blogapp",
                    "Qm1bh3_u_YYJ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectUser(User user) {
        String query = "SELECT * FROM users INNER JOIN roles ON users.fk_roles = roles.id WHERE email = ? AND password = md5(?) ";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void newPasswordDB(User user){
        String sql = "UPDATE users SET password = md5(?) WHERE email = ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,user.getPassword());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getBlogPosts() {
        String query = "SELECT * FROM blog INNER JOIN users ON blog.fk_users = users.id ORDER BY blog.id DESC";
        try {
            preparedStatement = con.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getLatestBlogPosts() {
        String query = "SELECT * FROM blog INNER JOIN users ON blog.fk_users = users.id ORDER BY blog.id DESC LIMIT 4";
        try {
            preparedStatement = con.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createPost(Blog blog) {
        String query = "INSERT INTO blog (title, text, fk_users) VALUES (?,?,?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getText());
            preparedStatement.setInt(3, blog.getAid());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBlog(Blog blog) {
        String query = "UPDATE blog SET title=?, text=? WHERE id=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getText());
            preparedStatement.setInt(3, blog.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String deleteFrom, int id) {
        deleteFriendsFromDeletedUser(id);
        String query = "DELETE FROM "+deleteFrom+" WHERE id=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(User user) {
        String query = "INSERT INTO users(firstName, lastName, city, email, age, password, fk_roles) VALUES (?, ?, ?, ?, ?, md5(?), ?)";

        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getRid());
            preparedStatement.execute();
            preparedStatement.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet findUserById(int id) {
        String query = "SELECT * FROM users INNER JOIN roles ON users.fk_roles = roles.id WHERE users.id = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet findBlogById(int id) {
        String query = "SELECT * FROM blog WHERE id = ?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUser(User user) {
        String query = "UPDATE users SET firstName=?, lastName=?, city=?, email=?, age=?, password=md5(?), fk_roles=? WHERE id=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getRid());
            preparedStatement.setInt(8,user.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  ResultSet getUsers() {
        String query = "SELECT * FROM users INNER JOIN roles ON users.fk_roles = roles.id ORDER BY users.id DESC";
        try {
            preparedStatement = con.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  ResultSet getLatestUser() {
        String query = "SELECT * FROM users INNER JOIN roles ON users.fk_roles = roles.id ORDER BY users.id DESC LIMIT 4";
        try {
            preparedStatement = con.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public  ResultSet getRoles() {
        String query = "SELECT * FROM roles";
        try {
            preparedStatement = con.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public ResultSet receiveMessages(int sender, int receiver) {
        String query = "SELECT * FROM message " +
                "INNER JOIN users as u1 ON fk_senderUser = u1.id " +
                "INNER JOIN users as u2 ON fk_receiverUser = u2.id " +
                "WHERE (fk_senderUser = ? AND fk_receiverUser = ?) OR (fk_senderUser = ? AND fk_receiverUser = ?) ORDER BY message.id";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, sender);
            preparedStatement.setInt(2, receiver);
            preparedStatement.setInt(3, receiver);
            preparedStatement.setInt(4, sender);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet userAlreadyexists(String email) {
        String query = "SELECT email, id FROM users WHERE email=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, email);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet userAlreadyexistsUpdate(String email, int id) {
        String query = "SELECT email FROM users WHERE email=? AND id=?";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void sendMsg(Message message, int sender, int receiver) {
        String query = "INSERT INTO message (message, readCheck, fk_senderUser, fk_receiverUser) VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, message.getMessage());
            preparedStatement.setBoolean(2, false);
            preparedStatement.setInt(3, sender);
            preparedStatement.setInt(4, receiver);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
