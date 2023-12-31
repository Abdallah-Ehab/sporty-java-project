package application_pack;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.Vector;

public class clubs_menu_dashboard implements Initializable {


    scene_manager go_to_description = new scene_manager();
    @FXML
    private FlowPane flow_pane_id;

    public static int clicked_image_id;

    public static String clicked_club_name;

    public static String clicked_image_path;

    String sql = "select * from clubs";

    Vector<query_data> image_vector = new Vector<>();

    Connection conn = null;

    PreparedStatement pst = null;

    ResultSet res = null;

    public void add_to_the_club_menu(){


        conn = DB_connection.connection();
        try{
            pst = conn.prepareStatement(sql);
            res = pst.executeQuery();
            while(res.next()){
                String clubName = res.getString("name");
                String imagePath = res.getString("image_path");
                int id = res.getInt("id");

                image_vector.add(new query_data(imagePath,clubName,id));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        update_flow_pane();
    }

    public void update_flow_pane(){
        flow_pane_id.getChildren().clear();
        flow_pane_id.setHgap(10);
        flow_pane_id.setVgap(10);

            for(query_data data : image_vector){
                if(data.getImage_path() != null){
                    Image image = new Image(data.getImage_path());
                    ImageView imageView = new ImageView(image);

                    imageView.setFitWidth(100);
                    imageView.setPreserveRatio(true);

                    final query_data final_data = data;

                    imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            // Handle the click event
                            clicked_image_id = final_data.getId();
                            clicked_club_name = final_data.getClub_name();
                            clicked_image_path = final_data.getImage_path();
                            try {
                                go_to_description.go_to_club_description_dashboard(event);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    });


                    imageView.setOnMouseEntered(e->{
                        imageView.setEffect(new Glow());
                        imageView.setScaleX(1.02);
                        imageView.setScaleY(1.02);
                        imageView.setCursor(Cursor.HAND);
                    });

                    imageView.setOnMouseExited(e->{
                        imageView.setEffect(null);
                        imageView.setScaleX(1);
                        imageView.setScaleY(1);
                        flow_pane_id.setCursor(Cursor.DEFAULT);
                    });

                    // Add the ImageView to the layout
                    flow_pane_id.getChildren().add(imageView);
                }else{
                    try {
                        URL fileUrl = new URL("file:" + "src/main/logos/dummy_logo.png");

                        data.setImage_path(String.valueOf(fileUrl));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }


                    Image image = new Image(data.getImage_path());
                    ImageView imageView = new ImageView(image);

                    imageView.setFitWidth(100);
                    imageView.setPreserveRatio(true);


                    final query_data final_data = data;

                    imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            // Handle the click event
                            clicked_image_id = final_data.getId();
                            clicked_club_name = final_data.getClub_name();
                            clicked_image_path = final_data.getImage_path();
                            try {
                                go_to_description.go_to_club_description_dashboard(event);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    });

                    imageView.setOnMouseEntered(e->{
                        imageView.setEffect(new Glow());
                        imageView.setScaleX(1.02);
                        imageView.setScaleY(1.02);
                        imageView.setCursor(Cursor.HAND);
                    });

                    imageView.setOnMouseExited(e->{
                        imageView.setEffect(null);
                        imageView.setScaleX(1);
                        imageView.setScaleY(1);
                        flow_pane_id.setCursor(Cursor.DEFAULT);
                    });

                    // Add the ImageView to the layout
                    flow_pane_id.getChildren().add(imageView);

                }

        }
    }

    public static int get_image_id(){
        return clicked_image_id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_to_the_club_menu();
    }

    public static String get_clicked_club_name(){
        return clicked_club_name;
    }

    public static String get_clicked_image_path(){
        return clicked_image_path;
    }
}
