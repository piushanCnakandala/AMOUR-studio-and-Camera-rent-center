package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OderItem;
import view.tm.RentItemOrderDeatilsTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetunServiceController {
    public ObservableList<RentItemOrderDeatilsTM> getItemList() throws SQLException, ClassNotFoundException {
        ObservableList<RentItemOrderDeatilsTM> observableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM `Rent_Item_Order_Deatils` WHERE status='" + "Rent" + "'";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            observableList.add(new RentItemOrderDeatilsTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(4),
                    rst.getString(6),
                    rst.getString(7)

            ));
        }
        return observableList;
    }

    public List<RentItemOrderDeatilsTM> searchRentHistory(String value) throws SQLException, ClassNotFoundException {
        List<RentItemOrderDeatilsTM> rentItemOrderDeatils = new ArrayList<>();
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Rent_Item_Order_Deatils` WHERE status='" + "Rent" + "' AND o_id LIKE '%" + value + "%'").executeQuery();
        while (set.next()) {
            rentItemOrderDeatils.add(new RentItemOrderDeatilsTM(
                    set.getString(1),
                    set.getString(2),
                    set.getString(4),
                    set.getString(6),
                    set.getString(7)
            ));
        }
        return rentItemOrderDeatils;
    }




    public boolean updateToReturned(OderItem oderItem) {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement("UPDATE `Rent_Item_Order` SET total_price = (total_price+?) WHERE o_id=?");
            stm.setObject(1, oderItem.getTotalPrice());
            stm.setObject(2, oderItem.getOrderId());
            System.out.println("rp : " + oderItem.getOrderId() + " " + oderItem.getTotalPrice());
            if (stm.executeUpdate() > 0) {
                if (updateDeatil(oderItem.getOrderId(), oderItem.getRentItemOrderDeatils())) {
                    System.out.println("b comit");
                    connection.commit();
                    System.out.println("a comit");
                    return true;
                } else {
                    System.out.println("wtf");
                    connection.rollback();
                    return false;
                }
            } else {
                System.out.println("wtf2");
                connection.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public boolean updateDeatil(String Oid, ArrayList<RentItemOrderDeatilsTM> list) throws SQLException, ClassNotFoundException {
        System.out.println("up");
        for (RentItemOrderDeatilsTM tm : list) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `Rent_Item_Order_Deatils` SET status=?, total_price=(total_price+?) WHERE o_id=? AND serial_number=?");
            stm.setObject(1, tm.getStatus());
            stm.setObject(2, tm.getTotalPrice());
            stm.setObject(3, Oid);
            stm.setObject(4, tm.getSerialNum());
            //stm.setObject(5,tm.getOrderId());
            System.out.println("orderId : " + tm.getOrderId() + " s:" + tm.getStatus() + " " + tm.getSerialNum());
            if (stm.executeUpdate() > 0) {
                if (updateStatus(tm.getSerialNum(), "Available")) {
                    System.out.println("mmmmmmmm");
                } else {
                    System.out.println("re");
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean updateStatus(String serialNumber, String status) throws SQLException, ClassNotFoundException {
        System.out.println("las SN: " + serialNumber + "  ss: " + status);
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `Rent_Item` SET status='" + status + "'WHERE  serial_number='" + serialNumber + "'");

        return stm.executeUpdate() > 0;
    }
}
