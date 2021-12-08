package GUI;

//import DAL.db.DisplaySongs;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/MainVeiw.fxml"));

        //XMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NewEditSongsVeiw.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 800 , 600);
        stage.setTitle("MyTunes");
        stage.setScene(new Scene(root));
        stage.show();
    }



    public static void main(String[] args) throws SQLException {
        //SongDAO_DB songDAO_db = new SongDAO_DB();
        //DisplaySongs ds = new DisplaySongs();
        //System.out.println(songDAO_db.getAll());
        launch(args);
    }
}
