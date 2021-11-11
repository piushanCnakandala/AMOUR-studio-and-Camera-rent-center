package Launcher;

import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LauncherPreloader implements Initializable {
    public AnchorPane lodingPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new load().start();
    }


    class load extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(5000);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                   // scene.setFill(Color.TRANSPARENT);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                   // stage.initStyle(StageStyle.TRANSPARENT); //Use For Boarder TRANSPARENT
                    stage.show();
                    lodingPane.getScene().getWindow().hide();
                }
            });


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
}