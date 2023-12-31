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

public class MatchesTable extends get_lists implements Initializable,table_interface {

    ObservableList<matches> final_list;

    add_to_db add = new add_to_db();

    int index = -1;

    Connection conn = null;

    PreparedStatement pst = null;

    @FXML
    private TableColumn<matches,String> away_team_col;



    @FXML
    private TableColumn<matches,Integer> away_team_id_col;

    @FXML
    private TextField away_team_id_text;

    @FXML
    private TableColumn<matches,Integer> away_team_score_col;

    @FXML
    private TextField away_team_score_text;

    @FXML
    private TextField away_team_text;

    @FXML
    private TextField championship_text;

    @FXML
    private TableColumn<matches,String> home_team_col;

    @FXML
    private TableColumn<matches,Integer> home_team_id_col;

    @FXML
    private TextField home_team_id_text;

    @FXML
    private TableColumn<matches,Integer> home_team_score_col;

    @FXML
    private TextField home_team_score_text;

    @FXML
    private TextField home_team_text;

    @FXML
    private TableColumn<matches,String> match_championship_col;

    @FXML
    private TableColumn<matches,String> date_col;


    @FXML
    private Button matches_add_btn;

    @FXML
    private Button matches_del_btn;

    @FXML
    private TableView<matches> matches_table_view;

    @FXML
    private Button matches_update_button;

    @FXML
    private TableColumn<matches,Integer> matches_id_col;

    @FXML
    private TextField date_text;


    @Override
    public void add(ActionEvent event){
        String sql_add = "insert into matches(home_team, away_team, championship, home_team_id, away_team_id, home_team_score, away_team_score)";
        String home_team = home_team_text.getText();
        String away_team = away_team_text.getText();
        String championship= championship_text.getText();
        String home_team_id = home_team_id_text.getText();
        String away_team_id = away_team_id_text.getText();
        String home_team_score = home_team_score_text.getText();
        String away_team_score = away_team_score_text.getText();
        add.add_to_matches(sql_add,home_team,away_team,championship,home_team_id,away_team_id,home_team_score,away_team_score);
        this.refresh_and_update();

    }

    public void get_selected_table_data(){
        index = matches_table_view.getSelectionModel().getSelectedIndex();
        if(index == -1){
            return;
        }else{
            home_team_text.setText(home_team_col.getCellData(index).toString());
            away_team_text.setText(away_team_col.getCellData(index).toString());
            championship_text.setText(match_championship_col.getCellData(index).toString());
            home_team_id_text.setText(home_team_id_col.getCellData(index).toString());
            away_team_id_text.setText(away_team_col.getCellData(index).toString());
            home_team_score_text.setText(home_team_score_col.getCellData(index).toString());
            away_team_score_text.setText(away_team_score_col.getCellData(index).toString());
            date_text.setText(date_col.getCellData(index).toString());
        }
    }

    @Override
    public void delete(ActionEvent event){
        String sql_delete = "DELETE FROM matches WHERE id = ?";
        try{
        conn = DB_connection.connection();
        pst = conn.prepareStatement(sql_delete);
        pst.setString(1,Integer.toString(matches_table_view.getSelectionModel().getSelectedIndex()));
        pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void Update(ActionEvent event){
        String home_team = home_team_text.getText();
        String away_team = away_team_text.getText();
        String championship= championship_text.getText();
        String home_team_id = home_team_id_text.getText();
        String away_team_id = away_team_id_text.getText();
        String home_team_score = home_team_score_text.getText();
        String away_team_score = away_team_score_text.getText();
        String date = date_text.getText();

        try{
            conn = DB_connection.connection();
            String sql_update = "UPDATE matches SET home_team=?, away_team=?, championship=?, home_team_id=?, away_team_id=?, home_team_score=?, away_team_score=?,date=? WHERE id=?";
            pst = conn.prepareStatement(sql_update);
            pst.setString(1, home_team);
            pst.setString(2, away_team);
            pst.setString(3, championship);
            pst.setString(4, home_team_id);
            pst.setString(5, away_team_id);
            pst.setString(6, home_team_score);
            pst.setString(7, away_team_score);
            pst.setString(8,date);
            pst.setString(9,matches_id_col.getCellData(index).toString());
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

    public void refresh_and_update(){
        final_list.clear();
        final_list=get_matches_list();
        matches_table_view.setItems(final_list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        matches_id_col.setCellValueFactory(new PropertyValueFactory<matches,Integer>("id"));
        home_team_col.setCellValueFactory(new PropertyValueFactory<matches,String>("home_team"));
        away_team_col.setCellValueFactory(new PropertyValueFactory<matches,String>("away_team"));
        match_championship_col.setCellValueFactory(new PropertyValueFactory<matches,String>("championship"));
        home_team_id_col.setCellValueFactory(new PropertyValueFactory<matches,Integer>("home_team_id"));
        away_team_id_col.setCellValueFactory(new PropertyValueFactory<matches,Integer>("away_team_id"));
        home_team_score_col.setCellValueFactory(new PropertyValueFactory<matches,Integer>("home_team_score"));
        away_team_score_col.setCellValueFactory(new PropertyValueFactory<matches,Integer>("away_team_score"));
        date_col.setCellValueFactory(new PropertyValueFactory<matches,String>("date"));

        final_list = get_matches_list();

        matches_table_view.setItems(final_list);
    }
}
