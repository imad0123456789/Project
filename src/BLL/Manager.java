package BLL;


import BE.Playlist;
import BE.Songs;
import BLL.util.SongFilter;
import DAL.db.CategoriesDAO;
import DAL.db.PlayListDAO;
import DAL.db.PlaylistSongDAO;
import DAL.db.SongDAO_DB;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Manager implements LogicInterface {


    private final PlayListDAO playListDAO;
    private final SongDAO_DB songDAO_db;
    private final SongFilter songSearcher;
    private final PlaylistSongDAO playlistSongInfo;
    private final CategoriesDAO categoriesDAO;


    public Manager() throws IOException {
        playListDAO = new PlayListDAO();
       songDAO_db = new SongDAO_DB();
        songSearcher = new SongFilter();
        playlistSongInfo = new PlaylistSongDAO();
       categoriesDAO = new CategoriesDAO();
    }


    @Override
    public List<Playlist> getAllPlaylists() throws SQLException {
        return PlayListDAO.getAllPlayList();
    }
    public List<Songs> getPlayListSong(int idPlaylist){
        List<Songs> allSongsInPLayList = playlistSongInfo.getPlaylistSongs(idPlaylist);
        return allSongsInPLayList;
    }
    @Override
    public Playlist createPlaylist(String name) {
        return null;
    }

    @Override
    public void editPlaylist(Playlist get, String text) {

    }

    @Override
    public void deletePlaylist(Playlist play) {

        //playlistSongInfo.deleteFromPlaylistSongsEverything(play);
        playListDAO.deletePlaylist(play);
    }



    @Override
    public List<Songs> getAllSongs() throws SQLException {
        return SongDAO_DB.getAll();
    }


    @Override
    public Songs createSong(int id, String title, String artist, String category, String time, String fileurl) {
      return SongDAO_DB.createSong( title,  artist,  category,  time,  fileurl);
    }


    @Override
    public void deleteSong(Songs songToDelete) {
        playlistSongInfo.deleteFromPlaylistSongsEverything(songToDelete);
        songDAO_db.deleteSong(songToDelete);

    }

    @Override
    public Songs updateSong(Songs song, String Title, String Artist, String Category, String Timeof, String fileurl) {
        return SongDAO_DB.updateSong( song,  Title,  Artist,  Category,  Timeof,  fileurl);
    }



    @Override
    public Songs addToPlaylist(Playlist playlist, Songs song) {
        return playlistSongInfo.addToPlaylist(playlist, song);
    }



    @Override
    public void removeSongFromPlaylist(Playlist selectedItem, Songs selectedSong) {
        playlistSongInfo.removeSongFromPlaylist(selectedItem, selectedSong);
    }


    @Override
    public void editSongPosition(Playlist selectedItem, Songs selected, Songs exhangeWith) {
        playlistSongInfo.editSongPosition(selectedItem,selected,exhangeWith);
    }


    @Override
    public ObservableList<Songs> search(ObservableList<Songs> items, String text) {
        return null;
    }


    @Override
    public List<String> getAllCategories() {
        return null;
    }

    @Override
    public void createCategory(String name) {

    }

    @Override
    public void deleteCategory(String name) {

    }
}

