package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;
import util.ValidationUtil;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddCustomerFormController {

    public JFXTextField txtCrustName;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCustNic;
    public JFXTextField txtTp;
    public JFXTextField txtCusstId;
    public JFXButton btnAddCust;

    LinkedHashMap<TextField ,Pattern > map = new LinkedHashMap();
    Pattern customerIdPatton = Pattern.compile("^(Cust-|cust-)[0-9]{4}$");
    Pattern customerNamePatton = Pattern.compile("^[A-z ]{3,30}$");
    Pattern customerAddressPatton = Pattern.compile("^[A-z 0-9 \\/\\,]{2,50}[A-z 0-9]{1,50}$");
    Pattern customerNicPatton = Pattern.compile("^[0-9]{10,12}$");
    Pattern customerTpPatton = Pattern.compile("^[0-9]{10}$");


    public void addCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(txtCusstId.getText(), txtCrustName.getText(), txtCustAddress.getText(), txtCustNic.getText(), txtTp.getText());

        if (new CustomerServiceController().saveCustomer(customer)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }


    public void initialize() {
        btnAddCust.setDisable(true);
        storeValidation();
    }

    private void storeValidation() {
        map.put(txtCusstId, customerIdPatton);
        map.put(txtCrustName, customerNamePatton);
        map.put(txtCustAddress, customerAddressPatton);
        map.put(txtCustNic, customerNicPatton);
        map.put(txtTp, customerTpPatton);
    }


    public void textFiieldsOnKeyReleased(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnAddCust);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();

            }
        }





 /*String customerIdRegEx = "^(Cust-|cust-)[0-9]{4}$";
        String customerNameRegEx = "^[A-z ]{3,30}$";
        String customerAddressRegEx = "^[A-z ]{3,30}$";
        String customerNicRegEx = "^[0-9]{10,12}$";
        String customerTpRegEx = "^[0-9]{10}$";


        Pattern customerIdPatton = Pattern.compile(customerIdRegEx);
        Pattern customerNamePatton = Pattern.compile(customerNameRegEx);
        Pattern customerAddressPatton = Pattern.compile(customerAddressRegEx);
        Pattern customerNicPatton = Pattern.compile(customerNicRegEx);
        Pattern customerTpPatton = Pattern.compile(customerTpRegEx);

        if (keyEvent.getCode() == KeyCode.ENTER){
        String typedCustomerId = txtCusstId.getText();
        if (customerIdPatton.matcher(typedCustomerId).matches()) {
            txtCusstId.setStyle("-fx-background-color: blue");
            txtCrustName.requestFocus();

            String typedCustomerName = txtCrustName.getText();
            if (customerNamePatton.matcher(typedCustomerName).matches()) {
                txtCrustName.setStyle("-fx-background-color: blue");
                txtCustAddress.requestFocus();

                String typedCustomerAddress = txtCustAddress.getText();
                if (customerAddressPatton.matcher(typedCustomerAddress).matches()) {
                    txtCustAddress.setStyle("-fx-background-color: blue");
                    txtCustNic.requestFocus();


                String typedCustomerNic = txtCustNic.getText();
                if (customerNicPatton.matcher(typedCustomerNic).matches()) {
                    txtCustNic.setStyle("-fx-background-color: blue");
                    txtTp.requestFocus();

                    String typedCustomerTp = txtTp.getText();
                    if (customerTpPatton.matcher(typedCustomerTp).matches()) {
                        txtTp.setStyle("-fx-background-color: blue");
                    }else {
                        txtTp.setStyle("-fx-background-color: red;");
                        txtTp.requestFocus();
                    }

                    }else {
                        txtCustNic.setStyle("-fx-background-color: red;");
                    }


                }else {
                    txtCustAddress.setStyle("-fx-background-color: red;");
                    txtCustAddress.requestFocus();
                }

            }else {
                txtCrustName.setStyle("-fx-background-color:  red");
                txtCrustName.requestFocus();
            }

        }else {

            txtCusstId.setStyle("-fx-background-color:  red");
            txtCusstId.requestFocus();
        }
        }*/


    }


}
