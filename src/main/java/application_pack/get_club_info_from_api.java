package application_pack;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class get_club_info_from_api extends clubs_menu_dashboard {

    private static String description = "";

    public static String get_club_info_from_json(){
        String club_name = get_clicked_club_name().toLowerCase(Locale.ROOT).replaceAll("[\\s\\-_!?~]", "");
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/dependencies/clubs.json");

            JsonNode root_node = mapper.readTree(file);

            JsonNode clubs = root_node.get("clubs");

            for(JsonNode node : clubs){
                String name = node.get("name").asText();
                if(name.equals(club_name)){
                    description = node.get("description").asText();
                    break;
                }else{
                    return null;
                }
            }
            return description;

        }catch (IOException e){
                System.out.println(e);
                return null;
        }

    }

    public static String get_club_info_from_wiki(){

        String club_name = get_clicked_club_name().toLowerCase(Locale.ROOT).replaceAll("[\\s\\-_!?~]", "")+" fc";
        String url = "https://en.wikipedia.org/wiki/"+club_name;

        try{
            Document document = Jsoup.connect(url).get();

            Elements paragraphs = document.select("p");

            for(Element paragraph:paragraphs){
                description+=paragraph.text()+"\n";

            }
            return description;

        }catch (IOException e){
            System.out.println(e);
        }
        return null;
    }
}
