package application_pack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class log_in extends scene_manager {

    @FXML
    private CheckBox admin_checkbox_id;




    @FXML
    private Button go_to_signup_btn;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField password_login_text;

    @FXML
    private TextField user_name_login_text;




    Connection conn = null;

    PreparedStatement pst = null;


    private String sql;

    @FXML
    public void submit_log_in(ActionEvent event) throws IOException {

        if(admin_checkbox_id.isSelected()){
            sql = "SELECT * FROM logged_in WHERE user_name = ? AND password = ? AND admin = true";
        }else{

        sql = "SELECT * FROM logged_in WHERE user_name = ? AND password = ?";
        }


        String user_name = user_name_login_text.getText();

        String password = password_login_text.getText();
        try {
            conn = DB_connection.connection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, user_name);
            pst.setString(2, password);
            if(pst.executeQuery().next()){
                if(!admin_checkbox_id.isSelected()){
                    go_to("clubs_menu.fxml","clubs_menu",event);
                }
                go_to("main_menu.fxml","main_menu",event);
                alerts_and_warning.get_alert("happy","login succeeded").showAndWait();
            }else{
                alerts_and_warning.get_alert("error","sign up first please").showAndWait();
            }
        } catch (SQLException e) {
            alerts_and_warning.get_alert("error", "Error occurred during login").showAndWait();
            e.printStackTrace();
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void go_to_sign_up(ActionEvent event) throws IOException {
        go_to("sign_up.fxml","sign_ip",event);
    }
}
