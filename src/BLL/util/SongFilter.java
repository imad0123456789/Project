package BLL.util;


import BE.Songs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Locale;

public class SongFilter {

    private ObservableList<Songs> temp = FXCollections.observableArrayList();

    public ObservableList<Songs> search (ObservableList<Songs> items, String text){
        temp.clear();;
        for (Songs item : items){
            if (item.getTitle().toLowerCase().startsWith(text.toLowerCase())){
                temp.add(item);
            }
        }
        return temp;
    }
}
