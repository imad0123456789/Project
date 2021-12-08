package DAL.db;



import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.table.AbstractTableModel;
import java.sql.*;

public class ResultSetTableModel extends AbstractTableModel {
    private Connection connection;
    private  Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;

        private static SQLServerDataSource dataSource;



        public  ResultSetTableModel()
        {

            dataSource = new SQLServerDataSource();
            dataSource.setDatabaseName("MyTouns");
            dataSource.setUser("CSe21B_12");
            dataSource.setPassword("CSe21B_12");
            dataSource.setPortNumber(1433);
            dataSource.setServerName("10.176.111.31");
        }
        public static Connection getConnection() throws SQLServerException {
            return dataSource.getConnection();
        }

        public static void main(String[] args) throws SQLException {

            DAL.db.MyDatabaseConnector databaseConnector = new DAL.db.MyDatabaseConnector();

            try  (Connection connection = databaseConnector.getConnection()){
                System.out.println("Is it Open " + !connection.isClosed());
            }
        }




    private boolean connectedToDatabase = false;


    public ResultSetTableModel(Connection connection ,String query) throws SQLException {
        connection = MyDatabaseConnector.getConnection();
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        connectedToDatabase=true;
        setQuery(query);
    }






    public Class getColumnClass (int column) throws IllegalStateException {
        if (!connectedToDatabase){
            throw new IllegalStateException("Not Connected to Database");
        }
        try {
            String className = metaData.getColumnClassName(column + 1);
            return Class.forName(className);

            } catch (Exception exception) {
            exception.printStackTrace();
            }
            return Object.class;
        }


    public int getColumnCount() throws IllegalStateException {
        if (!connectedToDatabase){
            throw new IllegalStateException("Not Connected to Database");
        }
        try{
            return metaData.getColumnCount();
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return 0;
    }

    public String grtColumnName(int column)throws IllegalStateException {
        if (!connectedToDatabase){
            throw new IllegalStateException("Not Connected to Database");
        }
        try {
            return metaData.getColumnName(column +1 );
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return "";
        }





    public int getRowCount() throws IllegalStateException {
        if (!connectedToDatabase){
            throw new IllegalStateException("Not Connected to Database");
        }
        return numberOfRows;
    }



    public Object getValueAt(int rowIndex, int columnIndex) throws IllegalStateException {
        if (!connectedToDatabase){
            throw new IllegalStateException("Not Connected to Database");
        }
        try {
                resultSet.absolute(rowIndex + 1);
                return resultSet.getObject(columnIndex +1);

            }
        catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        return "";
    }


    public void setQuery(String query) throws SQLException, IllegalStateException {

        if (!connectedToDatabase){
            throw new IllegalStateException("Not Connected to Database");
        }

        resultSet = statement.executeQuery(query);

        metaData= resultSet.getMetaData();

        resultSet.last();
        numberOfRows = resultSet.getRow();

        fireTableStructureChanged();
    }



    public void disconnectFromDatabase(){
        if (connectedToDatabase){
            try {
                resultSet.close();
                statement.close();
                connection.close();
            }
           catch (SQLException sqlException){
                    sqlException.printStackTrace();
                }
            finally {
                connectedToDatabase = false;
            }

        }
    }
}
