package application_pack;

import javafx.scene.control.Alert;

public class alerts_and_warning {

    private static Alert success = new Alert(Alert.AlertType.CONFIRMATION);
    private static Alert warning = new Alert(Alert.AlertType.ERROR);

    public static Alert get_alert(String alert_type,String alert_message){
        if(alert_type.equals("happy")){
            success.setTitle("connection_succeeded");
            success.setContentText(alert_message);
            return success;
        } else if (alert_type.equals("error")) {
            warning.setTitle("connection failed");
            warning.setContentText(alert_message);
            return warning;
        }
        return null;
    }
}
