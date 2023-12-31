package application_pack;

public class stats {

    private int id;

    private int wins;

    private int loses;

    private int draws;


    private int team_id;

    public stats(int id, int wins, int loses, int draws, int team_id) {
        this.id = id;
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
        this.team_id = team_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }
}
