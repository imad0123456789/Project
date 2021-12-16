package DAL.db;

import BE.Playlist;
import BE.Songs;
import com.microsoft.sqlserver.jdbc.SQLServerException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SongDAO_DB {
    private static MyDatabaseConnector databaseConnector;


    public SongDAO_DB() {
        databaseConnector = new MyDatabaseConnector();
    }

    // for insert method
    private Connection con;
    private String Sqlinsert = "INSERT INTO Songs ( Title, Artist, Category, Timeof, fileurl) VALUES (?, ?, ?, ?, ?)";
    private String Title;
    private String Artist;
    private String Category;
    private String Timeof;
    private String fileurl;


    public static List<Songs> getAll() throws SQLException {
        ArrayList<Songs> allSongs = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()) {
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM Songs";

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String Title = resultSet.getString("Title");
                    String Artist = resultSet.getString("Artist");
                    String Category = resultSet.getString("Category");
                    String Time = resultSet.getString("Timeof");
                    String FileURL = resultSet.getString("fileurl");

                    Songs songs = new Songs(id, Title, Artist, Category, Time, FileURL);
                    allSongs.add(songs);
                }

            }
        }
        return allSongs;
    }


    /*
    public Songs addSongs (String title, String artist, String category, String time, String fileurl) {
        String sql =  "INSERT INTO Songs ( Title, Artist, Category, Timeof, fileurl) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, artist);
            ps.setString(3, category);
            ps.setString(4, time);
            ps.setString(5, fileurl);
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        Songs son = new Songs(title, artist, category, time, fileurl); // Creates a song object
        return son; //Returns the song object
    }

*/


    public static Songs addSongs(Songs songs) {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Songs ( Title, Artist, Category, Timeof, fileurl) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, songs.getTitle());
            pstmt.setString(2, songs.getArtist());
            pstmt.setString(3, songs.getCategory());
            pstmt.setString(4, songs.getTime());
            pstmt.setString(5, songs.getFileurl());


            pstmt.executeUpdate();

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }





    public static void main(String[] args) throws SQLException {
        SongDAO_DB songDAO_db = new SongDAO_DB();
        List<Songs> allSongs = songDAO_db.getAll();

        System.out.println(allSongs);
    }


    public static Songs createSong(String title, String artist, String category, String time, String fileurl) {
        String sql = "INSERT INTO Songs( Title, Artist, Category, Timeof, fileurl) VALUES (?,?,?,?,?)";
        try (Connection con = databaseConnector.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, artist);
            ps.setString(3, category);
            ps.setString(4, time);
            ps.setString(5, fileurl);
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        Songs son = new Songs(title, artist, category, time, fileurl); // Creates a song object
        return son; //Returns the song object
    }


    public static Songs updateSong(Songs song, String Title, String Artist, String Category, String Timeof, String fileurl) {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "UPDATE Songs set Title = ?,Artist = ?,Category = ?,Timeof = ?,fileurl = ? WHERE id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString(1, Title);
            preparedStmt.setString(2, Artist);
            preparedStmt.setString(3, Category);
            preparedStmt.setString(4, Timeof);
            preparedStmt.setString(5, fileurl);
            preparedStmt.setInt(6, song.getId());
            preparedStmt.executeUpdate();
            Songs son = new Songs(song.getId(),Title, Artist, Category, Timeof, fileurl);
            return son;

        } catch (SQLServerException ex) {
            System.out.println(ex);
            return null;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }


    public void deleteSong (Songs songtoDelete){
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "DELETE FROM Songs WHERE id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setInt(1, songtoDelete.getId());
            preparedStmt.execute();
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}





