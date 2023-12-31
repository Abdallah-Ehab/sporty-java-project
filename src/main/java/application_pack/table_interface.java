package application_pack;

import javafx.event.ActionEvent;

import java.io.IOException;

public interface table_interface {
    public void Update(ActionEvent event);

    public void get_selected_table_data();

    public void add(ActionEvent event) throws IOException;

    public void delete(ActionEvent event);

    public void refresh_and_update();


}
