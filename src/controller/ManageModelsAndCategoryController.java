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
import model.PackageCategory;
import model.RentItemCategory;
import model.RentItemModels;
import util.ValidationUtil;
import view.tm.PackageCategoryTM;
import view.tm.RentItemCategoryTM;
import view.tm.RentItemModelsTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ManageModelsAndCategoryController {
    public JFXTextField txtpcId;
    public TableView<PackageCategoryTM> tblPackageCategory;
    public TableColumn colPcId;
    public TableColumn colPcName;
    public JFXTextField txtPcName;
    public JFXTextField txtIItemCId;
    public JFXTextField txtIyemICName;
    public TableView <RentItemCategoryTM>tblIyemCategory;
    public TableColumn colItemId;
    public TableColumn colItemCName;
    public JFXTextField txtModelId;
    public JFXTextField txtModelName;
    public TableView<RentItemModelsTM>tblModel;
    public TableColumn colIMId;
    public TableColumn colIMNname;
    public JFXButton btnPadd;
    public JFXButton btnPclear;
    public JFXButton btnPremove;
    public JFXButton btnCadd;
    public JFXButton btnCclear;
    public JFXButton btnCremove;
    public JFXButton btnMadd;
    public JFXButton btnMclear;
    public JFXButton btnMromve;


    LinkedHashMap<TextField, Pattern> map =new LinkedHashMap();
    Pattern packageCategoryId=Pattern.compile("^(Pc-|pc-)[0-9]{4}$");
    Pattern packageCategoryName=Pattern.compile("^[A-z ]{3,20}$");

    LinkedHashMap<TextField, Pattern> map1 =new LinkedHashMap();
    Pattern itemCategoryId=Pattern.compile("^(Ic-|ic-)[0-9]{4}$");
    Pattern itemCategoryName=Pattern.compile("^[A-z ]{3,20}$");

    LinkedHashMap<TextField, Pattern> map2 =new LinkedHashMap();
    Pattern itemModelId=Pattern.compile("^(M-|m-)[0-9]{4}$");
    Pattern itemModelName=Pattern.compile("^[A-z ]{3,20}$");

    public void addPackageCategoryOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PackageCategory packageCategory = new PackageCategory(txtpcId .getText(), txtPcName.getText());

        if (new PackageCategoryController().savePackageCategory(packageCategory)){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
           showPackageCategory();
           clearFiled();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public ObservableList<PackageCategoryTM>getPackageCategoryList() throws SQLException, ClassNotFoundException {
        ObservableList<PackageCategoryTM> observableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM PackageCategory";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            observableList.add(new PackageCategoryTM(
                    rst.getString(1),
                    rst.getString(2)
            ));
        }
        tblPackageCategory.setItems(observableList);
        return observableList;
    }

    public  void initialize(){
        btnCadd.setDisable(true);
        btnMadd.setDisable(true);
        btnPadd.setDisable(true);
        try {
            showPackageCategory();
            showRentItemCategory();
            showRentItemModels();
            storeValidation1();
            storeValidation2();
            storeValidation3();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clearFieldOnAction(ActionEvent actionEvent) {
        clearFiled();
    }

    public void removePackageCategoryOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        PackageCategoryTM pc = tblPackageCategory.getSelectionModel().getSelectedItem();
        String  pcId = pc.getPcId();


        if (new PackageCategoryController().removePackageCategory(pcId)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete Selected Item").show();
            showPackageCategory();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clearFiled();

    }

    private void clearFiled() {
        txtpcId.clear();
        txtPcName.clear();
    }

    public void showPackageCategory() throws SQLException, ClassNotFoundException {
        ObservableList<PackageCategoryTM> observableList=getPackageCategoryList();
        colPcId.setCellValueFactory(new PropertyValueFactory<>("pcId"));
        colPcName.setCellValueFactory(new PropertyValueFactory<>("pcName"));
    }

    public void addItemCategoricesOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       RentItemCategory rentItemCategory = new RentItemCategory(txtIItemCId .getText(), txtIyemICName.getText());

        if (new RentItemCategoryServiceController().saveRentItemCategory(rentItemCategory)){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
          showRentItemCategory();
           clearFeildsItemCategory();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    public ObservableList<RentItemCategoryTM>getRentItemCategory() throws SQLException, ClassNotFoundException {
        ObservableList<RentItemCategoryTM> observableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM `Rent_Item_Category`";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            observableList.add(new RentItemCategoryTM(
                    rst.getString(1),
                    rst.getString(2)
            ));
        }
      tblIyemCategory.setItems(observableList);
        return observableList;
    }

    public void showRentItemCategory() throws SQLException, ClassNotFoundException {
        ObservableList<RentItemCategoryTM> observableList=getRentItemCategory();
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemCategoryId"));
        colItemCName.setCellValueFactory(new PropertyValueFactory<>("itemCategoryName"));
    }

    public void clearItemFeildsOnAction(ActionEvent actionEvent) {
        clearFeildsItemCategory();
    }

    public void removeItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        RentItemCategoryTM pc = (RentItemCategoryTM) tblIyemCategory.getSelectionModel().getSelectedItem();
        String  itemCategoryId = pc.getItemCategoryId();


        if (new RentItemCategoryServiceController().removeRentItemCategory(itemCategoryId)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete Selected Item").show();
            showRentItemCategory();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clearFeildsItemCategory();

    }

    public void clearFeildsItemCategory(){
        txtIItemCId.clear();
        txtIyemICName.clear();
        
    }

    public void addModelOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    RentItemModels itemModels = new RentItemModels(txtModelId .getText(),txtModelName.getText());

        if (new RentItemModelsServiceController().saveRentItemModels(itemModels)){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
           showRentItemModels();
          clearfieldsModel();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public ObservableList<RentItemModelsTM>getRentItemModel() throws SQLException, ClassNotFoundException {
        ObservableList<RentItemModelsTM> observableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM `Rent_Item_Model`";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            observableList.add(new RentItemModelsTM(
                    rst.getString(1),
                    rst.getString(2)
            ));
        }
        tblModel.setItems(observableList);
        return observableList;
    }

    public void showRentItemModels() throws SQLException, ClassNotFoundException {
        ObservableList<RentItemModelsTM> observableList=getRentItemModel();
       colIMId.setCellValueFactory(new PropertyValueFactory<>("itemModelId"));
        colIMNname.setCellValueFactory(new PropertyValueFactory<>("itemModelName"));
    }

    public void clearModelFieldsOnAction(ActionEvent actionEvent) {
    }

    public void removeModelOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       RentItemModelsTM pc = (RentItemModelsTM) tblModel.getSelectionModel().getSelectedItem();
        String  pcId = pc.getItemModelId();


        if (new RentItemModelsServiceController().removeRentItemModels(pcId)){
            new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete Selected Item").show();
            showRentItemModels();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
     clearfieldsModel();

    }

    public void clearfieldsModel(){
        txtModelName.clear();
        txtModelId.clear();
    }

    public void storeValidation1() {
        map.put(txtpcId,packageCategoryId);
        map.put(txtPcName, packageCategoryName);

    }

    public void storeValidation2(){
        map1.put(txtIItemCId,itemCategoryId);
        map1.put(txtIyemICName, itemCategoryName);
    }

    public void storeValidation3(){
        map2.put(txtModelId, itemModelId);
        map2.put(txtModelName, itemModelName);
    }

    public void onketReleassedItemModel(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map2,btnMadd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();

            }
        }
    }

    public void onkeyReleassedItemCategory(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map1,btnCadd);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();

            }
        }
    }

    public void onKeyReleasedPackageCategory(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnPadd);

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
