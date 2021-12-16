package GUI.Controller;


import DAL.db.SongDAO_DB;



import BE.Songs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;


import java.sql.SQLException;

import java.util.ResourceBundle;

public class NewEditSongsController implements Initializable {
    @FXML
    private TextField txt_artist;
    @FXML
    private ComboBox<String> txt_category;
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

        txt_category.setItems(FXCollections.observableArrayList("Pop", "Rock", "Tecno"));

    }

    private void getAllEntries() throws SQLException {
        contactList.setAll(songDAO_db.getAll());
    }


    @FXML
    private void addSongs(ActionEvent actionEvent) {

        String title = txt_title.getText();
        String artist = txt_artist.getText();
        String category = txt_category.getSelectionModel().getSelectedItem();
        String time = txt_time.getText();
        String fileurl = txt_file_url.getText();
        Songs songCreated = new Songs(0, title, artist, category, time, fileurl);
        songDAO_db.addSongs(songCreated);
        Stage stage = (Stage) ClickSave.getScene().getWindow();
        stage.close();


    }


    @FXML
    void clickClose(ActionEvent event) {
        Stage stage = (Stage) CloseBotton.getScene().getWindow();
        stage.close();

    }



    @FXML
    void ChooseLocation(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
                new FileChooser.ExtensionFilter("MP3", "*.mp3"),
                new FileChooser.ExtensionFilter("WAV", "*.wav")

        );


        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            // pickUpPathField it's your TextField fx:id
            txt_file_url.setText(file.getPath());

        } else  {
            System.out.println("error"); // or something else
        }

    }

    @FXML
    private void createCategory(ActionEvent event) throws IOException {
        Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/View/Category.fxml"));
        root1 = (Parent) fxmlLoader.load();
        fxmlLoader.<CategoryController>getController().setController(this); //Sets controler by default for both creating and editing playlists
        Stage stage = new Stage();
        stage.setScene(new Scene(root1, 800, 800));
        stage.centerOnScreen();
        stage.show();
    }

    void updateCategory(String name, boolean isAdding) {
        if (isAdding) {
            txt_category.getItems().add(name); //adds the name to current category box
        } else {
            txt_category.getItems().remove(name); //removes the name from current category box
        }
    }

}
