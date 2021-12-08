package DAL.db;

import BE.Songs;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.table.AbstractTableModel;


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


    public void addSongs(Songs songs) {
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
    }

    public static void main(String[] args) throws SQLException {
        SongDAO_DB songDAO_db = new SongDAO_DB();
        List<Songs> allSongs = songDAO_db.getAll();
        System.out.println(allSongs);
    }

}





