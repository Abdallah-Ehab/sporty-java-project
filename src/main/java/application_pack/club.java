package application_pack;

public class club {

    private int id;

    private int club_id;

    private String name;

    private int branches;

    private int budget;

    private int stadiums;

    private int championships;

    private String image_path;

    public club(int id, int club_id, String name, int branches, int budget,int stadiums, int championships,String image_path) {
        this.id = id;
        this.club_id = club_id;
        this.name = name;
        this.branches = branches;
        this.budget = budget;
        this.stadiums = stadiums;
        this.championships = championships;
        this.image_path = image_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBranches() {
        return branches;
    }

    public void setBranches(int branches) {
        this.branches = branches;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getChampionships() {
        return championships;
    }

    public void setChampionships(int championships) {
        this.championships = championships;
    }

    public int getStadiums() {
        return stadiums;
    }

    public void setStadiums(int stadiums) {
        this.stadiums = stadiums;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
