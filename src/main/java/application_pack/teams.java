package application_pack;

public class teams{
    private int id;

    private String sport;

    private int club_id;
    private int championships;

    private String player_of_month;

    private int points;

    private int goals;

    private int cleansheets;

    private String top_player_image;

    public teams(int id, String sport,int championships,String player_of_month,int points,int goals, int cleansheets,int club_id,String top_player_image) {
        this.id = id;
        this.sport = sport;
        this.club_id = club_id;
        this.championships = championships;
        this.player_of_month = player_of_month;
        this.points = points;
        this.goals = goals;
        this.cleansheets = cleansheets;
        this.top_player_image = top_player_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public int getChampionships() {
        return championships;
    }

    public void setChampionships(int championships) {
        this.championships = championships;
    }

    public String getPlayer_of_month() {
        return player_of_month;
    }

    public void setPlayer_of_month(String player_of_month) {
        this.player_of_month = player_of_month;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getCleansheets() {
        return cleansheets;
    }

    public void setCleansheets(int cleansheets) {
        this.cleansheets = cleansheets;
    }

    public String getTop_player_image() {
        return top_player_image;
    }

    public void setTop_player_image(String top_player_image) {
        this.top_player_image = top_player_image;
    }
}


