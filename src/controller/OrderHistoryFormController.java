package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class OrderHistoryFormController {
    public AnchorPane historyPane;
    public ToggleButton rbtnRentItem;
    public ToggleGroup togalbuttonset;


    public void initialize(){
        rbtnRentItem.fire();
    }

    public void rentItemOrdersOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RentItemHistoryForm.fxml");
        Parent load = FXMLLoader.load(resource);
       historyPane.getChildren().clear();
        historyPane.getChildren().add(load);

    }

    public void photographyServiceOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PhotogrphyServiceHistoryForm.fxml");
        Parent load = FXMLLoader.load(resource);
        historyPane.getChildren().clear();
        historyPane.getChildren().add(load);
    }
}
