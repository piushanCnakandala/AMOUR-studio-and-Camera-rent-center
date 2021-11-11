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
import model.Item;
import model.RentItemCategory;
import model.RentItemModels;
import util.ValidationUtil;
import view.tm.ItemTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ManageItemForm1Controller {
    public ComboBox<String> cmbCategory;
    public ComboBox<String> cmbModel;
    public JFXTextField txtCategory;
    public JFXTextField txtSerialNumber;
    public JFXTextField txtItemType;
    public JFXTextField txtStatys;
    public JFXTextField txtPrice;
    public TableColumn colCid;
    public TableColumn colModelId;
    public TableColumn colSNumber;
    public TableColumn colType;
    public TableColumn colPrice;
    public TableColumn colStatus;
    public JFXButton btnAdd;
    public JFXButton btnClear;
    public JFXButton btnUpdate;
    public JFXButton btnRemove;
    public TableView<ItemTm> tblItem;
    public JFXTextField txtModel;


    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern itemCategoryIdPatton = Pattern.compile("^(Ic-|ic-)[0-9]{4}$");
    Pattern itemModelIdPatton = Pattern.compile("^(M-|m-)[0-9]{4}$");
    Pattern serialNumberPatton = Pattern.compile("^(S-|s-)[0-9]{4}$");
    Pattern itemTypePatton = Pattern.compile("^[A-z ]{3,30}$");
    Pattern itemPricePatton = Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");

    private void loadCategory() throws SQLException, ClassNotFoundException {
        List<String> categoryId = new RentItemCategoryServiceController().getAllCategory();
        cmbCategory.getItems().addAll(categoryId);
    }

    private void loadModel() throws SQLException, ClassNotFoundException {
        List<String> modelId = new RentItemModelsServiceController().getAllModels();
        cmbModel.getItems().addAll(modelId);
    }

    public void addItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Item item = new Item(txtSerialNumber.getText(), txtItemType.getText(), Double.parseDouble(txtPrice.getText()), txtCategory.getText(), txtModel.getText(), txtStatys.getText());
        if (new ItemServiceController().saveItem(item)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            showItem();
            clearFiled();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public ObservableList<ItemTm> getItemList() throws SQLException, ClassNotFoundException {
        ObservableList<ItemTm> observableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM `Rent_Item`";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            observableList.add(new ItemTm(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            ));
        }
        tblItem.setItems(observableList);
        return observableList;
    }

    public void initialize() {
        btnAdd.setDisable(true);
        try {
            showItem();
            loadCategory();
            loadModel();
            storeValidation();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            try {
                setRentCategoryData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });
        cmbModel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setRentModelData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    public void showItem() throws SQLException, ClassNotFoundException {
        ObservableList<ItemTm> observableList = getItemList();
        colSNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        colModelId.setCellValueFactory(new PropertyValueFactory<>("modelId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("text"));
    }

    private void setRentCategoryData(String rentCategoryData) throws SQLException, ClassNotFoundException {
        RentItemCategory rentItemCategory = new RentItemCategoryServiceController().passCategory(rentCategoryData);
        if (rentCategoryData == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();

        } else {
            txtCategory.setText(rentItemCategory.getItemCategoryId());

        }
    }

    private void setRentModelData(String rentModelData) throws SQLException, ClassNotFoundException {
        RentItemModels rentItemModels = new RentItemModelsServiceController().passModels(rentModelData);
        if (rentModelData == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();

        } else {
            txtModel.setText(rentItemModels.getItemModelId());

        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clearFiled();
    }

    public void upadteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemTm itemTm = new ItemTm(
                txtSerialNumber.getText(),
                txtItemType.getText(),
                Double.parseDouble(txtPrice.getText()),
                txtCategory.getText(),
                txtModel.getText(),
                txtStatys.getText()
        );
        if (new ItemServiceController().updateItem(itemTm)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update").show();
            showItem();
        } else {
            new Alert(Alert.AlertType.WARNING, "try again").show();
        }
    }

    public void removeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ItemTm item = tblItem.getSelectionModel().getSelectedItem();
        String items = item.getSerialNumber();


        if (new ItemServiceController().removeItem(items)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete Selected Item").show();
            showItem();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clearFiled();
    }

    private void clearFiled() {
        txtModel.clear();
        txtCategory.clear();
        txtPrice.clear();
        txtItemType.clear();
        txtSerialNumber.clear();
    }

    public void clickedOnAction(MouseEvent mouseEvent) {
        ItemTm itemTm = null;
        {
            try {
                itemTm = tblItem.getSelectionModel().getSelectedItem();
                txtSerialNumber.setText("" + itemTm.getSerialNumber());
                txtItemType.setText("" + itemTm.getType());
                txtPrice.setText("" + itemTm.getPrice());
                txtCategory.setText("" + itemTm.getCategoryId());
                txtModel.setText("" + itemTm.getModelId());
                txtStatys.setText("" + itemTm.getText());
            } catch (Exception e) {

            }
        }
    }

    private void storeValidation() {
        map.put(txtCategory, itemCategoryIdPatton);
        map.put(txtModel, itemModelIdPatton);
        map.put(txtSerialNumber, serialNumberPatton);
        map.put(txtItemType, itemTypePatton);
        map.put(txtPrice, itemPricePatton);
    }

    public void onKeyReleasedOnAction(KeyEvent keyEvent) {
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
