package controller;

import model.OderItem;
import model.OderPackageDeatils;
import model.OrderPackage;
import model.RentItemOrderDeatils;
import view.tm.RentItemOrderDeatilsTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderItemSrevice {
    public boolean saveOrderItem(OderItem oderItem) throws SQLException, ClassNotFoundException;
    public String getOrderId() throws SQLException, ClassNotFoundException;
    boolean saveOderDeatils(ArrayList<RentItemOrderDeatilsTM> orderDeatils,String orderId) throws SQLException, ClassNotFoundException;
}
