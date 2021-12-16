package GUI.Controller;

import GUI.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private Label specificFunctionLabel;
    @FXML
    private TextField nameField;
    @FXML
    private ChoiceBox<String> categoryChoice;
    @FXML
    private Label errorLabel;

    private CategoryModel categoryModel;
    NewEditSongsController controller1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            categoryModel = new CategoryModel();
            List<String> allcategories = categoryModel.getAllCategories(); //Gets all categories
            for (String allcategory : allcategories) { //Adds all categories to choice box
                categoryChoice.getItems().add(allcategory);
            }
        } catch (SQLException ex) {
            errorLabel.setText("Error: Cannot load song database");
        }
    }







    @FXML
    void goBack(ActionEvent event) {
        Stage stage = (Stage) errorLabel.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void createCategory(ActionEvent event) {
        String name = nameField.getText().trim();
        if (name != null && name.length() > 0 && name.length() < 50 && !categoryChoice.getItems().contains(name)) { // If name is typed and no dublicate names exist
            categoryModel.createCategory(name);
            errorLabel.setText("Success: Category " + name + " has been created succesfully");
            categoryChoice.getItems().add(name); //adds the name to current category box
            controller1.updateCategory(name, true); // updates the popupSong window category box
        } else {
            errorLabel.setText("Error: Please check if typed the name of the category correctly");
        }
    }



    @FXML
    private void deleteCategory(ActionEvent event) {
        String name = categoryChoice.getSelectionModel().getSelectedItem();
        if (name != null && name.length() > 0) {
            categoryModel.deleteCategory(name);
            errorLabel.setText("Success: Category " + name + " has been deleted succesfully");
            categoryChoice.getItems().remove(name); //deletes the name from current category box
            controller1.updateCategory(name, false); // updates the popupSong window category box
        } else {
            errorLabel.setText("Error: Please check if you selected a category");
        }

    }

    void setController(NewEditSongsController controller1) {
        this.controller1 = controller1;
    }


}

