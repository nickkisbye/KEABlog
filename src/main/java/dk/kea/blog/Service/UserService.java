package dk.kea.blog.Service;


import dk.kea.blog.Models.User;
import dk.kea.blog.Repositories.Database;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    Database db = new Database();
    public boolean verifyUser(User user) {
        ResultSet rs = db.selectUser(user);
        try {
            if (rs.next()) {
                if (rs.getString("email").equals(user.getEmail()) && rs.getString("password").equals(user.getPassword())) {
                    user.setCity(rs.getString("city"));
                    user.setFirstname(rs.getString("firstName"));
                    user.setLastname(rs.getString("lastName"));

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

    public boolean validateEmail(String email) {
        String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

        matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean changePassword(User user) {

        // CHECK HERE IF PASSWORD IS VALID (4-16 character)

        db.newPasswordDB(user);
        return true;
    }

}
