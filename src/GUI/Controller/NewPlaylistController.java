package GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewPlaylistController {

    @FXML
    private Button CloseBotton;


    @FXML
    void clickClose(ActionEvent event) {
        Stage stage = (Stage) CloseBotton.getScene().getWindow();
        stage.close();

    }
}
