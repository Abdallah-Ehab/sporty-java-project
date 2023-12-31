package application_pack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class sign_up extends scene_manager{



    @FXML
    private Button return_to_login_btn;

    @FXML
    private CheckBox admin_checkbox_signup;

    @FXML
    private PasswordField confirm_password;


    @FXML
    private PasswordField password_signup_text;

    @FXML
    private Button sign_up_btn;

    @FXML
    private TextField user_name_signup_text;

    String sql = "insert into logged_in(user_name, password,admin) values(?,?,?)";

    private Connection conn = null;

    private PreparedStatement pst = null;

    private String user_name;

    private String password;

    private String repeat_password;



    public void sing_up_submit(){
        conn = DB_connection.connection();

        user_name = user_name_signup_text.getText();
        password = password_signup_text.getText();
        repeat_password = confirm_password.getText();

            try{
                if(password.length()>4 && repeat_password.equals(password)){
                    pst = conn.prepareStatement(sql);
                    pst.setString(1,user_name);
                    pst.setString(2,password);
                    pst.setBoolean(3,admin_checkbox_signup.isSelected());
                    pst.executeUpdate();
                    alerts_and_warning.get_alert("happy","sign up succeeded").showAndWait();
                }else{
                    alerts_and_warning.get_alert("error","signup failed").showAndWait();
                }

            }catch(Exception e){
                alerts_and_warning.get_alert("error","signup failed").showAndWait();
                e.printStackTrace();
            }
        }



    public void return_to_login(ActionEvent event) throws IOException {
        go_to("log_in.fxml","log_in",event);
    }
}
