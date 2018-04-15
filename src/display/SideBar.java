package display;


import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import services.Display;

import java.util.ArrayList;

public class SideBar {

    private HBox pane;

    private ListView<String> list_view;

    public SideBar(Display display){

        this.pane = new HBox(5);

        list_view = new ListView<>();

        list_view.getSelectionModel().selectedItemProperty().addListener(e-> display.displayData(selectedData()));

        pane.getChildren().add(list_view);
    }

    public void setInfo(ArrayList<String> list){
        list_view.setItems(FXCollections.observableArrayList(list));
    }

    public Pane display(){
        return pane;
    }

    //Private Methods
    private String selectedData(){
        if(list_view.getSelectionModel().getSelectedIndex() != -1)
            return list_view.getItems().get(list_view.getSelectionModel().getSelectedIndex());
        else
            return list_view.getItems().get(0);
    }
}
