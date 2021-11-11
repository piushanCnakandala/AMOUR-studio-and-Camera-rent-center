package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.OderPackageDeatils;
import view.tm.RentItemOrderDeatilsTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhotogrphyServiceHistoryController {
    public JFXTextField txtSearchOrder;
    public TableView<OderPackageDeatils> tblPackage;
    public TableColumn colOid;
    public TableColumn colPid;
    public TableColumn colPcId;
    public TableColumn colCid;
    public TableColumn colPrice;
    public TableColumn colPhId;
    public TableColumn colDate;


    public ObservableList<OderPackageDeatils> getItemList() throws SQLException, ClassNotFoundException {
        ObservableList<OderPackageDeatils> observableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM `Package_Order_Deatils`";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            observableList.add(new OderPackageDeatils(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)


            ));
        }
        tblPackage.setItems(observableList);
        return observableList;
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<OderPackageDeatils>observableList=getItemList();
        colOid.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colPid.setCellValueFactory(new PropertyValueFactory<>("packageId"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("packagePrice"));
        colPcId.setCellValueFactory(new PropertyValueFactory<>("packageCatogeryId"));
       colCid.setCellValueFactory(new PropertyValueFactory<>("customerId"));
       colPhId.setCellValueFactory(new PropertyValueFactory<>("photographerId"));
       colDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
    }

    public List<OderPackageDeatils> searchRentHistory(String value) throws SQLException, ClassNotFoundException {
        List<OderPackageDeatils> oderPackageDeatils = new ArrayList<>();
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Package_Order_Deatils` WHERE o_id LIKE '%" +value+ "%'").executeQuery();
        while (set.next()) {
            oderPackageDeatils.add(new OderPackageDeatils(
                   set.getString(1),
                    set.getString(2),
                    set.getDouble(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6),
                    set.getString(7)
            ));
        }
        return oderPackageDeatils;
    }

    public void searchONaction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        List<OderPackageDeatils> rentOackageOrderDeatils =new PhotogrphyServiceHistoryController().searchRentHistory(txtSearchOrder.getText());
        ObservableList<OderPackageDeatils>observableList=FXCollections.observableArrayList();
        for (OderPackageDeatils temp :rentOackageOrderDeatils) {
            observableList.add(new OderPackageDeatils(
                    temp.getOrderId(),
                    temp.getPackageId(),
                    temp.getPackagePrice(),
                    temp.getPackageCatogeryId(),
                    temp.getCustomerId(),
                    temp.getOrderDate(),
                    temp.getPhotographerId()
            ));

        }
       tblPackage.setItems(observableList);
    }
}
