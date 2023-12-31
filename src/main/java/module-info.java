module com.example.app2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jackson.databind;
    requires jsoup;


    opens application_pack to javafx.fxml;
    exports application_pack;
}