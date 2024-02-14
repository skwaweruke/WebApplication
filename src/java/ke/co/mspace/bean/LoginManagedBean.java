package ke.co.mspace.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import ke.co.mspace.data.DBConnection;

@ManagedBean(name = "loginManagedBean")
@RequestScoped
@SessionScoped
public class LoginManagedBean implements Serializable {

    private String password;
    private String username;

    public String login() {
        
        System.out.println("Called method login");

        String page = "login";
        Connection connection = DBConnection.getConnection1();

        //establish connection 
        //Connection connection = DriverManager.getConnection(url, username, password);
        String query = "SELECT * FROM tUsers WHERE username = ? AND password = ?";

        try (PreparedStatement preparedstatement = connection.prepareStatement(query)) {

            preparedstatement.setString(1, username);
            preparedstatement.setString(2, password);
            ResultSet resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                page = "demo0";
                // Successful login
                return page; // Redirect to home.xhtml
            }

        } catch (Exception e) {

            System.out.println(e);
        }

        // Default outcome for failed login
        return page;
    }

    // Getters and setters for 'username' and 'password'
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
