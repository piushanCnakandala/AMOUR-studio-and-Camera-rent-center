package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.PackageCategory;
import model.RentItemCategory;
import view.tm.PackageCategoryTM;
import view.tm.RentItemCategoryTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentItemCategoryServiceController implements RentItemCategoryService {
    @Override
    public boolean saveRentItemCategory(RentItemCategory rentItemCategory) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO `Rent_Item_Category` VALUES(?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,rentItemCategory.getItemCategoryId());
        stm.setObject(2,rentItemCategory.getItemCategoryName());
        return stm.executeUpdate()> 0;
    }

    @Override
    public ObservableList<RentItemCategoryTM> getRentItemCategory() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean removeRentItemCategory(String itemCategoryId) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE  FROM `Rent_Item_Category` WHERE item_c_id='" + itemCategoryId + "'").executeUpdate()> 0;
    }

    @Override
    public List<String> getAllCategory() throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Rent_Item_Category`").executeQuery();
        List<String> ids=new ArrayList<>();
        while (set.next()){
            ids.add(set.getString(1)
            );
        }
        return ids;
    }

    @Override
    public RentItemCategory passCategory(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM `Rent_Item_Category` WHERE item_c_id='" + id + "'").executeQuery();
        if (rst.next()) {
            return new RentItemCategory (
                    rst.getString(1),
                    rst.getString(2)
            );
        } else {
            return null;
        }
    }

}
