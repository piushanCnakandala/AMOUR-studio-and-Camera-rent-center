package controller;

import model.Item;
import model.PackageCategory;
import model.Packages;
import view.tm.ItemTm;
import view.tm.PackagesTM;

import java.sql.SQLException;
import java.util.List;

public interface ItemService {
    boolean saveItem(Item item) throws SQLException, ClassNotFoundException;
   Item getItem(String item) throws SQLException, ClassNotFoundException;
    boolean removeItem(String serial) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemTm itemTm) throws SQLException, ClassNotFoundException;
    public List<String> getAllItems() throws SQLException, ClassNotFoundException;
  Item passItems(String packages) throws SQLException, ClassNotFoundException;
}
