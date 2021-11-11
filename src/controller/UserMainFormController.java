package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class UserMainFormController {
    public AnchorPane mainPane;
    public ToggleButton btndashBoard;

    public void initialize(){
        btndashBoard.fire();
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage)mainPane.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }

    public void dashBoardFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AvailableForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }

    public void photographerFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PackageOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }

    public void rentItemFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RentItemOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }

    public void historyFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/OrderHistoryForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }

    public void returnFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ReturnItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }

    public void manageCustomerFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManagerCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        mainPane.getChildren().clear();
        mainPane.getChildren().add(load);
    }
}

