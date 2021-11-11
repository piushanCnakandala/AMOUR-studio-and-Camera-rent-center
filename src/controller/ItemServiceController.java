package controller;

import db.DbConnection;
import model.Item;
import model.Packages;
import model.RentItemCategory;
import view.tm.ItemTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceController implements ItemService{

    @Override
    public boolean saveItem(Item item) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO `Rent_Item` VALUES(?,?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,item.getSerialNumber());
        stm.setObject(2,item.getType());
        stm.setObject(3,item.getPrice());
        stm.setObject(4,item.getCategoryId());
        stm.setObject(5,item.getModelId());
        stm.setObject(6,item.getText());
        return stm.executeUpdate()> 0;

    }

    @Override
    public Item getItem(String item) throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Rent_Item` WHERE serial_number='" + item + "'").executeQuery();
        if (set.next()){
            return new Item(set.getString(1),
                    set.getString(2),
                    set.getDouble(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6)

            );

        }
        return null;
    }

    @Override
    public boolean removeItem(String serial) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE  FROM   `Rent_Item` WHERE serial_number='" + serial + "'").executeUpdate()> 0;
    }

    @Override
    public boolean updateItem(ItemTm itemTm) throws SQLException, ClassNotFoundException {
        PreparedStatement rst = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `Rent_Item`  SET item_type=?,item_price=?,item_c_id=?,item_m_id=?,status=? WHERE serial_number=?");
        rst.setObject(1, itemTm.getType());
        rst.setObject(2, itemTm.getPrice());
        rst.setObject(3, itemTm.getCategoryId());
        rst.setObject(4, itemTm.getModelId());
        rst.setObject(5,itemTm.getText());
        rst.setObject(6,itemTm.getSerialNumber());

        return rst.executeUpdate() > 0;

    }

    @Override
    public List<String> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Rent_Item` WHERE status ='"+"Available"+"' ").executeQuery();
        List<String> ids=new ArrayList<>();
        while (set.next()){
            ids.add(set.getString(1)
            );
        }
        return ids;
    }

    @Override
    public Item passItems(String items) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM `Rent_Item` WHERE serial_number='" +items + "'").executeQuery();
        if (rst.next()) {
            return new Item(
                   rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
        } else {
            return null;
        }
    }
}
