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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PlayersTable extends get_lists implements Initializable,table_interface {

    ObservableList<players> final_list;

    add_to_db add = new add_to_db();

    private int index = -1;

    @FXML
    private Button player_add_btn;

    @FXML
    private Button player_delete_btn;

    @FXML
    private TableColumn<players,Integer> player_goals_col;

    @FXML
    private TextField player_goals_text;

    @FXML
    private TableColumn<players,Integer> player_id_col;

    @FXML
    private TableColumn<players,String> player_name_col;

    @FXML
    private TextField player_name_text;

    @FXML
    private TableColumn<players,Integer> player_team_id;

    @FXML
    private TextField player_team_id_text;

    @FXML
    private Button player_update_btn;

    @FXML
    private TableView<players> players_table_view;

    Connection conn = null;

    PreparedStatement pst = null;



    @Override
    public void add(ActionEvent event){
        String sql_add ="insert into players(name, goals, team_id) values(?,?,?)";
        String name = player_name_text.getText();
        String goals = player_goals_text.getText();
        String team_id = player_team_id_text.getText();
        add.add_to_players_table(sql_add,name,goals,team_id);

        this.refresh_and_update();
    }

    @Override
    public void refresh_and_update(){
        final_list.clear();
        final_list = get_players_list();
        players_table_view.setItems(final_list);
    }

    @Override
    public void get_selected_table_data(){
        index = players_table_view.getSelectionModel().getSelectedIndex();
        if(index == -1){
            return;
        }else{
            player_name_text.setText(player_name_col.getCellData(index).toString());
            player_goals_text.setText(player_goals_col.getCellData(index).toString());
            player_team_id_text.setText(player_team_id.getCellData(index).toString());

        }


    }

    @Override
    public void Update(ActionEvent event){
        String sql_update = "UPDATE players SET name=?, goals=?, team_id=? WHERE id=?";
        String name = player_name_text.getText();
        String goals = player_goals_text.getText();
        String team_id = player_team_id_text.getText();

        try{
            conn = DB_connection.connection();
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, name);
            pst.setString(2, goals);
            pst.setString(3,team_id);
            pst.setString(4,player_id_col.getCellData(index).toString());
            pst.executeUpdate();
            alerts_and_warning.get_alert("happy","congrats data was edited successfully");
            this.refresh_and_update();
        } catch (Exception e) {
            alerts_and_warning.get_alert("error","editing failed");
            System.out.println(e);
        }
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

    @Override
    public void delete(ActionEvent event){
        String sql_delete= "DELETE from players where id = ?";
        try{
            conn = DB_connection.connection();
            pst = conn.prepareStatement(sql_delete);
            pst.setString(1,player_id_col.getCellData(index).toString());
            pst.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player_id_col.setCellValueFactory(new PropertyValueFactory<players,Integer>("id"));
        player_name_col.setCellValueFactory(new PropertyValueFactory<players,String>("name"));
        player_goals_col.setCellValueFactory(new PropertyValueFactory<players,Integer>("goals"));
        player_team_id.setCellValueFactory(new PropertyValueFactory<players,Integer>("team_id"));


        final_list = get_players_list();

        players_table_view.setItems(final_list);
    }
}
