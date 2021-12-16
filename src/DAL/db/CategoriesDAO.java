package DAL.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO { // Initialises the CategoriesDAO class

    SQLServerDataSource ds;


    private static MyDatabaseConnector databaseConnector;

    public CategoriesDAO() throws IOException {
        this.ds = new SQLServerDataSource();
        databaseConnector = new MyDatabaseConnector();
    }

    public List<String> getAllCategories() {
        List<String> allCategories = new ArrayList<>(); // Creates a String array to store all categories

        try (Connection con = ds.getConnection()) {
            String sqlStatement = "SELECT * FROM Catagory";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            while (rs.next()) {
                allCategories.add(rs.getString("name")); // Adds the category to the String array
            }
            return allCategories; // Returns the String array
        } catch (SQLServerException ex) {
            System.out.println(ex);
            return null;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    /*
    Inserts a new category into Category table
     */
    public void createCategory(String name) {
        String sql = "INSERT INTO Catagory VALUES (?)";
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.addBatch();
            ps.executeBatch();
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /*
    Deletes category from Category table
     */
    public void deleteCategory(String name) {
        try (Connection con = ds.getConnection()) {
            String query = "DELETE from Catagory WHERE name = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.execute();
        } catch (SQLServerException ex) {
            System.out.println(ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}

