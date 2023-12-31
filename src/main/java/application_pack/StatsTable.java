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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StatsTable extends get_lists implements Initializable,table_interface  {

    @FXML
    private Button ADD_btn;

    @FXML
    private Button Update_btn;

    @FXML
    private Button back_btn;

    @FXML
    private Button delete_btn;

    @FXML
    private TextField draws_text;

    @FXML
    private TextField loses_text;

    @FXML
    private TableColumn<stats,Integer> stats_draws_col;

    @FXML
    private TableColumn<stats,Integer> stats_id_col;

    @FXML
    private TableColumn<stats,Integer> stats_loses_col;

    @FXML
    private TableView<stats> stats_table_view;

    @FXML
    private TableColumn<stats,Integer> stats_team_id_col;

    @FXML
    private TableColumn<stats,Integer> stats_wins_col;

    @FXML
    private TextField teams_id_text;

    @FXML
    private TextField wins_text;

    private int index = -1;

    Connection conn = null;

    PreparedStatement pst = null;

    ResultSet res = null;

    add_to_db add = new add_to_db();

    private ObservableList<stats> final_list;


    @Override
    public void get_selected_table_data(){
        index = stats_table_view.getSelectionModel().getSelectedIndex();
        if(index == -1){
            return;
        }else{
            wins_text.setText(stats_wins_col.getCellData(index).toString());
            draws_text.setText(stats_draws_col.getCellData(index).toString());
            loses_text.setText(stats_loses_col.getCellData(index).toString());
            teams_id_text.setText(stats_team_id_col.getCellData(index).toString());
        }

    }
    @Override
    public void Update(ActionEvent event){
        String sql_update = "UPDATE stats SET wins=?, loses=?, draws=?,team_id=? WHERE id=?";
        String wins = wins_text.getText();
        String loses = loses_text.getText();
        String draws = draws_text.getText();
        String team_id = teams_id_text.getText();

        try{
            conn = DB_connection.connection();
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, wins);
            pst.setString(2, loses);
            pst.setString(3,draws);
            pst.setString(4,team_id);
            pst.setString(5,stats_id_col.getCellData(index).toString());
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
        String sql_delete= "DELETE from stats where id = ?";
        try{
            conn = DB_connection.connection();
            pst = conn.prepareStatement(sql_delete);
            pst.setString(1,stats_id_col.getCellData(index).toString());
            pst.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(ActionEvent event){
        String sql_add ="insert into stats(wins, loses, draws,team_id) values(?,?,?,?)";
        String wins = wins_text.getText();
        String loses = loses_text.getText();
        String draws = draws_text.getText();
        String team_id = teams_id_text.getText();
        add.add_to_stats_table(sql_add,wins,loses,draws,team_id);

        this.refresh_and_update();
    }
    public void refresh_and_update(){
        final_list.clear();
        final_list= get_stats_list();
        stats_table_view.setItems(final_list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stats_id_col.setCellValueFactory(new PropertyValueFactory<stats,Integer>("id"));
        stats_wins_col.setCellValueFactory(new PropertyValueFactory<stats,Integer>("wins"));
        stats_loses_col.setCellValueFactory(new PropertyValueFactory<stats,Integer>("loses"));
        stats_draws_col.setCellValueFactory(new PropertyValueFactory<stats,Integer>("draws"));
        stats_team_id_col.setCellValueFactory(new PropertyValueFactory<stats,Integer>("team_id"));

        final_list = get_stats_list();

        stats_table_view.setItems(final_list);
    }
}
