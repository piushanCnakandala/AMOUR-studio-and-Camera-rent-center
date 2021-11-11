package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Customer;
import model.Item;
import model.OderItem;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.CartTM;
import view.tm.RentItemOrderDeatilsTM;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RentItemOrderFormController {
    static ObservableList<CartTM> parentObList = FXCollections.observableArrayList();
    static ObservableList<Item> observableList = FXCollections.observableArrayList();
    public Label iblDate;
    public Label iblTime;
    public ComboBox<String> cmbCustomer;
    public JFXTextField txtCustId;
    public JFXTextField txtCustName;
    public ComboBox<String> cmbItems;
    public JFXTextField txtSId;
    public JFXTextField txtIType;
    public JFXTextField txtMId;
    public JFXTextField txtDPrice;
    public JFXTextField txtCId;
    public TableView tblItem;
    public TableColumn colOId;
    public TableColumn colCustomerId;
    public TableColumn colSId;
    public TableColumn colMId;
    public TableColumn colCId;
    public TableColumn colOneDayPrice;
    public TableColumn colTotalPrice;
    public TableColumn colPrice;
    public Label lblOId;
    public DatePicker rentDate;
    public DatePicker returnDate;
    public Label lblTotal;
    int totalDays;
    private OderItem item;

    private void loadCustomers() throws SQLException, ClassNotFoundException {
        List<String> customerId = new CustomerServiceController().getAllCustomer();
        cmbCustomer.getItems().addAll(customerId);
    }

    private void loadItems() throws SQLException, ClassNotFoundException {
        List<String> itemId = new ItemServiceController().getAllItems();
        cmbItems.getItems().addAll(itemId);
    }

    public void initialize() {
        try {
            showsData();
            loadCustomers();
            loadItems();
            setOrderIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbCustomer.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            try {
                setCustomerData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }));
        cmbItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    public void showsData() {
        colOId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colSId.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colMId.setCellValueFactory(new PropertyValueFactory<>("modelId"));
        colCId.setCellValueFactory(new PropertyValueFactory<>("catogeryId"));
        colOneDayPrice.setCellValueFactory(new PropertyValueFactory<>("oneDyPrice"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalDays"));
        parentObList.clear();
    }

    private void setCustomerData(String customerData) throws SQLException, ClassNotFoundException {
        Customer customer = new CustomerServiceController().passCustomer(customerData);
        if (customer == null) {
            //new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();

        } else {
            txtCustId.setText(customer.getCustomerId());
            txtCustName.setText(customer.getCustomerName());

        }
    }

    private void setItemData(String itemDataData) throws SQLException, ClassNotFoundException {
        Item item = new ItemServiceController().passItems(itemDataData);
        if (item == null) {
            //  new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();

        } else {
            txtSId.setText(item.getSerialNumber());
            txtIType.setText(item.getType());
            txtCId.setText(item.getCategoryId());
            txtMId.setText(item.getModelId());
            txtDPrice.setText(String.valueOf(item.getPrice()));

        }
    }

    public void OderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<RentItemOrderDeatilsTM> list = new ArrayList<>();
        for (CartTM temp : parentObList) {
            list.add(
                    new RentItemOrderDeatilsTM(
                            temp.getOrderId(),
                            temp.getSerialNumber(),
                            temp.getTotalPrice(),
                            temp.getCustomerId(),
                            temp.getTotalDays(),
                            temp.getRentDate(),
                            temp.getReturnDate(),
                            "Rent"


                    )
            );
            System.out.println(temp);
        }
        for (CartTM cartTm : parentObList) {
            OderItem rentItem = new OderItem(

                    lblOId.getText(),
                    cartTm.getSerialNumber(),
                    cartTm.getCustomerId(),
                    cartTm.getModelId(),
                    cartTm.getCatogeryId(),
                    cartTm.getOneDyPrice(),
                    cartTm.getTotalPrice(),
                    totalDays,
                    list

            );
            item = rentItem;
        }

        System.out.println(txtDPrice.getText());

        if (new OderItemServiceController().saveOrderItem(item)) {

            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            setOrderIds();
            try {
                JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../view/reports/bilItem2.jrxml"));
                JasperReport compileReport = JasperCompileManager.compileReport(design);
                String totalPrice = lblTotal.getText();
                HashMap map2 = new HashMap();
                map2.put("totalPrice", totalPrice);

                ObservableList<CartTM> deatils = tblItem.getItems();
                JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map2, new JRBeanArrayDataSource(deatils.toArray()));
                JasperViewer.viewReport(jasperPrint, false);
            } catch (JRException e) {
                e.printStackTrace();
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

    }

    public void AddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AddCustomerForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.centerOnScreen();
        stage.show();

        txtCustId.clear();
        txtCustName.clear();
    }

    public void addOrderOnAction(ActionEvent actionEvent) {
        long dateOne = rentDate.getValue().toEpochDay();
        long dateTwo = returnDate.getValue().toEpochDay();
        totalDays = (int) Math.abs(dateOne - dateTwo);

        String orderId = lblOId.getText();
        String serialNumber = txtSId.getText();
        String customerId = txtCustId.getText();
        String modelId = txtMId.getText();
        String catogery = txtCId.getText();
        double oneDyPrice = Double.valueOf(txtDPrice.getText());
        Double totalPrice = oneDyPrice * totalDays;
        String rentDate = this.rentDate.getValue().toString();
        String returnDate = this.returnDate.getValue().toString();


        CartTM tm = new CartTM(
                orderId,
                serialNumber,
                customerId,
                modelId,
                catogery,
                oneDyPrice,
                totalPrice,
                totalDays,
                rentDate,
                returnDate

        );
        int rowNumber = isExists(tm);
        if (rowNumber == -1) {
            parentObList.add(tm);
            clear();
        } else {
            CartTM newTm = new CartTM(
                    orderId,
                    serialNumber,
                    customerId,
                    modelId,
                    catogery,
                    oneDyPrice,
                    totalPrice,
                    totalDays,
                    rentDate,
                    returnDate
            );

            parentObList.remove(rowNumber);
            parentObList.add(newTm);

        }
        tblItem.setItems(parentObList);
        calcuteCost();
    }


    private void setOrderIds() {
        try {
            lblOId.setText(new OderItemServiceController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void calcuteCost() {
        double ttl = 0;
        for (CartTM tm : parentObList) {
            ttl += tm.getOneDyPrice();
        }
        String format = new DecimalFormat("0.00").format(ttl);
        lblTotal.setText(String.valueOf(format));
    }

    private int isExists(CartTM cartTM) {
        for (int i = 0; i < parentObList.size(); i++) {
            if (cartTM.getSerialNumber().equals(parentObList.get(i).getSerialNumber())) {
                return i;
            }
        }
        return -1;
    }

    public void clearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void cancelOnAction(ActionEvent actionEvent) {

        parentObList.clear();
        clear();

    }

    public void clear() {
        txtDPrice.clear();
        txtMId.clear();
        txtCId.clear();
        txtIType.clear();
        txtCustName.clear();
        txtCustId.clear();
        txtSId.clear();
        cmbItems.setValue("Select  Item");
        cmbCustomer.setValue("Customer");
        cmbItems.getSelectionModel().getSelectedItem();
        cmbCustomer.getSelectionModel().getSelectedItem();
    }

    public void refreshCmbCustomer(MouseEvent mouseEvent) {
        if (!(txtCustId.getText().isEmpty())) {

        } else {
            cmbCustomer.getItems().clear();
            try {
                loadCustomers();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
