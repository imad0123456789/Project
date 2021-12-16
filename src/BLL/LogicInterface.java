package BLL;


import BE.Playlist;
import BE.Songs;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.List;

public interface LogicInterface {


    // Playlist
    public List<Playlist> getAllPlaylists() throws SQLException;

    public Playlist createPlaylist(String name);

    public void editPlaylist(Playlist get, String text);

    public void deletePlaylist(Playlist play);


    //Songs

    public List<Songs> getAllSongs() throws SQLException;

    public Songs createSong(int id, String title, String artist, String category, String time, String fileurl);

    public Songs updateSong(Songs songToDelete, String title, String artist, String category, String time, String fileurl);

    public void deleteSong(Songs songToDelete);


    //Adds and remove specified song to  playlist

    public Songs addToPlaylist(Playlist playlist, Songs song);

    public void removeSongFromPlaylist(Playlist selectedItem, Songs selectedSong);

    public void editSongPosition(Playlist selectedItem, Songs selected, Songs exhangeWith);

    public ObservableList<Songs> search(ObservableList<Songs> items, String text);


    //Category

    public List<String> getAllCategories() throws SQLException;

    public void createCategory(String name);

    public void deleteCategory(String name);


    List<Songs> getPlayListSong(int id);
}
