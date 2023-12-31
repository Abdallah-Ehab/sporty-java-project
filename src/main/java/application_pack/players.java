package application_pack;

public class players {

    private int id;

    private String name;

    private int goals;

    private int team_id;

    public players(int id,String name, int goals, int team_id) {
        this.name = name;
        this.goals = goals;
        this.team_id = team_id;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
