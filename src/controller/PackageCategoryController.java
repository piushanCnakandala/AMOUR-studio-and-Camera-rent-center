package controller;

import db.DbConnection;
import model.PackageCategory;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackageCategoryController implements PackageCategoryService{
    @Override
    public boolean savePackageCategory(PackageCategory packageCategory) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO PackageCategory VALUES(?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,packageCategory.getPcId());
        stm.setObject(2,packageCategory.getPcName());
        return stm.executeUpdate()> 0;
    }

    @Override
    public PackageCategory getPackageCategory(String packageCategory) throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM PackageCategory WHERE p_c_id='" + packageCategory + "'").executeQuery();
        if (set.next()){
            return new PackageCategory(set.getString(1),
                    set.getString(2));
        }else {

        }
        return null;
    }

    @Override
    public boolean removePackageCategory(String packageCategoryId) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE  FROM PackageCategory WHERE p_c_id='" + packageCategoryId + "'").executeUpdate()> 0;
    }

    @Override
    public List<String> getAllCategory() throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM PackageCategory").executeQuery();
        List<String> ids=new ArrayList<>();
        while (set.next()){
            ids.add(set.getString(1)
            );
        }
        return ids;
    }

    @Override
    public PackageCategory passCategory(String id) throws SQLException, ClassNotFoundException {
            ResultSet rst = DbConnection.getInstance().getConnection().
                    prepareStatement("SELECT * FROM PackageCategory WHERE  p_c_id='" + id + "'").executeQuery();
            if (rst.next()) {
                return new PackageCategory(
                        rst.getString(1),
                        rst.getString(2)

                );
            } else {
                return null;
            }
    }

}
