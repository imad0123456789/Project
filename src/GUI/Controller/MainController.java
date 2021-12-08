package GUI.Controller;




import BE.Songs;
import DAL.db.DisplaySongs;
import DAL.db.SongDAO_DB;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {

    @FXML
    private AnchorPane MainborderPane;

    @FXML
    private TableView<Songs> SongsTabelView;

    @FXML
    private TableColumn<Songs, String> col_Artist;

    @FXML
    private TableColumn<Songs, String> col_Category;

    @FXML
    private TableColumn<Songs, String> col_Title;
    @FXML
    private TableColumn<Songs, String> col_Time;

    @FXML
    private Button CloseBotton;

    @FXML
    private Button LoadBut;

    @FXML
    private Button NewBot;

    @FXML
    private Button NewListBt;


    private SongDAO_DB songDAO_db  = new SongDAO_DB();
    private ActionEvent actionEvent;


    public void initialize(URL url, ResourceBundle resourceBundle) {




        SongsTabelView.getSelectionModel().selectedItemProperty().addListener(
                (obs , olaVal , newVal) -> {
                    if(newVal!=null){
                        System.out.println("newVal:  "+olaVal+" "+obs);
                        System.out.println(newVal);
                        col_Title.setText(newVal.getTitle());
                        col_Artist.setText(newVal.getArtist());
                        col_Category.setText(newVal.getCategory());
                        col_Time.setText(newVal.getTime());

                    }
                }
        );



    }




    public void clickLoad(ActionEvent actionEvent) throws SQLException {
        this.actionEvent = actionEvent;
        List<Songs> songs = songDAO_db.getAll();

        SongsTabelView.getItems().clear();

        for (Songs s:songs) {
            col_Title.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getTitle()));
            col_Artist.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getArtist()));
            col_Category.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCategory()));
            col_Time.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getTime()));
        }

        SongsTabelView.getItems().setAll(songs);

    }


    @FXML
    void clickNew(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/View/NewEditSongsVeiw.fxml"));
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickClose(ActionEvent event) throws IOException {

        Stage stage = (Stage) CloseBotton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void clickNewlist(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/View/PlaylistView.fxml"));
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }





}

