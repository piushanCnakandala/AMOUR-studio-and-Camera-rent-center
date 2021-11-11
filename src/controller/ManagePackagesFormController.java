package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.PackageCategory;
import model.Packages;
import util.ValidationUtil;
import view.tm.PackagesTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ManagePackagesFormController {

    public AnchorPane managePackagesPane;
    public ComboBox<String> cmbPackage;
    public JFXTextField txtPcId;
    public JFXTextField txtPid;
    public JFXTextField txtPType;
    public JFXTextField txtPDeatils;
    public JFXTextField txtPPrice;
    public TableView<PackagesTM> tblPackgers;
    public TableColumn colPCId;
    public TableColumn colPId;
    public TableColumn colPType;
    public TableColumn collPDeatils;
    public TableColumn colPrice;
    public JFXButton btnAdd;
    public JFXButton btnReomve;
    public JFXButton btnUpdate;

    LinkedHashMap<TextField, Pattern> map= new LinkedHashMap();
    Pattern packageCategoryIdPattern=Pattern.compile("^(Pc-|pc-)[0-9]{4}$");
    Pattern packageIdPattern=Pattern.compile("(Pa-|pa-)[0-9]{4}$");
    Pattern packageTypePattern=Pattern.compile("^[A-z ]{3,30}$");
    Pattern packageDeatilsPattern=Pattern.compile("^[A-z ]{3,30}$");
    Pattern packagePricePattern=Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");


    private void loadCategory() throws SQLException, ClassNotFoundException {
        List<String> category = new PackageCategoryController().getAllCategory();
        cmbPackage.getItems().addAll(category);
    }

    public void initialize() {

        try {
            showPackages();
            loadCategory();
            storeValidation();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbPackage.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setPackageCategoryData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void setPackageCategoryData(String packageCategoryData) throws SQLException, ClassNotFoundException {
        PackageCategory packageCategory = new PackageCategoryController().passCategory(packageCategoryData);
        if (packageCategoryData == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();

        } else {
            txtPcId.setText(packageCategory.getPcId());

        }
    }

    public void addPackageOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Packages packages = new Packages(txtPid.getText(), txtPType.getText(), txtPDeatils.getText(), Double.parseDouble(txtPPrice.getText()), txtPcId.getText());

        if (new PackageServiceController().savePackage(packages)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            showPackages();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public ObservableList<PackagesTM> getPackegsList() throws SQLException, ClassNotFoundException {
        ObservableList<PackagesTM> observableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM Package";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            observableList.add(new PackagesTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5)
            ));
        }
        tblPackgers.setItems(observableList);
        return observableList;
    }

    public void showPackages() throws SQLException, ClassNotFoundException {
        ObservableList<PackagesTM> observableList = getPackegsList();
        colPId.setCellValueFactory(new PropertyValueFactory<>("packageId"));
        colPType.setCellValueFactory(new PropertyValueFactory<>("packageType"));
        collPDeatils.setCellValueFactory(new PropertyValueFactory<>("packageDeatils"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colPCId.setCellValueFactory(new PropertyValueFactory<>("packageCategoryId"));
    }

    public void clearFiled() {
        txtPcId.clear();
        txtPDeatils.clear();
        txtPType.clear();
        txtPid.clear();
        txtPPrice.clear();

    }

    public void clearFeildsOnAction(ActionEvent actionEvent) {
        clearFiled();
    }

    public void removePackageOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PackagesTM packag = tblPackgers.getSelectionModel().getSelectedItem();
        String packages = packag.getPackageId();


        if (new PackageServiceController().removePackage(packages)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete Selected Item").show();
            showPackages();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clearFiled();
    }

    public void updateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PackagesTM packages = new PackagesTM(
                txtPid.getText(),
                txtPType.getText(),
                txtPDeatils.getText(),
                Double.parseDouble(txtPPrice.getText()),
                txtPcId.getText()
        );
        if (new PackageServiceController().updatePackage(packages)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update").show();
            showPackages();
        } else {
            new Alert(Alert.AlertType.WARNING, "try again").show();
        }
    }

    public void clickOnAction(MouseEvent mouseEvent) {
        PackagesTM packages = null;
        {
            try {
                packages = tblPackgers.getSelectionModel().getSelectedItem();
                txtPid.setText("" + packages.getPackageId());
                txtPType.setText("" + packages.getPackageType());
                txtPDeatils.setText("" + packages.getPackageDeatils());
                txtPPrice.setText("" + packages.getPrice());
                txtPcId.setText("" + packages.getPackageCategoryId());
            } catch (Exception e) {

            }
        }
    }

    private void storeValidation() {
        map.put(txtPcId, packageCategoryIdPattern);
        map.put(txtPid, packageIdPattern);
        map.put(txtPType, packageTypePattern);
        map.put(txtPDeatils,packageDeatilsPattern);
        map.put(txtPPrice,packagePricePattern);
    }

    public void onKeyReleassedFields(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnAdd);

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
