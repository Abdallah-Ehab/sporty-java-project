package application_pack;

public class query_data {

    private String image_path;

    private int id;

    String club_name;

    public query_data(String image_path,String club_name, int id) {
        this.image_path = image_path;
        this.id = id;
        this.club_name = club_name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }
}
