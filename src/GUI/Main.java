package GUI;

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

        stage.setTitle("MyTunes");
        stage.setScene(new Scene(root));
        stage.show();
    }



    public static void main(String[] args) throws SQLException {

        launch(args);
    }
}
