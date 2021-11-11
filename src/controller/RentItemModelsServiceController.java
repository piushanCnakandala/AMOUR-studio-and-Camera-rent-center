package controller;

import db.DbConnection;
import javafx.collections.ObservableList;
import model.RentItemCategory;
import model.RentItemModels;
import view.tm.RentItemCategoryTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentItemModelsServiceController implements RentItemModelsService{
    @Override
    public boolean saveRentItemModels(RentItemModels rentItemModel) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO `Rent_Item_Model` VALUES(?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,rentItemModel.getItemModelId());
        stm.setObject(2,rentItemModel.getGetItemModelName());
        return stm.executeUpdate()> 0;
    }

    @Override
    public ObservableList<RentItemCategoryTM> getRentItemModels() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean removeRentItemModels(String itemModelId) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE  FROM `Rent_Item_Model` WHERE item_m_id='" + itemModelId + "'").executeUpdate()> 0;
    }

    @Override
    public List<String> getAllModels() throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Rent_Item_Model`").executeQuery();
        List<String> ids=new ArrayList<>();
        while (set.next()){
            ids.add(set.getString(1)
            );
        }
        return ids;

    }

    @Override
    public RentItemModels passModels(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM `Rent_Item_Model` WHERE item_m_id='" + id + "'").executeQuery();
        if (rst.next()) {
            return new RentItemModels(
                    rst.getString(1),
                    rst.getString(2)
            );
        } else {
            return null;
        }
    }
}
