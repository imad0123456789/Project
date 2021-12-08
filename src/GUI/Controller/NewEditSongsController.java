package GUI.Controller;


import DAL.db.InsertSongs;
import DAL.db.SongDAO_DB;



import BE.Songs;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.net.URL;


import java.sql.SQLException;

import java.util.List;
import java.util.ResourceBundle;

public class NewEditSongsController implements Initializable {
    @FXML
    private TextField txt_artist;
    @FXML
    private ComboBox<?> txt_category;
    @FXML
    private TextField txt_file_url;
    @FXML
    private TextField txt_time;
    @FXML
    private TextField txt_title;

    @FXML
    private Button ClickMore;
    @FXML
    private Button ClickSave;
    @FXML
    private Button CloseBotton;
    @FXML
    private Button ClikeChoose;





    private SongDAO_DB songDAO_db = new SongDAO_DB();

    private final ObservableList<Songs> contactList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void getAllEntries() throws SQLException {
        contactList.setAll(songDAO_db.getAll());
    }


    @FXML
    public void addSongs(ActionEvent actionEvent) throws SQLException {
    }


    @FXML
    void clickClose(ActionEvent event) {
        Stage stage = (Stage) CloseBotton.getScene().getWindow();
        stage.close();

    }

}
