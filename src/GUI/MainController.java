package GUI;

import BE.Songs;
import DAL.db.MyDatabaseConnector;
import DAL.db.SongDAO_DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TableView<Songs> SongsTabel;

    @FXML
    private TableColumn<Songs, String> Title;

    @FXML
    private TableColumn<Songs, String> Artist;

    @FXML
    private TableColumn<Songs, String> Category;

    @FXML
    private TableColumn<Songs, String> Time;

    ObservableList<Songs> listS;
    int index = -1;

    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


/*
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Title.setCellValueFactory(new PropertyValueFactory<Songs,String>("Title"));
        Artist.setCellValueFactory(new PropertyValueFactory<Songs,String>("Artist"));
        Category.setCellValueFactory(new PropertyValueFactory<Songs,String>("Category"));
        Time.setCellValueFactory(new PropertyValueFactory<Songs,String>("Time"));

        try {
            listS = (ObservableList<Songs>) SongDAO_DB.getAllSongs();
            SongsTabel.setItems(listS);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
*/

}

