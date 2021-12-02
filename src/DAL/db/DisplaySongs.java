package DAL.db;


import BE.Songs;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisplaySongs {

    private static MyDatabaseConnector databaseConnector;
    public DisplaySongs() {
        databaseConnector = new MyDatabaseConnector();
    }
    public static List<Songs> getAllSongs() throws SQLException {

        ArrayList<Songs> allSongs = new ArrayList<>();

        //final String DATABASE_URL = "jdbc:derby:songs";
        final String sql = "SELECT Title, Artist , Category, Timeof FROM Songs";



        try(Connection connection = databaseConnector.getConnection(); Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            System.out.printf("Songs Table of MyTunes DataBase: %n%n");


            //display the name of Columns
            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            }
            System.out.println();

            //display query result
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                }
                System.out.println();
            }

            }
                return allSongs;

        }
    public static void main(String[] args) throws SQLException {
        DisplaySongs displaySongs = new DisplaySongs();
        List<Songs> allSongs = displaySongs.getAllSongs();
       System.out.println(allSongs);
    }

}


