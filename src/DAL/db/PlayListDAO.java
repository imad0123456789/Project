package DAL.db;



import BE.Playlist;
import BE.Songs;
import com.microsoft.sqlserver.jdbc.SQLServerException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class PlayListDAO {
    private static MyDatabaseConnector databaseConnector;


    public PlayListDAO() {
        databaseConnector = new MyDatabaseConnector();
    }

    private Connection con;
    private String name;



    public static List<Playlist> getAllPlayList() throws SQLException {
        ArrayList<Playlist> allPlaylist = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()) {
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM Playlists";

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("Name");

                    Playlist playlist = new Playlist(id, name);
                    allPlaylist.add(playlist);
                }

            }
        }
        return allPlaylist;
    }


    public static void main(String[] args) throws SQLException {
       PlayListDAO playListDAO = new PlayListDAO();
        List<Playlist> allPlaylist = playListDAO.getAllPlayList();

        System.out.println(allPlaylist);
    }


    public void addPlaylist (Playlist playlist) {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Playlists ( NAME ) VALUES (?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, playlist.getName());

            pstmt.executeUpdate();

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void deletePlaylist(Playlist play) {
        try (Connection con = databaseConnector.getConnection()) {
            String query = "DELETE from Playlists WHERE id = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, play.getId());
            preparedStmt.execute();
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }


}

