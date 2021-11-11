package controller;

import db.DbConnection;
import model.OderPackageDeatils;
import model.OrderPackage;
import model.Packages;
import view.tm.PackagesTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderPackageSearviceController implements OderPackageSearvice{
    @Override
    public boolean savePackage(OrderPackage orderPackage)  {
        Connection con= null;
        try {
            con = DbConnection.getInstance().getConnection();
con.setAutoCommit(false);
            String query="INSERT INTO `Package_Order` VALUES(?,?,?,?,?,?)";
            PreparedStatement stm=con.prepareStatement(query);
            stm.setObject(1,orderPackage.getOrderId());
            stm.setObject(2,orderPackage.getPackageId());
            stm.setObject(3,orderPackage.getPackageCatogeryId());
            stm.setObject(4,orderPackage.getPhotographerId());
            stm.setObject(5,orderPackage.getPackagePrice());
            stm.setObject(6,orderPackage.getCustomerId());

            if(stm.executeUpdate()> 0) {
                if (savePackageDeatils(orderPackage.getOrderDeatiles())){
                    con.commit();
                    return true;
                }else {
                    con.rollback();
                    return false;
                }
            }else {
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

return false;
    }

    @Override
    public boolean savePackageDeatils(ArrayList<OderPackageDeatils> orderPackage) throws SQLException, ClassNotFoundException {
        for (OderPackageDeatils temp : orderPackage) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `Package_Order_Deatils` VALUES(?,?,?,?,?,?,?)");
stm.setObject(1,temp.getOrderId());
stm.setObject(2,temp.getPackageId());
stm.setObject(3,temp.getPackagePrice());
stm.setObject(4,temp.getPackageCatogeryId());
stm.setObject(5,temp.getCustomerId());
stm.setObject(6,temp.getOrderDate());
stm.setObject(7,temp.getPhotographerId());
return stm.executeUpdate()>0;
        }
        return  true;
    }


    @Override
    public Packages getPackage(String packages) throws SQLException, ClassNotFoundException {

        return null;
    }

    @Override
    public boolean removePackage(String packageId) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE  FROM `Package_Order` WHERE o_id='" + packageId + "'").executeUpdate()> 0;

    }

    @Override
    public boolean updatePackage(PackagesTM packagesTM) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Package_Order` ORDER BY o_id DESC LIMIT 1").executeQuery();

        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId += 1;

            if (tempId < 9) {
                return "O-00" + tempId;
            } else if (tempId < 99) {
                return "O-0" + tempId;
            } else {
                return "O" + tempId;
            }
        } else {
            return "O-000";
        }
    }

}

