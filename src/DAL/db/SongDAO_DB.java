package DAL.db;

import BE.Songs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO_DB {

    private static MyDatabaseConnector databaseConnector;

    public SongDAO_DB() {
        databaseConnector = new MyDatabaseConnector();
    }

    public static List<Songs> getAllSongs() throws SQLException {

        ArrayList<Songs> allSongs = new ArrayList<>();


        try (Connection connection = databaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM Songs;";

            System.out.printf("Songs Table of MyTunes DataBase: %n%n");



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

    public static void main(String[] args) throws SQLException {
        SongDAO_DB songDAO_db = new SongDAO_DB();
        List<Songs> allSongs = songDAO_db.getAllSongs();
        System.out.println(allSongs);
    }




    /*

    public static ObservableList<Songs> getDataSongs() throws SQLException {
        final MyDatabaseConnector databaseConnector;
        databaseConnector = new MyDatabaseConnector();
        Connection connection = databaseConnector.getConnection();
        ObservableList<Songs> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Songs");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                {
                    int id = rs.getInt("id");
                    String Title = rs.getString("Title");
                    String Artist = rs.getString("Artist");
                    String Category = rs.getString("Category");
                    String Time = rs.getString("Timeof");
                    String FileURL = rs.getString("fileurl");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;

    }
*/


}

