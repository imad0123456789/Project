package GUI.Controller;


import BE.Playlist;
import BE.Songs;
import DAL.db.PlayListDAO;
import DAL.db.SongDAO_DB;
import GUI.model.PlaylistModel;
import GUI.model.SongModel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    private final PlaylistModel playlistModel;
    private final SongModel songModel;
    private ObservableList<Playlist> observableListPlay;
    private ObservableList<Songs> observableListSong;


    @FXML
    private AnchorPane MainborderPane;
    @FXML
    private TableView<Songs> SongsTabelView;
    @FXML
    private TableColumn<Songs, String> col_Artist;
    @FXML
    private TableColumn<Songs, String> col_Category;
    @FXML
    private TableColumn<Songs, String> col_Title;
    @FXML
    private TableColumn<Songs, String> col_Time;
    @FXML
    private Button CloseBotton;

    @FXML
    private Button NewBot;
    @FXML
    private Button NewListBt;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label SongPlayLabel;

    @FXML
    private TableView<Playlist> PlaylistTabelView;
    @FXML
    private TableColumn<Playlist, String> col_name;
    @FXML
    private TableColumn<?, ?> col_totalSong;

    @FXML
    private TableColumn<?, ?> col_totalTime;
    @FXML
    private Button playSong;
    @FXML
    private Label currentSong;
    @FXML
    private TextField Txt_search;

    @FXML
    private TableView<Songs> songsInPlaylist;
    @FXML
    private TableColumn<Songs, String> songsInPlaylistName;
    @FXML
    private TableColumn<Songs, Integer> songInPlaylistID;


    private SongDAO_DB songDAO_db = new SongDAO_DB();
    private PlayListDAO playListDAO = new PlayListDAO();
    private ActionEvent actionEvent;

    private MediaPlayer mediaPlayer;
    private int currentSongPlaying = 0;


    public MainController() throws IOException {
        playlistModel = new PlaylistModel();
        songModel = new SongModel();
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            observableListPlay = playlistModel.getPlaylists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            observableListSong = songModel.getSongs();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        col_Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        col_Artist.setCellValueFactory(new PropertyValueFactory<>("Artist"));
        col_Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        col_Time.setCellValueFactory(new PropertyValueFactory<>("time"));
        SongsTabelView.setItems(observableListSong);

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_totalSong.setCellValueFactory(new PropertyValueFactory<>("songCount"));
        col_totalTime.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
        PlaylistTabelView.setItems(observableListPlay);


        songInPlaylistID.setCellValueFactory(new PropertyValueFactory<>("id"));
        songsInPlaylistName.setCellValueFactory(new PropertyValueFactory<>("Title"));


    }


    private void addSongToTableView(Songs song) {
        songInPlaylistID.setCellValueFactory(new PropertyValueFactory<>("id"));
        songsInPlaylistName.setCellValueFactory(new PropertyValueFactory<>("Title"));

        songsInPlaylist.getItems().add(song);
    }

    @FXML
    void clickNew(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/View/NewEditSongsVeiw.fxml"));
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickClose(ActionEvent event) throws IOException {

        Stage stage = (Stage) CloseBotton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void clickNewlist(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/View/PlaylistView.fxml"));
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        refreshPlayList(false);
    }


    @FXML
    public void audioPlayerButtons(ActionEvent actionEvent) {

        if (mediaPlayer == null && songsInPlaylist.getSelectionModel().getSelectedIndex() != -1) {
            currentSongPlaying = songsInPlaylist.getSelectionModel().getSelectedIndex();
            play();
            volumeSlider.setValue(mediaPlayer.getVolume() * 50);
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 50);
                }
            });

        } else {
            currentSong.setText(" no song is playing");
            playSong.setText("▶");
            stopMediaPlayer();
            mediaPlayer = null;
        }


    }


    @FXML
    void editSong(ActionEvent event) throws IOException {

    }


    @FXML
    void deleteSong(ActionEvent event) {
        if (SongsTabelView.getSelectionModel().getSelectedIndex() != -1) {
            stopMediaPlayer();
            songModel.deleteSong(SongsTabelView.getSelectionModel().getSelectedItem());
            refreshSongList(false);
            songsInPlaylist.getItems().clear();
            refreshList();
        }

    }

    @FXML
    void deletePlaylist(ActionEvent event) {
        if (PlaylistTabelView.getSelectionModel().getSelectedIndex() != -1) {
            playlistModel.deletePlaylist(PlaylistTabelView.getSelectionModel().getSelectedItem());
            refreshPlayList(false);
            songsInPlaylist.getItems().clear();
            refreshList();
        }

    }

    private void refreshList() {
        PlaylistTabelView.getItems().clear();
        try {
            PlaylistTabelView.setItems(playlistModel.getPlaylists());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void refreshSongList(boolean isEditing) {
        SongsTabelView.getItems().clear();
        try {
            SongsTabelView.setItems(songModel.getSongs());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (isEditing) {
            songsInPlaylist.getItems().clear();
            refreshList();
        }
    }

    private void refreshPlayList(boolean isEditing) {
        PlaylistTabelView.getItems().clear();
        try {
            PlaylistTabelView.setItems(playlistModel.getPlaylists());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (isEditing) {
            songsInPlaylist.getItems().clear();
            refreshList();
        }
    }


    @FXML
    void ClickAddSong(ActionEvent event) {
        if (PlaylistTabelView.getSelectionModel().getSelectedIndex() != -1 && SongsTabelView.getSelectionModel().getSelectedIndex() != -1) {
            stopMediaPlayer();
            // addSongToTableView(SongsTabelView.getSelectionModel().getSelectedItem());
            playlistModel.addToPlaylist(PlaylistTabelView.getSelectionModel().getSelectedItem(),
                    SongsTabelView.getSelectionModel().getSelectedItem());
            refreshPlaylistSongs(SongsTabelView.getItems().size(), SongsTabelView.getSelectionModel().getSelectedItem(), true);

        }

    }


    private void refreshPlaylistSongs(int songToModifyIndex, Songs songToModify, boolean isAdding) {
        if (isAdding) { // If the user is adding a song
            songToModify.setId(songsInPlaylist.getItems().size() + 1);
            System.out.println("song to modify: " + songToModify);
            songsInPlaylist.getItems().add(songToModify);
        } else {
            int tomodify = songsInPlaylist.getSelectionModel().getSelectedItem().getId();
            for (int z = tomodify; z < songsInPlaylist.getItems().size(); z++) {
            }
            songsInPlaylist.getItems().remove(songToModifyIndex);

        }
    }


    @FXML
    void displaySongsInPlaylist(MouseEvent event) {
        stopMediaPlayer();
        songsInPlaylist.getItems().clear();
        List<Songs> toBeAddedSongList = PlaylistTabelView.getSelectionModel().getSelectedItem().getSongList();
        /*for (int x = toBeAddedSongList.size() -1; x >= 0; x--){
            //toBeAddedSongList.get(x).setId(toBeAddedSongList.size()-x);
            songInPlaylistID.setCellValueFactory(new PropertyValueFactory<>("id"));
            songsInPlaylistName.setCellValueFactory(new PropertyValueFactory<>("Title"));
            songsInPlaylist.getItems().add(toBeAddedSongList.get(x));
        }*/
        for (Songs s : PlaylistTabelView.getSelectionModel().getSelectedItem().getSongList()) {

            songsInPlaylist.getItems().add(s);
        }

    }

    @FXML
    void deleteSongFromPlaylist(ActionEvent event) {
        if (songsInPlaylist.getSelectionModel().getSelectedIndex() != -1 && PlaylistTabelView.getSelectionModel().getSelectedIndex() != -1) {
            stopMediaPlayer();
            playlistModel.removeSongFromPlaylist(PlaylistTabelView.getSelectionModel().getSelectedItem(), songsInPlaylist.getSelectionModel().getSelectedItem());
            refreshPlaylistSongs(songsInPlaylist.getSelectionModel().getSelectedIndex(), SongsTabelView.getSelectionModel().getSelectedItem(), false);
        }
    }


    private void play() {
        playSong.setText("||");
        mediaPlayer = new MediaPlayer(new Media(new File(songsInPlaylist.getItems().get(currentSongPlaying).getFileurl()).toURI().toString()));
        songsInPlaylist.getSelectionModel().clearAndSelect(currentSongPlaying);
        currentSong.setText(songsInPlaylist.getItems().get(currentSongPlaying).getTitle() + " is now playing");
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            if (songsInPlaylist.getSelectionModel().getSelectedIndex() != -1) {
                if (songsInPlaylist.getItems().size() == currentSongPlaying + 1) {
                    currentSongPlaying = 0;
                } else {
                    currentSongPlaying++;
                }
                play();
            } else {
                stopMediaPlayer();
            }
        });
    }


    private void stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            currentSong.setText("no song is playing");
            playSong.setText("▶");
            mediaPlayer = null;
        }

    }

    @FXML
    void SongBackward(ActionEvent event) {
        if (songsInPlaylist.getSelectionModel().getSelectedIndex() != -1) {
            stopMediaPlayer();
            if (currentSongPlaying - 1 == -1) {
                currentSongPlaying = 0;
            } else {
                currentSongPlaying--;
            }
            play();
        }
    }

    @FXML
    void SongForward(ActionEvent event) {
        if (songsInPlaylist.getSelectionModel().getSelectedIndex() != -1) {
            stopMediaPlayer();
            if (currentSongPlaying + 1 == songsInPlaylist.getItems().size()) {
                currentSongPlaying = 0;
            } else {
                currentSongPlaying++;
            }
            play();
        }

    }

    @FXML
    void editPlaylist(ActionEvent event) throws IOException {
        if (PlaylistTabelView.getSelectionModel().getSelectedIndex() != -1) {

            Parent root1;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/GUI/View/PlaylistView.fxml"));
            fxmlLoader.load();
            stopMediaPlayer();
            fxmlLoader.<NewPlaylistController>getController().setInfo(PlaylistTabelView.getSelectionModel().getSelectedItem()); // Tells the playlist controller class that the method will be editing its name
            //fxmlLoader.<NewPlaylistController>getController().setController(this); //Sets controler by default for both creating and editing playlists
        }
    }

    @FXML
    void SearchBtn(ActionEvent event) throws SQLException {
        if (Txt_search.getText() == null  || Txt_search.getText().length() <= 0 ){
            SongsTabelView.setItems(songModel.getSongs());
        }
        else {
            ObservableList<Songs> foundSong = songModel.search(songModel.getSongs(), Txt_search.getText());
            if ( foundSong != null){
                SongsTabelView.setItems(foundSong);

            }
        }

    }


}





