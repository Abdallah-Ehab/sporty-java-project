package application_pack;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class dashboard extends club_description_dashboard implements Initializable {
    @FXML
    private CategoryAxis horisontal_axis;

    @FXML
    private BarChart<String, Number> top_10_barchart;

    @FXML
    private NumberAxis vertical_axis;

    @FXML
    private PieChart pie_chart_id;

    @FXML
    private ObservableList<teams_table_dashboard_object> final_list;

    @FXML
    private TableView<teams_table_dashboard_object> dash_board_table_view;

    @FXML
    private TableColumn<teams_table_dashboard_object, Integer> dashboard_cleansheet_col;

    @FXML
    private TableColumn<teams_table_dashboard_object,Integer> dashboard_goals_col;

    @FXML
    private TableColumn<teams_table_dashboard_object,String> dashboard_name_col;

    @FXML
    private TableColumn<teams_table_dashboard_object,Integer> dashboard_points_col;

    @FXML
    private ImageView player_image;

    @FXML
    private ImageView away_team_image;

    @FXML
    private Text away_team_text;

    @FXML
    private ImageView home_team_image;

    @FXML
    private Text home_team_text;



    private ObservableList<String> names_list = FXCollections.observableArrayList();
    private ObservableList<Number> goals_list = FXCollections.observableArrayList();

    private String home_team_score;
    private String away_team_score;
    private String home_team;
    private String away_team;



    ObservableList<PieChart.Data> pieChartData;

    Connection conn = null;

    PreparedStatement pst = null;

    ResultSet res = null;

    //add data to the name list and the goal list

    //make the bar char
    public void make_bar_chart(ObservableList<String> names_list,ObservableList<Number> goals_list) {


        //create series
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        // Add player names and goals as data to the series
        for (int i = 0; i < names_list.size(); i++) {
            //create a data object
            XYChart.Data<String, Number> data = new XYChart.Data<>(names_list.get(i), goals_list.get(i));
            series.getData().add(data);
        }

        // Clear existing data from the bar chart
        top_10_barchart.getData().clear();

        // Add the series to the bar chart
        top_10_barchart.getData().add(series);

        // Set the label for the x-axis
        horisontal_axis.setLabel("Player Names");

        // Set the label for the y-axis
        vertical_axis.setLabel("Goals");
    }



    //make the piechart
    public void make_pie_chart(){
        String sql_pie_chart = "SELECT SUM(wins) AS total_wins, SUM(loses) AS total_loses, SUM(draws) AS total_draws FROM stats INNER JOIN teams ON stats.team_id = teams.id WHERE teams.club_id = ? and sport = ?";
        try {
            conn = DB_connection.connection();
            pst = conn.prepareStatement(sql_pie_chart);
            pst.setString(1,Integer.toString(clubs_menu_dashboard.get_image_id()));
            pst.setString(2,get_sport_name());
            res = pst.executeQuery();
            while(res.next()){
                int wins = res.getInt("total_wins");
                int loses = res.getInt("total_loses");
                int draws = res.getInt("total_draws");
                int sum = (wins+loses+draws);
                int wins_percentage = (int) (((double) wins / sum) * 100);
                int draws_percentage = (int) (((double) draws / sum) * 100);
                int loses_percentage = (int) (((double) loses / sum) * 100);

                PieChart.Data winsData = new PieChart.Data("Wins", wins_percentage);
                PieChart.Data drawsData = new PieChart.Data("Draws", draws_percentage);
                PieChart.Data losesData = new PieChart.Data("Losses", loses_percentage);

                pieChartData = FXCollections.observableArrayList(winsData,drawsData,losesData);

                winsData.setName("Wins: " + wins_percentage + "%");
                drawsData.setName("Draws: " + draws_percentage + "%");
                losesData.setName("Losses: " + loses_percentage + "%");

            }

        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                if (res != null) res.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        pie_chart_id.setData(pieChartData);
    }

    public void make_matches_board_text(){
        String sql_get_team_id = "select m.home_team,m.away_team,m.home_team_score,m.away_team_score from matches m where home_team = ? or away_team = ? order by m.date limit 1";
        try{
            conn = DB_connection.connection();
            pst = conn.prepareStatement(sql_get_team_id);
            pst.setString(1,clubs_menu_dashboard.get_clicked_club_name());
            pst.setString(2,clubs_menu_dashboard.get_clicked_club_name());
            res = pst.executeQuery();
            while(res.next()){
                home_team_score = res.getString("home_team_score");
                away_team_score= res.getString("away_team_score");
                home_team = res.getString("home_team");
                away_team = res.getString("away_team");
            }
            home_team_text.setText(home_team_score);
            away_team_text.setText(away_team_score);

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void get_home_team_image(){


            String sql_get_home_team_image = "select c.image_path from clubs c where c.name = ?";


            try {
                conn = DB_connection.connection();

                // Query 1: Get home team image
                pst = conn.prepareStatement(sql_get_home_team_image);
                pst.setString(1, home_team);
                res = pst.executeQuery();

                while (res.next()) {
                    String image_path = res.getString("image_path");
                    if(image_path != null){
                    Image image = new Image(res.getString("image_path"));
                    home_team_image.setImage(image);
                    }else{
                        URL file_url = new URL("file:"+"src/main/logos/dummy_logo.png");
                        Image image = new Image(String.valueOf(file_url));
                        home_team_image.setImage(image);
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
    }

    public void get_away_team_image(){
        String sql_get_away_team_image = "select c.image_path from clubs c where c.name = ?";
        try {
            conn = DB_connection.connection();

            // Query 1: Get home team image
            pst = conn.prepareStatement(sql_get_away_team_image);
            pst.setString(1, away_team);
            res = pst.executeQuery();

            while (res.next()) {
                String image_path = res.getString("image_path");
                if(image_path != null){
                    Image image = new Image(res.getString("image_path"));
                    away_team_image.setImage(image);
                }else{
                    URL file_url = new URL("file:"+"src/main/logos/dummy_logo.png");
                    Image image = new Image(String.valueOf(file_url));
                    away_team_image.setImage(image);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }




    public void add_top_player_image(){
        String select_image = "select t.top_player_image from teams t join clubs c on t.club_id = c.id where c.id = ? and sport = ?";
        try{
        conn = DB_connection.connection();
        pst = conn.prepareStatement(select_image);
        pst.setInt(1,clubs_menu_dashboard.get_image_id());
        pst.setString(2,club_description_dashboard.get_sport_name());
        res = pst.executeQuery();
        while(res.next()){
            String image_path = res.getString("top_player_image");
            if(image_path!= null){

            Image image = new Image(image_path);
            player_image.setImage(image);

            } else{
                URL file_url = new URL("file:"+"src/main/logos/no_player.png");
            Image image = new Image(String.valueOf(file_url));
            player_image.setImage(image);
        }

        }
        }catch(Exception e){
            System.out.println(e);
    }
    }

    @FXML
    void back_to_description(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("club_description_dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("club_description");
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
        System.out.println(names_list);
        dashboard_name_col.setCellValueFactory(new PropertyValueFactory<teams_table_dashboard_object,String>("club_name"));
        dashboard_goals_col.setCellValueFactory(new PropertyValueFactory<teams_table_dashboard_object,Integer>("team_goals"));
        dashboard_cleansheet_col.setCellValueFactory(new PropertyValueFactory<teams_table_dashboard_object,Integer>("team_cleansheets"));
        dashboard_points_col.setCellValueFactory(new PropertyValueFactory<teams_table_dashboard_object,Integer>("team_points"));

        final_list = get_lists.get_teams_dashboard_list();

        names_list = get_lists.get_names_list();

        goals_list = get_lists.get_goals_list();


        dash_board_table_view.setItems(final_list);

        make_matches_board_text();

        make_pie_chart();

        make_bar_chart(names_list,goals_list);

        add_top_player_image();

        get_home_team_image();
        get_away_team_image();
    }
}
