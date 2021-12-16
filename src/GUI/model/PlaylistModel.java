package GUI.model;


import BE.Playlist;
import BE.Songs;
import BLL.LogicInterface;
import BLL.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class PlaylistModel {

    private ObservableList<Playlist> allPlaylists;

    private final LogicInterface logiclayer;

    public PlaylistModel() throws IOException {
        logiclayer = new Manager();
    }


    public ObservableList<Playlist> getPlaylists() throws SQLException {
        allPlaylists = FXCollections.observableArrayList();
        allPlaylists.addAll(logiclayer.getAllPlaylists());
        for (Playlist pl: allPlaylists) {
            pl.setSongList(logiclayer.getPlayListSong(pl.getId()));
        }
        return allPlaylists;
    }


    public void createPlaylist(String name) {
        logiclayer.createPlaylist(name);
    }

    public void deletePlaylist(Playlist play) {
        logiclayer.deletePlaylist(play);
    }

    public void editPlaylist(Playlist play, String name) {
        logiclayer.editPlaylist(play, name);
    }

    public Songs addToPlaylist(Playlist play, Songs song) {
        return logiclayer.addToPlaylist(play, song);
    }

    public void removeSongFromPlaylist(Playlist selectedItem, Songs selectedSong) {
        logiclayer.removeSongFromPlaylist(selectedItem, selectedSong);
    }

    public void editSongPosition(Playlist selectedItem, Songs selected, Songs exhangeWith) {
        logiclayer.editSongPosition(selectedItem, selected, exhangeWith);
    }
}
