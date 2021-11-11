package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.User;
import view.tm.UserTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ManageUserFormController {
    public AnchorPane manageUserPane;
    public JFXTextField txtRole;
    public JFXTextField txtFullName;
    public JFXTextField txtName;
    public JFXTextField txtPassword;
    public TableView<UserTM> tblUsers;
    public TableColumn colRole;
    public TableColumn cilFullName;
    public TableColumn colUserName;
    public TableColumn colUserPassword;
    public JFXButton btnAdd;
    public JFXButton btnClear;
    public JFXButton btnReovee;
    public JFXButton btnUpdate;

    LinkedHashMap<TextField, Pattern> map= new LinkedHashMap();
    Pattern rolePattern=Pattern.compile("^(Admin|admin|User|user|USER|ADMIN)$");
    Pattern fullNamePattern=Pattern.compile("^[A-z ]{3,30}$");
    Pattern userNamePattern=Pattern.compile("^[A-z ]{3,30}$");
    Pattern passwordPattern=Pattern.compile("^[A-z ]{3,30}$");


    public void addUserOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        User user = new User(txtName.getText(), txtPassword.getText(), txtFullName.getText(), txtRole.getText());

        if (new UserServiceController().saveUser(user)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            showUsers();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void initialize() {
        btnAdd.setDisable(true);
        try {
            showUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clearFiled();
    }

    public ObservableList<UserTM> getUserList() throws SQLException, ClassNotFoundException {
        ObservableList<UserTM> observableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM USER";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            observableList.add(new UserTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        tblUsers.setItems(observableList);
        return observableList;
    }

    public void showUsers() throws SQLException, ClassNotFoundException {
        ObservableList<UserTM> observableList = getUserList();
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colUserPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        cilFullName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    public void clearFiled() {
        txtFullName.clear();
        txtName.clear();
        txtPassword.clear();
        txtRole.clear();

    }

    public void clearFieldsOnAction(ActionEvent actionEvent) {
        clearFiled();
    }

    public void removeUserOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        UserTM item = tblUsers.getSelectionModel().getSelectedItem();
        String userName = item.getUserName();


        if (new UserServiceController().removeUser(userName)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete Selected Item").show();
            showUsers();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clearFiled();


    }

    public void updateUserOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        UserTM user = new UserTM(
                txtPassword.getText(),
                txtFullName.getText(),
                txtName.getText(),
                txtRole.getText()
        );
        if (new UserServiceController().updateUser(user)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update").show();
            showUsers();
        } else {
            new Alert(Alert.AlertType.WARNING, "try again").show();
        }
    }

    public void selectOneClick(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        UserTM user = null;
        {
            try {
                user = tblUsers.getSelectionModel().getSelectedItem();
                txtName.setText("" + user.getUserName());
                txtPassword.setText("" + user.getPassword());
                txtFullName.setText("" + user.getName());
                txtRole.setText("" + user.getRole());
            } catch (Exception e) {

            }
        }
    }



    public void onKeyReleseedOnAction(KeyEvent keyEvent) {

    }
}
