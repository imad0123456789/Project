package GUI.Controller;

import BE.Playlist;
import BE.Songs;
import DAL.db.PlayListDAO;
import DAL.db.SongDAO_DB;
import GUI.model.PlaylistModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.namespace.QName;

public class NewPlaylistController {

    @FXML
    private Button CloseBotton;

    @FXML
    private Button ClikeSave;
    @FXML
    private TextField Txt_name;

    //private SongDAO_DB songDAO_db = new SongDAO_DB();
    private PlayListDAO playListDAO = new PlayListDAO();
    private PlaylistModel playlistModel;
    private boolean isEditing = false;
    private Playlist editingList;

    @FXML
    void clickClose(ActionEvent event) {
        Stage stage = (Stage) CloseBotton.getScene().getWindow();
        stage.close();

    }



    @FXML
    void addNewPlaylist(ActionEvent event) {
        String name = Txt_name.getText();
        Playlist npl = new Playlist(0, name);
        playListDAO.addPlaylist(npl);
        Stage stage = (Stage) ClikeSave.getScene().getWindow();
        stage.close();
    }

    void setInfo(Playlist selectedItem) {
        isEditing = true;
        editingList = selectedItem;
        Txt_name.setText(selectedItem.getName());
    }



}
