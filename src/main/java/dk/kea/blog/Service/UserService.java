package dk.kea.blog.Service;


import dk.kea.blog.Models.User;
import dk.kea.blog.Repositories.Database;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserService {
    Database db = new Database();
    public boolean verifyUser(User user) {
        ResultSet rs = db.selectUser(user);
        try {
            if (rs.next()) {
                if (rs.getString("email").equals(user.getEmail()) && rs.getString("password").equals(user.getPassword())) {
                    user.setCity(rs.getString("city"));
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
