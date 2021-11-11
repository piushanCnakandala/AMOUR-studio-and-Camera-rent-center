package controller;

import db.DbConnection;
import model.OderItem;
import view.tm.RentItemOrderDeatilsTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OderItemServiceController implements OrderItemSrevice {


    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Rent_Item_Order` ORDER BY o_id DESC LIMIT 1").executeQuery();

        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId += 1;

            if (tempId < 10) {
                return "O-00" + tempId;
            } else if (tempId < 100) {
                return "O-0" + tempId;
            } else {
                return "O" + tempId;
            }
        } else {
            return "O-000";
        }
    }

    @Override
    public boolean saveOrderItem(OderItem oderItem) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            String query = "INSERT INTO `Rent_Item_Order` VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement stm = con.prepareStatement(query);
            stm.setObject(1, oderItem.getOrderId());
            stm.setObject(2, oderItem.getSerialNumber());
            stm.setObject(3, oderItem.getOneDyPrice());
            stm.setObject(4, oderItem.getTotalDays());
            stm.setObject(5, oderItem.getCustomerId());
            stm.setObject(6, oderItem.getCatogeryId());
            stm.setObject(7, oderItem.getModelId());
            stm.setObject(8, oderItem.getTotalPrice());

            System.out.println("cust :"+oderItem.getCustomerId()+" sid :"+oderItem.getSerialNumber());

            if (stm.executeUpdate() > 0) {
                if (saveOderDeatils(oderItem.getRentItemOrderDeatils(),oderItem.getOrderId())) {
                    con.commit();
                    return true;
                } else {
                    con.rollback();
                    return false;
                }
            } else {
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }




    @Override
    public boolean saveOderDeatils(ArrayList<RentItemOrderDeatilsTM> orderDeatils,String orderId) throws SQLException, ClassNotFoundException {

        for (RentItemOrderDeatilsTM temp : orderDeatils) {
            System.out.println(temp+" jewwwa");
            System.out.println(temp.getSerialNum()+" sn");
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `Rent_Item_Order_Deatils` VALUES(?,?,?,?,?,?,?,?)");
            stm.setObject(1, orderId);
            stm.setObject(2, temp.getSerialNum());
            stm.setObject(3, temp.getTotalPrice());
            stm.setObject(4, temp.getCustId());
            stm.setObject(5, temp.getTotalDate());
            stm.setObject(6, temp.getRentDate());
            stm.setObject(7, temp.getReturnDate());
            stm.setObject(8, temp.getStatus());
            if (stm.executeUpdate() > 0) {

                if (updateStatus(temp.getSerialNum(), "Rent")) {

                } else {
                    return false;
                }
            } else {
                return false;
            }

        }
        return true;
    }

    private boolean updateStatus(String serialNumber, String status) throws SQLException, ClassNotFoundException {
        System.out.println("Awaa..........");
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE `Rent_Item` SET status ='"+status+"' WHERE serial_number ='" + serialNumber + "'");
        return stm.executeUpdate() > 0;
    }


}

