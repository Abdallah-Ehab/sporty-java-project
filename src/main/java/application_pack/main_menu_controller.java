package application_pack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main_menu_controller {

    private String scene_name;

public void get_clubs_scene(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("clubs_table.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setTitle("clubs");
    stage.setScene(scene);
    stage.show();


    scene_name = "clubs_scene";
}
    public void get_teams_scene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("teams_table.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("teams");
        stage.setScene(scene);
        stage.show();


        scene_name = "teams_scene";
    }
    public void get_matches_scene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("matches_table.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("matches");
        stage.setScene(scene);
        stage.show();


        scene_name = "matches_scene";
    }

    @FXML
    void log_out(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("log_in.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("log_in");
        stage.setScene(scene);
        stage.show();
    }
    public void get_players_scene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("players_table.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("players");
        stage.setScene(scene);
        stage.show();


        scene_name = "players_scene";
    }
    public void get_stats_scene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stats_table.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("players");
        stage.setScene(scene);
        stage.show();
    }
}
