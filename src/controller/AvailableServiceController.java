package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import view.tm.ItemTm;
import view.tm.PackagesTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvailableServiceController {


    public ObservableList<ItemTm> getItemList() throws SQLException, ClassNotFoundException {
        ObservableList<ItemTm> observableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT i.serial_number,i.item_type,i.item_price,c.item_c_id,m.item_m_id FROM `Rent_Item_Category` c,`Rent_Item_Model` m,`Rent_Item` i WHERE i.status='Available' AND c.item_c_id=i.item_c_id AND m.item_m_id=i.item_m_id GROUP  BY i.serial_number";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            observableList.add(new ItemTm(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5)

            ));
        }
        return observableList;
    }


    public List<ItemTm> searchRentItem(String value) throws SQLException, ClassNotFoundException {
        List<ItemTm> itemTm = new ArrayList<>();
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT i.serial_number,i.item_type,i.item_price,c.item_c_name,m.item_m_name FROM `Rent_Item_Category` c,`Rent_Item_Model` m,`Rent_Item` i  WHERE (i.status='Available'AND c.item_c_id=i.item_c_id AND m.item_m_id=i.item_m_id)AND  concat(i.serial_number,c.item_c_name,i.item_type,m.item_m_name,i.item_price )LIKE '%" + value + "%'").executeQuery();
        while (set.next()) {
            itemTm.add(new ItemTm(
                    set.getString(1),
                    set.getString(2),
                    set.getDouble(3),
                    set.getString(4),
                    set.getString(5)
            ));
        }
        return itemTm;
    }

    public ObservableList<PackagesTM> getPackageList() throws SQLException, ClassNotFoundException {
        ObservableList<PackagesTM> packagesTMObservableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT p.p_id,p.p_type,p.p_deatils,p.p_price,c.p_c_name FROM Package p\n" +
                "INNER JOIN PackageCategory c\n" +
                "ON p.p_c_id=c.p_c_id;";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            packagesTMObservableList.add(new PackagesTM(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5)

            ));
        }
        return packagesTMObservableList;
    }

    public List<PackagesTM> searchPackages(String text) throws SQLException, ClassNotFoundException {
        List<PackagesTM> packagesTMS = new ArrayList<>();
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT p.p_id,p.p_type,p.p_deatils,p.p_price,c.p_c_name FROM Package p\n" +
                "INNER JOIN PackageCategory c\n" +
                "ON p.p_c_id=c.p_c_id\n" +
                "WHERE CONCAT(p.p_id,p.p_type,p.p_deatils,p.p_price,c.p_c_name) LIKE'%"+text+"%';\n");
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            packagesTMS.add(new PackagesTM(
                    rst.getString(1),
                    rst.getString(3),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5)
            ));
        }
        return packagesTMS;
    }


    public String getCountPhotographers() throws SQLException, ClassNotFoundException {
        String count = null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(photographer_id) FROM Photographer ").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

    public String getCountPackage() throws SQLException, ClassNotFoundException {
        String count = null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(p_id ) FROM Package ").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

    public String getCountItems() throws SQLException, ClassNotFoundException {
        String count = null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(serial_number) FROM `Rent_Item`  ").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

    public String getCountAvailableItems() throws SQLException, ClassNotFoundException {
        String count = null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(serial_number) FROM `Rent_Item` WHERE status='"+"Available"+"' ").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

    public String getCountAvailableCustomer() throws SQLException, ClassNotFoundException {
        String count = null;
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT count(customer_id ) FROM Customer  ").executeQuery();
        while (rst.next()) {
            count = rst.getString(1);
        }
        return count;
    }

}
