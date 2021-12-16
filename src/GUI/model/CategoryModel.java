package GUI.model;


import BLL.LogicInterface;

import java.sql.SQLException;
import java.util.List;

public class CategoryModel {

    private LogicInterface logiclayer;

    public CategoryModel(LogicInterface logiclayer) {
        this.logiclayer = logiclayer;
    }

    public CategoryModel() {

    }

    public List<String> getAllCategories() throws SQLException {
        return logiclayer.getAllCategories();
    }

    public void createCategory(String name) {
        logiclayer.createCategory(name);
    }

    public void deleteCategory(String name) {
        logiclayer.deleteCategory(name);
    }

}
