package DAL.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSongs {

    private static MyDatabaseConnector databaseConnector;


    public InsertSongs() {
        databaseConnector = new MyDatabaseConnector();
    }
    private Connection con;
    private String Sqlinsert = "INSERT INTO Songs ( Title, Artist, Category, Timeof, fileurl) VALUES (?, ?, ?, ?, ?)";
    private String Title;
    private String Artist;
    private String Category;
    private String Timeof;
    private String fileurl;



    public InsertSongs(String Title, String Artist, String Category, String Timeof, String fileurl) throws SQLException {
        this.Title = Title;
        this.Artist = Artist;
        this.Category = Category;
        this.Timeof = Timeof;
        this.fileurl = fileurl;
        try (Connection con = databaseConnector.getConnection()) {
        }
        insert();


    }

    private void insert() {
        try {
            PreparedStatement s = con.prepareStatement(Sqlinsert);
            s.setString(1, Title);
            s.setString(2, Artist);
            s.setString(3, Category);
            s.setString(4, Timeof);
            s.setString(5, fileurl);
            s.executeLargeUpdate();
            System.out.print("ok");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }



}