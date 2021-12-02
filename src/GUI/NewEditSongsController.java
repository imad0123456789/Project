package GUI;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;


import BE.Songs;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;

public class NewEditSongsController implements Initializable {
    @FXML
    private TextField txt_artist;

    @FXML
    private ComboBox<?> txt_category;

    @FXML
    private TextField txt_file_url;

    @FXML
    private TextField txt_time;

    @FXML
    private TextField txt_title;


    @FXML
    private Button ClickMore;

    @FXML
    private Button ClickSave;

    @FXML
    private Button ClikeCancel;

    @FXML
    private Button ClikeChoose;

    ObservableList<Songs> listS;
    int index = -1;

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public static void main(String[] args) throws IOException {

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setDatabaseName("MyTouns");
        ds.setUser("CSe21B_12");
        ds.setPassword("CSe21B_12");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");

    }

    public void addSongs(SQLServerDataSource ds) {

        try (Connection con = ds.getConnection()) {
            String sql = "insert into songs (title, artist, category, time, file) values (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, txt_title.getText());
            pst.setString(2, txt_artist.getText());
            pst.setString(3, txt_category.getStyle());
            pst.setDouble(4, txt_time.getOpacity());
            pst.setString(5, txt_title.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Songs add succes");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
