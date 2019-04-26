package dk.kea.blog.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class Database {

    private Connection con;
    private PreparedStatement preparedStatement;

    public Database() {
        try {
            this.con = DriverManager.getConnection(
                    "jdbc:mysql://den1.mysql5.gear.host:3306/blogapp",
                    "blogapp",
                    "Qm1bh3_u_YYJ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    FriendRepository friendRepository;

    public void delete(String deleteFrom, int id) {
        friendRepository.deleteFriendsFromDeletedUser(id);
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

}
