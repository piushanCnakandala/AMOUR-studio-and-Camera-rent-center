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
import model.OderItem;
import model.RentItemOrderDeatils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.ValidationUtil;
import view.tm.CartTM;
import view.tm.RentItemOrderDeatilsTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ReturnItemFormController {
    public TableView<RentItemOrderDeatilsTM> tblRentItem;
    public TableColumn colOId;
    public TableColumn colSNumber;
    public TableColumn colCustId;
    public TableColumn colRetuenDate;
    public TableColumn colRentDate;
    public TableColumn colStaus;
    public TableView<RentItemOrderDeatilsTM> tblRentItem1;
    public TableColumn colOId1;
    public TableColumn colSNumber1;
    public TableColumn colCustId1;
    public TableColumn colRentDate1;
    public TableColumn colRetuenDate1;
    public JFXTextField txtOid;
    public JFXTextField txtCid;
    public JFXTextField txtSnumber;
    public JFXTextField txtRentDate;
    public JFXTextField txtfINE;
    public JFXTextField txtxReturnDate;
    public Label lblTotal;
    public JFXTextField txtSeach;
    public JFXButton btnAdd;


    LinkedHashMap<TextField , Pattern> map = new LinkedHashMap();
    Pattern finePatton =Pattern.compile("^[1-9][0-9]*([.][0-9]{2})?$");

    public final ObservableList<RentItemOrderDeatilsTM> orderDeatils =FXCollections.observableArrayList();

    public void  initialize() throws SQLException, ClassNotFoundException {
        btnAdd.setDisable(true);
        listOfRentItemTable();
        storeValidation();
        colOId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
       colSNumber.setCellValueFactory(new PropertyValueFactory<>("serialNum"));
       colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colRentDate.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
       colRetuenDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colStaus.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

    }

    public  void listOfRentItemTable() throws SQLException, ClassNotFoundException {
        ObservableList<RentItemOrderDeatilsTM> itemList = new RetunServiceController().getItemList();
     colOId1.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colSNumber1.setCellValueFactory(new PropertyValueFactory<>("serialNum"));
       colCustId1.setCellValueFactory(new PropertyValueFactory<>("custId"));
       colRentDate1.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
     colRetuenDate1.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

     tblRentItem1.setItems(itemList);
    }

    public void searchONaction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        List<RentItemOrderDeatilsTM> rentItemOrderDeatils = new RetunServiceController().searchRentHistory(txtSeach.getText());
        ObservableList<RentItemOrderDeatilsTM> observableList=FXCollections.observableArrayList();
        for (RentItemOrderDeatilsTM temp :rentItemOrderDeatils) {
            observableList.add(new RentItemOrderDeatilsTM(
                    temp.getOrderId(),
                    temp.getSerialNum(),
                    temp.getCustId(),
                    temp.getRentDate(),
                    temp.getReturnDate()
            ));

        }
        tblRentItem1.setItems(observableList);

    }

    public void clickOnAction(MouseEvent mouseEvent) {
        RentItemOrderDeatilsTM rentItemOrderDeatils= null;

        rentItemOrderDeatils=tblRentItem1.getSelectionModel().getSelectedItem();
        txtOid.setText(rentItemOrderDeatils.getOrderId());
        txtSnumber.setText(rentItemOrderDeatils.getSerialNum());
        txtCid.setText(rentItemOrderDeatils.getCustId());
        txtRentDate.setText(rentItemOrderDeatils.getRentDate());
        txtxReturnDate.setText(rentItemOrderDeatils.getReturnDate());
    }

    public void addOnAction(ActionEvent actionEvent) {
        RentItemOrderDeatilsTM rentItemOrderDeatils=new RentItemOrderDeatilsTM(
                txtOid.getText(),
                txtSnumber.getText(),
               Double.parseDouble(txtfINE.getText()),
                txtCid.getText(),
                txtRentDate.getText(),
                txtxReturnDate.getText()

        );
        int exists = isExists(rentItemOrderDeatils);
        if (exists == -1){
            orderDeatils.add(rentItemOrderDeatils);
        }else{
            RentItemOrderDeatilsTM newTm=new RentItemOrderDeatilsTM(

                    txtOid.getText(),
                    txtSnumber.getText(),
                    Double.parseDouble(txtfINE.getText()),
                    txtCid.getText(),
                    txtRentDate.getText(),
                    txtxReturnDate.getText()
            );
            orderDeatils.remove(exists);
            orderDeatils.add(newTm);
        }
        tblRentItem.setItems(orderDeatils);
        calcuteCost();
    }
    double total;
    private void calcuteCost() {
        double ttl=0;
        for (RentItemOrderDeatilsTM tm:orderDeatils){
            ttl+=tm.getTotalPrice();
        }
        String format =new DecimalFormat("0.00").format(ttl);
        lblTotal.setText(format+"/=");
        total=Double.parseDouble(format);
    }

    private  int isExists(RentItemOrderDeatilsTM rentItemOrderDeatils){
        for (int i=0;i<orderDeatils.size(); i++){
            if (rentItemOrderDeatils.getOrderId().equals(orderDeatils.get(i).getOrderId())){
                return i;
            }
        }
        return  -1;
    }
private  OderItem rentUpdate;
    public void returnOnAction(ActionEvent actionEvent) throws JRException {
        ArrayList<RentItemOrderDeatilsTM> list=new ArrayList<>();
        for (RentItemOrderDeatilsTM orderDeatilsTM:orderDeatils){
            list.add(new RentItemOrderDeatilsTM(

                    orderDeatilsTM.getOrderId(),
                    orderDeatilsTM.getSerialNum(),
                     orderDeatilsTM.getTotalPrice(),
                    orderDeatilsTM.getCustId(),
                    orderDeatilsTM.getTotalDate(),
                    orderDeatilsTM.getRentDate(),
                    orderDeatilsTM.getReturnDate(),
                    "Returened"
            ));
        }
        for (RentItemOrderDeatilsTM tm:list){
            OderItem update=new OderItem(
                    tm.getOrderId(),
                   total,
                    list
            );
            rentUpdate=update;
        }
        System.out.println("obj "+rentUpdate);
        if (new RetunServiceController().updateToReturned(rentUpdate) ){
            new Alert(Alert.AlertType.INFORMATION,"Done").show();
            JasperDesign design = null;
            try {
                design = JRXmlLoader.load(this.getClass().getResourceAsStream("../view/reports/returnbil.jrxml"));
            } catch (JRException e) {
                e.printStackTrace();
            }
            JasperReport compileReport = JasperCompileManager.compileReport(design);
            String totalPrice = lblTotal.getText();
            HashMap map2 = new HashMap();
            map2.put("totalPrice", totalPrice);

            ObservableList<RentItemOrderDeatilsTM> deatils =tblRentItem.getItems();
            JasperPrint jasperPrint = null;
            try {
                jasperPrint = JasperFillManager.fillReport(compileReport, map2, new JRBeanArrayDataSource(deatils.toArray()));
            } catch (JRException e) {
                e.printStackTrace();
            }
            JasperViewer.viewReport(jasperPrint, false);
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }
    }

    private void storeValidation() {
        map.put(txtfINE, finePatton);
    }

    public void onKeyRelesedOnAction(KeyEvent keyEvent) {
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
