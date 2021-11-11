package controller;

import db.DbConnection;
import model.Item;
import model.Packages;
import model.User;
import view.tm.PackagesTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageServiceController implements PackageService{


    @Override
    public boolean savePackage(Packages packages) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO Package VALUES(?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,packages.getPackageId());
        stm.setObject(2,packages.getPackageType());
        stm.setObject(3,packages.getPackageDeatils());
        stm.setObject(4,packages.getPrice());
        stm.setObject(5,packages.getPackageCategoryId());

        return stm.executeUpdate()> 0;
    }

    @Override
    public Packages getPackages(String packages) throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Package WHERE p_id='" + packages + "'").executeQuery();
        if (set.next()){
            return new Packages(set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4),
                    set.getString(5));
        }else {

        }
        return null;
    }

    @Override
    public boolean removePackage(String packageId) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE  FROM Package WHERE p_id='" + packageId + "'").executeUpdate()> 0;

    }

    @Override
    public boolean updatePackage(PackagesTM packages) throws SQLException, ClassNotFoundException {
        PreparedStatement rst = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Package  SET p_type=?,p_deatils=?,p_price=?,p_c_id=? WHERE p_id=?");
        rst.setObject(1, packages.getPackageType());
        rst.setObject(2, packages.getPackageDeatils());
        rst.setObject(3, packages.getPrice());
        rst.setObject(4, packages.getPackageCategoryId());
        rst.setObject(5, packages.getPackageId());

        return rst.executeUpdate() > 0;
    }



    public List<String> getAllPackage() throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Package").executeQuery();
        List<String> ids=new ArrayList<>();
        while (set.next()){
            ids.add(set.getString(1)
            );
        }
        return ids;
    }

    public Packages passPackage(String packages) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Package WHERE p_id='" +packages + "'").executeQuery();
        if (rst.next()) {
            return new Packages(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5)
);
        } else {
            return null;
        }
    }
}