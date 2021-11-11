package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminMainFormController  {
    public AnchorPane dashBoardPane;
    public ToggleButton btnReports;
    public ToggleGroup admin;


    public  void initialize(){
        btnReports.fire();
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dashBoardPane.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }

    public void reportFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ReportForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardPane.getChildren().clear();
        dashBoardPane.getChildren().add(load);
    }

    public void mageUserFormOnActuon(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageUserForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardPane.getChildren().clear();
        dashBoardPane.getChildren().add(load);
    }

    public void manageMandCFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageModelsAndCategory.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardPane.getChildren().clear();
        dashBoardPane.getChildren().add(load);
    }

    public void managePackageFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManagePackagesForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardPane.getChildren().clear();
        dashBoardPane.getChildren().add(load);
    }

    public void managePhotographersFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManagePhotographerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardPane.getChildren().clear();
        dashBoardPane.getChildren().add(load);
    }

    public void ManageRentIremFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageItemForm1.fxml");
        Parent load = FXMLLoader.load(resource);
        dashBoardPane.getChildren().clear();
        dashBoardPane.getChildren().add(load);
    }
}
