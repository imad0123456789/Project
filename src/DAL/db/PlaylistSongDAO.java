package DAL.db;

import BE.Playlist;
import BE.Songs;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistSongDAO {


    private static MyDatabaseConnector databaseConnector;

    public PlaylistSongDAO() {
        databaseConnector = new MyDatabaseConnector();
    }


    public List<Songs> getPlaylistSongs(int id) {
        List<Songs> newSongList = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT PlSong.*,Songs.* FROM PlSong INNER JOIN Songs ON PlSong.SgId = Songs.id WHERE PlSong.PLId = ? Order BY PLId desc ";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                Songs songs = new Songs(
                        rs.getInt("id"),
                        rs.getString("Title"),
                        rs.getString("Artist"),
                        rs.getString("Category"),
                        rs.getString("Timeof"),
                        rs.getString("fileurl"));
                newSongList.add(songs);


            }
            return newSongList;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            return null;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }


    public void deleteFromPlaylistSongsEverything(Songs songToDelete) {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "DELETE from PlSong WHERE sgid = ?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, songToDelete.getId());
            preparedStmt.execute();
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Songs addToPlaylist(Playlist playlist, Songs song) {
        String sql = "INSERT INTO PlSong(PLId ,SgId) VALUES (?,?)";
        int Id = -1;

        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            Id = getNewestSongInPlaylist(playlist.getId()) + 1;
            ps.setInt(1, playlist.getId());
            ps.setInt(2, song.getId());
            ps.execute();
            System.out.println("executed.");
            return song;

        } catch (SQLServerException ex) {
            System.out.println("addToPlayListMethod: "+ex);
            return null;

        } catch (SQLException ex) {
            System.out.println("addToPlayListMethod: "+ex);
            return null;
        }

    }

    private int getNewestSongInPlaylist(int id) {
        int newestID = -1;
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT TOP(1) * FROM PlSong WHERE PLId = ? ORDER by id desc";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                newestID = rs.getInt("id");
            }
            System.out.println(newestID);
            return newestID;
        } catch (SQLServerException ex) {
            System.out.println(ex);
            return newestID;
        } catch (SQLException ex) {
            System.out.println(ex);
            return newestID;
        }
    }

    public void removeSongFromPlaylist(Playlist selectedItem, Songs selectedSong) {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "DELETE from PlSong WHERE id=?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, selectedSong.getId());
            //preparedStatement.setInt(2, selectedSong.getId());
            preparedStatement.execute();


        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void editSongPosition(Playlist selectedItem, Songs selected, Songs exhangeWith) {
    }
}


