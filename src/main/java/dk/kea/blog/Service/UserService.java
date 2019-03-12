package dk.kea.blog.Service;


import dk.kea.blog.Models.User;
import dk.kea.blog.Repositories.Database;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                    user.setRoleName(rs.getString("name"));
                    user.setLevel(rs.getInt("level"));
                    user.setId(rs.getInt("id"));
                    user.setAge(rs.getInt("age"));
                    user.setDate(rs.getString("creationdate"));

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

    public boolean createUser(User user) {
        db.createUser(user);

        return true;
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        ResultSet rs = db.getUsers();
        try {
            while (rs.next()) {
                User user = new User();
                user.setFirstname(rs.getString("firstName"));
                user.setLastname(rs.getString("lastName"));
                user.setCity(rs.getString("city"));
                user.setAge(rs.getInt("age"));
                user.setEmail(rs.getString("email"));
                user.setDate(rs.getString("creationdate"));
                user.setRoleName(rs.getString("name"));
                user.setId(rs.getInt("users.id"));
                user.setRid(rs.getInt("roles.id"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<User> getRoles() {
        List<User> roleList = new ArrayList<>();
        ResultSet rs = db.getRoles();
        try {
            while (rs.next()) {
                User user = new User();
                user.setRoleName(rs.getString("name"));
                user.setRid(rs.getInt("id"));
                roleList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }

    public boolean deleteUser(int id) {
        db.delete("users", id);
        return true;
    }

}
