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
import model.Customer;
import util.ValidationUtil;
import view.tm.CustomerTM;
import view.tm.ItemTm;
import view.tm.PackagesTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ManagerCustomerFormController {
    public JFXTextField txtCusstId;
    public JFXTextField txtCrustName;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCustNic;
    public JFXTextField txtTp;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCid;
    public TableColumn colCname;
    public TableColumn colCAddress;
    public TableColumn colNic;
    public TableColumn colTnumber;
    public JFXButton btnAdd;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern customerIdPatton = Pattern.compile("^(CUST-Cust-|cust-)[0-9]{4}$");
    Pattern customerNamePatton = Pattern.compile("^[A-z ]{3,30}$");
    Pattern customerAddressPatton = Pattern.compile("^[A-z 0-9 \\/\\,]{2,50}[A-z 0-9]{1,50}$");
    Pattern customerNicPatton = Pattern.compile("^[0-9]{10,12}$");
    Pattern customerTpPatton = Pattern.compile("^[0-9]{10}$");



   public  void initialize(){
btnAdd.setDisable(true);
       try {
           showCustomers();
           storeValidation();
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }


   }

   public void showCustomers() throws SQLException, ClassNotFoundException {
       ObservableList<CustomerTM> observableList = getCustomerList();
       colCid.setCellValueFactory(new PropertyValueFactory<>("customerId"));
      colCname.setCellValueFactory(new PropertyValueFactory<>("customerName"));
       colCAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
       colNic.setCellValueFactory(new PropertyValueFactory<>("customerNic"));
       colTnumber.setCellValueFactory(new PropertyValueFactory<>("customerTPNumber"));
   }


    public ObservableList<CustomerTM> getCustomerList() throws SQLException, ClassNotFoundException {
        ObservableList<CustomerTM> observableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM Customer";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            observableList.add(new CustomerTM(
                  rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
       tblCustomer.setItems(observableList);
        return observableList;
    }

    public void addCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(txtCusstId.getText(),txtCrustName.getText(),txtCustAddress.getText(),txtCustNic.getText(),txtTp.getText());

        if (new CustomerServiceController().saveCustomer(customer)){
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            clear();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }


    public void clearOnAction(ActionEvent actionEvent) {
       clear();
    }


    public void removeCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       CustomerTM customerTM=tblCustomer.getSelectionModel().getSelectedItem();
       String customerId=customerTM.getCustomerId();
       if (new CustomerServiceController().removeCustomer(customerId)){
           new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete Selected Item").show();
           showCustomers();
       }else {
           new Alert(Alert.AlertType.WARNING, "Try Again").show();
       }
       clear();
    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
     CustomerTM customerTM = new CustomerTM(
               txtCusstId.getText(),
             txtCrustName.getText(),
             txtCustAddress.getText(),
             txtCustNic.getText(),
             txtTp.getText()
        );
        if (new CustomerServiceController().updateCustomer(customerTM)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update").show();
            showCustomers();
        } else {
            new Alert(Alert.AlertType.WARNING, "try again").show();
        }

    }

    public void clear(){
       txtCusstId.clear();
       txtCrustName.clear();
       txtCustAddress.clear();
       txtCustNic.clear();
       txtTp.clear();
    }

    public void mouseClckSelectOnActiopn(MouseEvent mouseEvent) {
       CustomerTM customerTM=null;

       customerTM=tblCustomer.getSelectionModel().getSelectedItem();
       txtCusstId.setText(customerTM.getCustomerId());
       txtCrustName.setText(customerTM.getCustomerName());
       txtCustAddress.setText(customerTM.getCustomerNic());
       txtCustNic.setText(customerTM.getCustomerNic());
       txtTp.setText(customerTM.getCustomerTPNumber());
    }

    private void storeValidation() {
        map.put(txtCusstId, customerIdPatton);
        map.put(txtCrustName, customerNamePatton);
        map.put(txtCustAddress, customerAddressPatton);
        map.put(txtCustNic, customerNicPatton);
        map.put(txtTp, customerTpPatton);
    }

    public void OnKeyReleased(KeyEvent keyEvent) {
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
