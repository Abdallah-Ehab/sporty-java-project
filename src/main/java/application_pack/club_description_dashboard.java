package application_pack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class club_description_dashboard extends get_club_info_from_api implements Initializable {

    private scene_manager go_to_dashboard = new scene_manager();

    private static String sport_name;

    @FXML
    private ImageView club_image_id;
    @FXML
    private TextArea club_description_text;

    @FXML
    private Text club_title;

    @FXML
    private FlowPane buttons_flow_pane;

    private String sql_get_sports_available= "SELECT t.sport FROM teams t JOIN clubs c ON t.club_id = c.id where c.id = ?";

    private static String button_text;

    private Connection conn = null;

    private PreparedStatement pst = null;

    private ResultSet res = null;


    public void generate_button() {
        try {
            conn = DB_connection.connection();
            pst = conn.prepareStatement(sql_get_sports_available);
            pst.setString(1, Integer.toString(clubs_menu_dashboard.get_image_id()));
            res = pst.executeQuery();
            while (res.next()) {
                String sport = res.getString("sport");

                Button button = new Button(sport);
                button.setMinWidth(200);
                buttons_flow_pane.setVgap(50);
                buttons_flow_pane.setHgap(50);


                // Assign an event handler to each button
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        sport_name = button.getText();
                        try {
                            go_to_dashboard.go_to_dashboard(event);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });

                buttons_flow_pane.getChildren().add(button);
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }


    public void add_descrption(){
        String description = get_club_info_from_json();
        if(description != null){
            club_description_text.setText(get_club_info_from_json());
        } else if(description== null && get_club_info_from_wiki()!= null){
            club_description_text.setText(get_club_info_from_wiki());
        }else{
            club_description_text.setText("no description available");
        }
    }
    public void add_image(){
        Image image = new Image(clubs_menu_dashboard.get_clicked_image_path());
        club_image_id.setImage(image);
    }

    public void add_title(){
        String title = clubs_menu_dashboard.get_clicked_club_name();
        club_title.setText(title);
    }

    public static String get_sport_name(){
        return sport_name;
    }


    @FXML
    void back_to_clubs(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("clubs_menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("clubs_menu");
        stage.setScene(scene);
        stage.show();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            add_image();
            add_title();
            generate_button();
            add_descrption();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
