package application_pack;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import org.w3c.dom.events.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.ResourceBundle;

public class ClubsTable extends get_lists implements Initializable,table_interface {

    @FXML
    private TableColumn<club, Integer> club_branches_col;

    @FXML
    private TableColumn<club, Integer> club_budget_col;

    @FXML
    private TableColumn<club, Integer> club_championships_col;

    @FXML
    private TableColumn<club, Integer> club_id_col;

    @FXML
    private TableColumn<club,String> club_name_col;

    @FXML
    private TableColumn<club, Integer> club_stadiums_col;

    @FXML
    private TableColumn<club,Integer> id_col;

    @FXML
    private TableColumn<club,String> image_path_col;


    @FXML
    private TableView<club> table_view_id;

    @FXML
    private TextField club_branches_text;

    @FXML
    private TextField club_budget_text;

    @FXML
    private TextField club_championships_text;

    @FXML
    private TextField club_id_text;

    @FXML
    private TextField club_name_text;

    @FXML
    private TextField club_stadium_text;

    @FXML
    private Button ADD_btn;

    @FXML
    private Button Update_btn;

    @FXML
    private Button upload_image_btn;

    private ObservableList<club> final_list;

    Connection conn = null;

    PreparedStatement pst = null;

    private int index = -1;

    FileChooser fileChooser = new FileChooser();

    private String filePath;

    private String fileUrl;


    String sql ="insert into clubs(club_id, name, branches, budget, stadiums, championships,image_path) values(?,?,?,?,?,?,?)";

    @Override
    public void add(ActionEvent event){
        String club_id = club_id_text.getText();
        String name = club_name_text.getText();
        String branches = club_branches_text.getText();
        String budget = club_budget_text.getText();
        String stadiums = club_stadium_text.getText();
        String championships = club_championships_text.getText();
        String image_path = fileUrl;
        add_to_db add = new add_to_db();
        add.add_to_clubs_table(sql,club_id,name,branches,budget,stadiums,championships,image_path);
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
    public void get_selected_table_data(){
        index = table_view_id.getSelectionModel().getSelectedIndex();
        if(index == -1){
            return;
        }else{
            club_id_text.setText(club_id_col.getCellData(index).toString());
            club_name_text.setText(club_name_col.getCellData(index).toString());
            club_branches_text.setText(club_branches_col.getCellData(index).toString());
            club_budget_text.setText(club_budget_col.getCellData(index).toString());
            club_stadium_text.setText(club_stadiums_col.getCellData(index).toString());
            club_championships_text.setText(club_championships_col.getCellData(index).toString());
        }


    }

    @FXML
    public void Update(ActionEvent event){
        String id = club_id_text.getText();
        String name = club_name_text.getText();
        String branches = club_branches_text.getText();
        String budget = club_budget_text.getText();
        String stadiums = club_stadium_text.getText();
        String championships = club_championships_text.getText();
        try{
        conn = DB_connection.connection();
        String sql_update = "update clubs set club_id=?,name=?,branches=?,budget=?,stadiums = ?, championships= ? ,image_path= ? where club_id=?";
        pst = conn.prepareStatement(sql_update);
        pst.setString(1,club_id_text.getText());
        pst.setString(2,club_name_text.getText());
        pst.setString(3,club_branches_text.getText());
        pst.setString(4,club_budget_text.getText());
        pst.setString(5,club_stadium_text.getText());
        pst.setString(6,club_championships_text.getText());
        pst.setString(7,fileUrl);
        pst.setString(8,club_id_col.getCellData(index).toString());
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

        String sql_delete_club = "DELETE FROM clubs WHERE id = ?";

        try {
            conn = DB_connection.connection();

            // Delete the clubs first
            pst = conn.prepareStatement(sql_delete_club);
            pst.setString(1, id_col.getCellData(index).toString());
            pst.executeUpdate();
            this.refresh_and_update();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void refresh_and_update(){
        final_list.clear();
        final_list= get_club_list();
        table_view_id.setItems(final_list);
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_col.setCellValueFactory(new PropertyValueFactory<club,Integer>("id"));
        club_id_col.setCellValueFactory(new PropertyValueFactory<club,Integer>("club_id"));
        club_name_col.setCellValueFactory(new PropertyValueFactory<club,String>("name"));
        club_branches_col.setCellValueFactory(new PropertyValueFactory<club,Integer>("branches"));
        club_budget_col.setCellValueFactory(new PropertyValueFactory<club,Integer>("budget"));
        club_stadiums_col.setCellValueFactory(new PropertyValueFactory<club,Integer>("stadiums"));
        club_championships_col.setCellValueFactory(new PropertyValueFactory<club,Integer>("championships"));
        image_path_col.setCellValueFactory(new PropertyValueFactory<club,String>("image_path"));
        final_list = get_club_list();

        table_view_id.setItems(final_list);





    }
}
