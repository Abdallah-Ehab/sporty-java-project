package application_pack;

public class teams_table_dashboard_object {

    private String club_name;

    private int team_goals;

    private int team_cleansheets;

    private int team_points;

    public teams_table_dashboard_object(String club_name, int team_goals, int team_cleansheets, int team_points) {
        this.club_name = club_name;
        this.team_goals = team_goals;
        this.team_cleansheets = team_cleansheets;
        this.team_points = team_points;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public int getTeam_goals() {
        return team_goals;
    }

    public void setTeam_goals(int team_goals) {
        this.team_goals = team_goals;
    }

    public int getTeam_cleansheets() {
        return team_cleansheets;
    }

    public void setTeam_cleansheets(int team_cleansheets) {
        this.team_cleansheets = team_cleansheets;
    }

    public int getTeam_points() {
        return team_points;
    }

    public void setTeam_points(int team_points) {
        this.team_points = team_points;
    }
}
