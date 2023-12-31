package application_pack;

public class matches {
    private int id;
    private String home_team;
    private String away_team;
    private String championship;
    private int home_team_id;
    private int away_team_id;

    private int home_team_score;

    private int away_team_score;

    private String date;

    public matches(int id, String home_team, String away_team, String championship, int home_team_id, int away_team_id,int home_team_score,int away_team_score,String date) {
        this.id = id;
        this.home_team = home_team;
        this.away_team = away_team;
        this.championship = championship;
        this.home_team_id = home_team_id;
        this.away_team_id = away_team_id;
        this.home_team_score = home_team_score;
        this.away_team_score = away_team_score;
        this.date =date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHome_team() {
        return home_team;
    }

    public void setHome_team(String home_team) {
        this.home_team = home_team;
    }

    public String getAway_team() {
        return away_team;
    }

    public void setAway_team(String away_team) {
        this.away_team = away_team;
    }


    public String getChampionship() {
        return championship;
    }

    public void setChampionship(String championship) {
        this.championship = championship;
    }

    public int getHome_team_id() {
        return home_team_id;
    }

    public void setHome_team_id(int home_team_id) {
        this.home_team_id = home_team_id;
    }

    public int getAway_team_id() {
        return away_team_id;
    }

    public void setAway_team_id(int away_team_id) {
        this.away_team_id = away_team_id;
    }

    public int getHome_team_score() {
        return home_team_score;
    }

    public void setHome_team_score(int home_team_score) {
        this.home_team_score = home_team_score;
    }

    public int getAway_team_score() {
        return away_team_score;
    }

    public void setAway_team_score(int away_team_score) {
        this.away_team_score = away_team_score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
