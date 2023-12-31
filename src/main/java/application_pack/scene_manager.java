package application_pack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class scene_manager {

    Connection conn = null;

    PreparedStatement pst = null;

    @FXML
    private Button back_to_main_menu;





    public void go_to(String scene_name,String title, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(scene_name));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void go_to_main_menu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main_menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("main_menu");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void go_to_club_description_dashboard(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("club_description_dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("club_description");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void go_to_dashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
