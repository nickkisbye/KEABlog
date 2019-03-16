package dk.kea.blog.Service;

import dk.kea.blog.Models.User;
import dk.kea.blog.Repositories.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class UserService {

    @Autowired
    Database db;
    public boolean verifyUser(User user) {
        ResultSet rs = db.selectUser(user);
        try {
            if (rs.next()) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String changePassword(User user) {
        if(user.getPassword().length() < 2 || user.getPassword().length() > 16) {
            return "Password must contain between 3 - 16 characters.";
        } else {
            db.newPasswordDB(user);
            return "You have successfully change your password!";
        }
    }

    public String createUser(User user) {

        if(user.getFirstname().length() < 1 || user.getLastname().length() < 1) {
            return "First & Last name must be minimum 2 characters.";
        } else if(user.getCity().length() < 1 || onlyAlphanumeric(user.getCity())) {
            return "Invalid City name. Minimum 2 characters.";
        } else if(!validateEmail(user.getEmail())) {
            return "Invalid email";
        } else if(user.getPassword().length() < 2 || user.getPassword().length() > 16) {
            return "Password must contain between 3 - 16 characters.";
        } else if(user.getAge() == null || user.getAge() > 120 || user.getAge() < 1) {
            return "Invalid age";
        } else {
            db.createUser(user);
            return "User have successfully been created!";
        }

    }

    public String updateUser(User user) {
        if(user.getFirstname().length() < 1 || user.getLastname().length() < 1) {
            return "First & Last name must be minimum 2 characters.";
        } else if(user.getCity().length() < 1 || onlyAlphanumeric(user.getCity())) {
            return "Invalid City name. Minimum 2 characters.";
        } else if(!validateEmail(user.getEmail())) {
            return "Invalid email";
        } else if(user.getPassword().length() < 2 || user.getPassword().length() > 16) {
            return "Password must contain between 3 - 16 characters.";
        } else if(user.getAge() == null || user.getAge() > 120 || user.getAge() < 1) {
            return "Invalid age";
        } else {
            db.updateUser(user);
            return "User have successfully been updated!";
        }
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
                user.setLevel(rs.getInt("roles.level"));
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

    public List<User> findUserById(int id) {
        List<User> userList = new ArrayList<>();
        ResultSet rs = db.findUserById(id);
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

    public boolean onlyAlphanumeric(String string){
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(string);

        if(matcher.matches()) {
            return true;
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

}
