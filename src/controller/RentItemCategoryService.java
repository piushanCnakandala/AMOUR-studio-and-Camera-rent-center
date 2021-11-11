package controller;

import javafx.collections.ObservableList;
import model.Item;
import model.PackageCategory;
import model.RentItemCategory;
import view.tm.RentItemCategoryTM;

import java.sql.SQLException;
import java.util.List;

public interface RentItemCategoryService {
    boolean saveRentItemCategory(RentItemCategory rentItemCategory) throws SQLException, ClassNotFoundException;
  ObservableList<RentItemCategoryTM>getRentItemCategory() throws SQLException, ClassNotFoundException;
    boolean removeRentItemCategory (String itemlId) throws SQLException, ClassNotFoundException;
    public List<String> getAllCategory() throws SQLException, ClassNotFoundException;
    RentItemCategory passCategory(String id) throws SQLException, ClassNotFoundException;
}
