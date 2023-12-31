package application_pack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class get_lists extends DB_connection {

    private static ObservableList<String> names_list = FXCollections.observableArrayList();

    private static ObservableList<Number> goals_list = FXCollections.observableArrayList();

    private static Connection conn = null;

    private static  PreparedStatement pst = null;

    private static  ResultSet res = null;

    private static ObservableList<club> clubs_list;

    private static ObservableList<teams_table_dashboard_object> teams_dashboard_list;

    private static ObservableList<teams> teams_list;


    private static ObservableList<matches> matches_list;

    private static ObservableList<players> players_list;


    private static ObservableList<stats> stats_list;

    public static ObservableList<club> get_club_list(){
         String sql = "select * from clubs";
        try{
            conn = connection();
            pst = conn.prepareStatement(sql);
            clubs_list = FXCollections.observableArrayList();
            res = pst.executeQuery();
            while(res.next()){
                clubs_list.add(new club(res.getInt("id"),res.getInt("club_id"),res.getString("name"),res.getInt("branches"),res.getInt("budget"),res.getInt("stadiums"),res.getInt("championships"),res.getString("image_path")));
            }
            alerts_and_warning.get_alert("happy","congrats data retrieved from that table");

        }catch(Exception e){
            alerts_and_warning.get_alert("error","too bad I didn't get the data");
            System.out.println(e);
            return null;
        }
        return clubs_list;

    }
    public static ObservableList<teams> get_teams_list(){
        String sql = "select * from teams";
        try{
            conn = connection();
            pst = conn.prepareStatement(sql);
            teams_list = FXCollections.observableArrayList();
            res = pst.executeQuery();
            while(res.next()){
                teams_list.add(new teams(res.getInt("id"), res.getString("sport"), res.getInt("championships"), res.getString("player_of_month"), res.getInt("points"), res.getInt("goals"), res.getInt("cleansheets"), res.getInt("club_id"),res.getString("top_player_image")));
            }
            alerts_and_warning.get_alert("happy","congrats data retrieved from that table");

        }catch(Exception e){
            alerts_and_warning.get_alert("error","too bad I didn't get the data");
            System.out.println(e);
            return null;
        }
        return teams_list;
    }

    public static ObservableList<teams_table_dashboard_object> get_teams_dashboard_list(){

      String sql = "SELECT c.name AS club_name, t.goals,t.cleansheets, t.points FROM teams t JOIN clubs c ON t.club_id = c.id where t.sport = ?  ORDER BY t.points DESC";
        try{
            conn = connection();
            pst = conn.prepareStatement(sql);
            pst.setString(1,club_description_dashboard.get_sport_name());
            teams_dashboard_list = FXCollections.observableArrayList();
            res = pst.executeQuery();
            while(res.next()){
                teams_dashboard_list.add(new teams_table_dashboard_object(res.getString("club_name"),res.getInt("goals"),res.getInt("cleansheets"),res.getInt("points")));
            }
            alerts_and_warning.get_alert("happy","congrats data retrieved from that table");

        }catch(Exception e){
            alerts_and_warning.get_alert("error","too bad I didn't get the data");
            System.out.println(e);
            return null;
        }
        return teams_dashboard_list;
    }
    public static ObservableList<matches> get_matches_list(){
        String sql = "select * from matches";
        try{
            conn = connection();
            pst = conn.prepareStatement(sql);
            matches_list = FXCollections.observableArrayList();
            res = pst.executeQuery();
            while(res.next()){
                matches_list.add(new matches(res.getInt("id"), res.getString("home_team"), res.getString("away_team"), res.getString("championship"),res.getInt("home_team_id"), res.getInt("away_team_id"),res.getInt("home_team_score"),res.getInt("away_team_score"),res.getString("date")));
            }
            alerts_and_warning.get_alert("happy","congrats data retrieved from that table");

        }catch(Exception e){
            alerts_and_warning.get_alert("error","too bad I didn't get the data");
            System.out.println(e);
            return null;
        }
        return matches_list;
    }

    public static ObservableList<players> get_players_list(){
        String sql = "select * from players";
        try{
            conn = connection();
            pst = conn.prepareStatement(sql);
            players_list = FXCollections.observableArrayList();
            res = pst.executeQuery();
            while(res.next()){
                players_list.add(new players(res.getInt("id"), res.getString("name"), res.getInt("goals"), res.getInt("team_id")));
            }
            alerts_and_warning.get_alert("happy","congrats data retrieved from that table");

        }catch(Exception e){
            alerts_and_warning.get_alert("error","too bad I didn't get the data");
            System.out.println(e);
            return null;
        }
        return players_list;
    }

    public static ObservableList<String> get_names_list(){
        String sql = "SELECT p.name FROM players p JOIN teams t ON p.team_id = t.id JOIN clubs c ON t.club_id = c.id WHERE t.sport = ? AND c.id = ? ORDER BY p.goals DESC LIMIT 10";
        try{
            conn = connection();
            pst = conn.prepareStatement(sql);
            names_list = FXCollections.observableArrayList();
            pst.setString(1,club_description_dashboard.get_sport_name());
            pst.setString(2,Integer.toString(clubs_menu_dashboard.get_image_id()));
            res = pst.executeQuery();
            while(res.next()){
                names_list.add(res.getString("name"));
            }
            alerts_and_warning.get_alert("happy","congrats data retrieved from that table");
            System.out.println(names_list);
        }catch(Exception e){
            alerts_and_warning.get_alert("error","too bad I didn't get the data");
            System.out.println(e);
            return null;
        }
        return names_list;
    }
    public static ObservableList<Number> get_goals_list(){
        String sql = "SELECT p.goals FROM players p JOIN teams t ON p.team_id = t.id JOIN clubs c ON t.club_id = c.id WHERE t.sport = ? AND c.id = ? ORDER BY p.goals DESC LIMIT 10";
        try{
            conn = connection();
            pst = conn.prepareStatement(sql);
            names_list = FXCollections.observableArrayList();
            pst.setString(1,club_description_dashboard.get_sport_name());
            pst.setString(2,Integer.toString(clubs_menu_dashboard.get_image_id()));
            res = pst.executeQuery();
            while(res.next()){
                goals_list.add(res.getInt("goals"));
            }
            alerts_and_warning.get_alert("happy","congrats data retrieved from that table");
            System.out.println(goals_list);
        }catch(Exception e){
            alerts_and_warning.get_alert("error","too bad I didn't get the data");
            System.out.println(e);
            return null;
        }
        return goals_list;
    }
    public static ObservableList<stats> get_stats_list(){
        String sql = "select * from stats";
        try{
            conn = connection();
            pst = conn.prepareStatement(sql);
            stats_list = FXCollections.observableArrayList();
            res = pst.executeQuery();
            while(res.next()){
                stats_list.add(new stats(res.getInt("id"), res.getInt("wins"), res.getInt("loses"), res.getInt("draws"),res.getInt("team_id")));
            }
            alerts_and_warning.get_alert("happy","congrats data retrieved from that table");

        }catch(Exception e){
            alerts_and_warning.get_alert("error","too bad I didn't get the data");
            System.out.println(e);
            return null;
        }
        return stats_list;
    }
}


