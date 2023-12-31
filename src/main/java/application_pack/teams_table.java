package application_pack;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class teams_table extends get_lists implements Initializable,table_interface {

    ObservableList<teams> final_list;

    add_to_db add = new add_to_db();

    int index = -1;

    Connection conn = null;

    PreparedStatement pst = null;


    @FXML
    private TextField championships_text;

    @FXML
    private TextField cleansheets_text;

    @FXML
    private TextField club_id_text;

    @FXML
    private TextField goals_text;

    @FXML
    private TextField points_text;

    @FXML
    private Button team_add_btn;

    @FXML
    private Button team_delete_btn;

    @FXML
    private Button team_update_btn;

    @FXML
    private TableColumn<teams, String> top_player_image;


    @FXML
    private TableColumn<teams, Integer> teams_championships_col;

    @FXML
    private TableColumn<teams, Integer> teams_cleansheets_col;

    @FXML
    private TableColumn<teams,Integer> teams_club_id_col;

    @FXML
    private TableColumn<teams, Integer> teams_goals_col;

    @FXML
    private TableColumn<teams, Integer> teams_id_col;

    @FXML
    private TableColumn<teams, Integer> teams_points_col;

    @FXML
    private TableColumn<teams,String> teams_sport_col;

    @FXML
    private TableView<teams> teams_table_view;

    @FXML
    private TableColumn<teams,String> teams_top_player_col;

    @FXML
    private TextField top_player_text;

    @FXML
    private TextField team_sport_text;

    FileChooser fileChooser = new FileChooser();

    private String filePath;

    private String fileUrl;



    @Override
    public void add(ActionEvent event){
        String sql = "insert into (sport, championships, player_of_month, points, goals, cleansheets, club_id,top_player_image) values(?,?,?,?,?,?,?,?)";
        String sport = team_sport_text.getText();
        String championships = championships_text.getText();
        String top_player = top_player_text.getText();
        String points = points_text.getText();
        String goals = goals_text.getText();
        String cleansheets = cleansheets_text.getText();
        String club_id = club_id_text.getText();
        String file_path = fileUrl;

        add.add_to_teams_table(sql,sport,championships,top_player,points,goals,cleansheets,club_id,file_path);
        this.refresh_and_update();
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
    @Override
    public void get_selected_table_data(){
        index = teams_table_view.getSelectionModel().getSelectedIndex();
        if(index == -1){
            return;
        }else{
            team_sport_text.setText(teams_sport_col.getCellData(index).toString());
            championships_text.setText(teams_championships_col.getCellData(index).toString());
            top_player_text.setText(teams_top_player_col.getCellData(index).toString());
            points_text.setText(teams_points_col.getCellData(index).toString());
            goals_text.setText(teams_goals_col.getCellData(index).toString());
            cleansheets_text.setText(teams_cleansheets_col.getCellData(index).toString());
            club_id_text.setText(teams_club_id_col.getCellData(index).toString());
        }


    }

    @Override
    public void Update(ActionEvent event){
        String sport = team_sport_text.getText();
        String championships = championships_text.getText();
        String top_player = top_player_text.getText();
        String points = points_text.getText();
        String goals = goals_text.getText();
        String cleansheets = cleansheets_text.getText();
        String club_id = club_id_text.getText();
        String file_path = fileUrl;
        try{
            conn = DB_connection.connection();
            String sql_update = "UPDATE teams SET sport=?, championships=?, player_of_month=?, points=?, goals=?, cleansheets=?, club_id=?,top_player_image=? WHERE id=?";
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, sport);
            pst.setInt(2, Integer.parseInt(championships));
            pst.setString(3, top_player);
            pst.setInt(4, Integer.parseInt(points));
            pst.setInt(5, Integer.parseInt(goals));
            pst.setInt(6, Integer.parseInt(cleansheets));
            pst.setInt(7, Integer.parseInt(club_id));
            pst.setString(8,fileUrl);
            pst.setString(9, teams_id_col.getCellData(index).toString());
            pst.executeUpdate();
            alerts_and_warning.get_alert("happy","congrats data was edited successfully");
            this.refresh_and_update();
        } catch (Exception e) {
            alerts_and_warning.get_alert("error","editing failed");
            System.out.println(e);
        }
    }

    @Override
    public void delete(ActionEvent event){
        String sql_delete = "DELETE from teams where id =?";
        try{
            conn = DB_connection.connection();
            pst = conn.prepareStatement(sql_delete);
            pst.setString(1,teams_id_col.getCellData(index).toString());
            pst.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void upload(){
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            filePath = selectedFile.getAbsolutePath();
            fileUrl = null;
            try {
                fileUrl = selectedFile.toURI().toURL().toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void refresh_and_update(){
        final_list.clear();
        final_list=get_teams_list();
        teams_table_view.setItems(final_list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teams_id_col.setCellValueFactory(new PropertyValueFactory<teams,Integer>("id"));
        teams_sport_col.setCellValueFactory(new PropertyValueFactory<teams,String>("sport"));
        teams_championships_col.setCellValueFactory(new PropertyValueFactory<teams,Integer>("championships"));
        teams_top_player_col.setCellValueFactory(new PropertyValueFactory<teams,String>("player_of_month"));
        teams_points_col.setCellValueFactory(new PropertyValueFactory<teams,Integer>("points"));
        teams_goals_col.setCellValueFactory(new PropertyValueFactory<teams,Integer>("goals"));
        teams_cleansheets_col.setCellValueFactory(new PropertyValueFactory<teams,Integer>("cleansheets"));
        teams_club_id_col.setCellValueFactory(new PropertyValueFactory<teams,Integer>("club_id"));
        top_player_image.setCellValueFactory(new PropertyValueFactory<teams,String>("top_player_image"));
        final_list = get_teams_list();

        teams_table_view.setItems(final_list);

    }
}
