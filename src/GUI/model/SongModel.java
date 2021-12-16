package GUI.model;

import BE.Songs;
import BLL.LogicInterface;
import BLL.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class SongModel {

    private ObservableList<Songs> allSongs = FXCollections.observableArrayList();

    private final LogicInterface logiclayer;


    public SongModel() throws IOException {
        logiclayer = (LogicInterface) new Manager();

    }


    public ObservableList<Songs> getSongs() throws SQLException {
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll(logiclayer.getAllSongs());
        return allSongs;
    }

    public void createSong(int id, String title, String artist, String category, String time, String fileurl) {
        logiclayer.createSong( id,  title,  artist,  category,  time,  fileurl);
    }

    public void deleteSong(Songs songToDelete) {
        logiclayer.deleteSong(songToDelete);
    }

    public void updateSong(Songs songToDelete, String title, String artist, String category, String time, String fileurl) {
        logiclayer.updateSong(songToDelete, title, artist, category, time, fileurl);
    }


    public ObservableList<Songs> search(ObservableList<Songs> items, String query) {
        return logiclayer.search(items, query);
    }




}
