package dk.kea.blog.Repositories;

import dk.kea.blog.Models.Message;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class MessageRepository {
    private Connection con;
    private PreparedStatement preparedStatement;

    public MessageRepository() {
        try {
            this.con = DriverManager.getConnection(
                    "jdbc:mysql://den1.mysql5.gear.host:3306/blogapp",
                    "blogapp",
                    "Qm1bh3_u_YYJ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet receiveMessages(int sender, int receiver) {
        String query = "SELECT * FROM message " +
                "LEFT JOIN users as u1 ON fk_senderUser = u1.id " +
                "LEFT JOIN users as u2 ON fk_receiverUser = u2.id " +
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
}
