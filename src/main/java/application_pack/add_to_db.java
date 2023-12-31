package application_pack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class add_to_db extends DB_connection {


    private Connection conn = null;

    private PreparedStatement pst = null;


    public void add_to_clubs_table(String sql,String club_id,String name,String branches,String budget,String stadiums,String championships,String image_path){
           conn = connection();
       try{
           pst = conn.prepareStatement(sql);

           pst.setInt(1,Integer.parseInt(club_id));
           pst.setString(2,name);
           pst.setInt(3,Integer.parseInt(branches));
           pst.setInt(4,Integer.parseInt(budget));
           pst.setInt(5,Integer.parseInt(stadiums));
           pst.setInt(6,Integer.parseInt(championships));
           pst.setString(7,image_path);

           pst.executeUpdate();

           alerts_and_warning.get_alert("happy","data added successfully");


       }catch (Exception e){
           System.out.println(e);
           alerts_and_warning.get_alert("error","data failed to be added");
       }


    }
    public void add_to_teams_table(String sql,String sport,String championships,String player_of_month,String points,String goals, String cleansheets,String club_id,String top_player_image){
        conn = connection();
        try{
            pst = conn.prepareStatement(sql);

            pst.setString(1,sport);
            pst.setInt(2,Integer.parseInt(championships));
            pst.setString(3,player_of_month);
            pst.setInt(4,Integer.parseInt(points));
            pst.setInt(5,Integer.parseInt(goals));
            pst.setInt(6,Integer.parseInt(cleansheets));
            pst.setInt(6,Integer.parseInt(club_id));
            pst.setString(7,top_player_image);

            pst.executeUpdate();

            alerts_and_warning.get_alert("happy","data added successfully");


        }catch (Exception e){
            System.out.println(e);
            alerts_and_warning.get_alert("error","data failed to be added");
        }


    }
    public void add_to_matches(String sql,String home_team,String away_team,String championship,String home_team_id,String away_team_id,String home_team_score,String away_team_score){
        conn = connection();
        try{
            pst = conn.prepareStatement(sql);

            pst.setString(1,home_team);
            pst.setString(2,away_team);
            pst.setString(3,championship);
            pst.setInt(4,Integer.parseInt(home_team_id));
            pst.setInt(5,Integer.parseInt(away_team_id));
            pst.setInt(6,Integer.parseInt(home_team_score));
            pst.setInt(7,Integer.parseInt(away_team_score));

            pst.executeUpdate();

            alerts_and_warning.get_alert("happy","data added successfully");


        }catch (Exception e){
            System.out.println(e);
            alerts_and_warning.get_alert("error","data failed to be added");
        }


    }
    public void add_to_players_table(String sql,String name,String goals,String team_id){
        conn = connection();
        try{
            pst = conn.prepareStatement(sql);

            pst.setString(1,name);
            pst.setInt(2,Integer.parseInt(goals));
            pst.setInt(3,Integer.parseInt(team_id));

            pst.executeUpdate();

            alerts_and_warning.get_alert("happy","data added successfully");


        }catch (Exception e){
            System.out.println(e);
            alerts_and_warning.get_alert("error","data failed to be added");
        }
    }

    public void add_to_stats_table(String sql,String wins,String loses,String draws,String team_id){
        conn = connection();
        try{
            pst = conn.prepareStatement(sql);

            pst.setInt(1,Integer.parseInt(wins));
            pst.setInt(2,Integer.parseInt(loses));
            pst.setInt(3,Integer.parseInt(draws));
            pst.setInt(4,Integer.parseInt(team_id));

            pst.executeUpdate();

            alerts_and_warning.get_alert("happy","data added successfully");


        }catch (Exception e){
            System.out.println(e);
            alerts_and_warning.get_alert("error","data failed to be added");
        }
    }

}
