package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane loginPane;

    public void logInOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        User login =new User(txtUserName.getText(),txtPassword.getText());
        String userType = new UserServiceController().login(login);

        if(userType.equals("Admin")){
            URL resource = getClass().getResource("../view/AdminMainForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) loginPane.getScene().getWindow();
            window.setScene(new Scene(load));
            window.centerOnScreen();
        }else if(userType.equals("User")){
            URL resource = getClass().getResource("../view/UserMainForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) loginPane.getScene().getWindow();
            window.setScene(new Scene(load));
            window.centerOnScreen();
        }else {
            new Alert(Alert.AlertType.WARNING,"Plesre chek User Name or Password").show();
        }
    }

}
