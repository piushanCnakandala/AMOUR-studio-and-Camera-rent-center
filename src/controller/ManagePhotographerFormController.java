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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Photographer;
import util.ValidationUtil;
import view.tm.PhotographerTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ManagePhotographerFormController {
    public JFXTextField txtPhotographerId;
    public JFXTextField txtPhotographerName;
    public JFXTextField txtPhotographerAddress;
    public JFXTextField txtTelephoneNum;
    public JFXTextField txtSalary;
    public TableView<PhotographerTm> tblManagePhotographers;
    public TableColumn colPid;
    public TableColumn colPNane;
    public TableColumn colPAddeess;
    public TableColumn clTpNumber;
    public TableColumn colSalary;
    public JFXButton btnAdd;
    public JFXButton btnClear;
    public JFXButton btnReove;
    public JFXButton btnUpdatr;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern photographerIdPatton = Pattern.compile("^(PH-Ph-|ph-)[0-9]{4}$");
    Pattern photographerNamePatton = Pattern.compile("^[A-z ]{3,30}$");
    Pattern photographerAddressPatton = Pattern.compile("^[A-z 0-9 \\/\\,]{2,50}[A-z 0-9]{1,50}$");
    Pattern photographerTpPatton = Pattern.compile("^[0-9]{10}$");
    Pattern photographerSalaryPatton = Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");


    public void addPhotographerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Photographer photographer = new Photographer(txtPhotographerId.getText(), txtPhotographerName.getText(), txtPhotographerAddress.getText(), txtTelephoneNum.getText(), Double.parseDouble(txtSalary.getText()));

        if (new PhotographerServiceFormController().savePhotographer(photographer)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            showPhotographers();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void initialize() {
        btnAdd.setDisable(true);
        try {
            showPhotographers();
            storeValidation();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<PhotographerTm> getPhotographerList() throws SQLException, ClassNotFoundException {
        ObservableList<PhotographerTm> observableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM Photographer";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            observableList.add(new PhotographerTm(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5)
            ));
        }
        tblManagePhotographers.setItems(observableList);
        return observableList;
    }

    public void showPhotographers() throws SQLException, ClassNotFoundException {
        ObservableList<PhotographerTm> observableList = getPhotographerList();
        colPid.setCellValueFactory(new PropertyValueFactory<>("photographerId"));
        colPNane.setCellValueFactory(new PropertyValueFactory<>("photographerName"));
        colPAddeess.setCellValueFactory(new PropertyValueFactory<>("photographerAddress"));
        clTpNumber.setCellValueFactory(new PropertyValueFactory<>("photographerTpNumber"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("photographerSalary"));
    }

    public void clearFieldOnAction(ActionEvent actionEvent) {
        clearFiled();
    }

    public void removePhotographerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PhotographerTm photographer = tblManagePhotographers.getSelectionModel().getSelectedItem();
        String photographerId = photographer.getPhotographerId();


        if (new PhotographerServiceFormController().removePhotographer(photographerId)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete Selected Item").show();
            showPhotographers();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clearFiled();

    }

    private void clearFiled() {
        txtSalary.clear();
        txtPhotographerId.clear();
        txtPhotographerAddress.clear();
        txtPhotographerName.clear();
        txtTelephoneNum.clear();
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PhotographerTm photographer = new PhotographerTm(
                txtPhotographerId.getText(),
                txtPhotographerName.getText(),
                txtPhotographerAddress.getText(),
                txtTelephoneNum.getText(),
                Double.parseDouble(txtSalary.getText())
        );
        if (new PhotographerServiceFormController().updatePhotographer(photographer)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update").show();
            showPhotographers();
            clearFiled();
        } else {
            new Alert(Alert.AlertType.WARNING, "try again").show();
        }
    }

    public void mouseClickOnAction(MouseEvent mouseEvent) {
        PhotographerTm photographer = null;
        {
            try {
                photographer = tblManagePhotographers.getSelectionModel().getSelectedItem();
                txtPhotographerId.setText("" + photographer.getPhotographerId());
                txtPhotographerName.setText("" + photographer.getPhotographerName());
                txtPhotographerAddress.setText("" + photographer.getPhotographerAddress());
                txtTelephoneNum.setText("" + photographer.getPhotographerTpNumber());
                txtSalary.setText("" + photographer.getPhotographerSalary());
            } catch (Exception e) {

            }
        }
    }

    private void storeValidation() {
        map.put(txtPhotographerId, photographerIdPatton);
        map.put(txtPhotographerName, photographerNamePatton);
        map.put(txtPhotographerAddress, photographerAddressPatton);
        map.put(txtTelephoneNum, photographerTpPatton);
        map.put(txtSalary, photographerSalaryPatton);
    }

    public void onKeyRelesedAction(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map, btnAdd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();

            }
        }
    }
}
