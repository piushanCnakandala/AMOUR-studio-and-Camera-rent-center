package controller;

import javafx.collections.ObservableList;
import model.RentItemCategory;
import model.RentItemModels;
import view.tm.RentItemCategoryTM;

import java.sql.SQLException;
import java.util.List;

public interface RentItemModelsService {
    boolean saveRentItemModels(RentItemModels itemModel) throws SQLException, ClassNotFoundException;
    ObservableList<RentItemCategoryTM> getRentItemModels() throws SQLException, ClassNotFoundException;
    boolean removeRentItemModels (String itemModelId) throws SQLException, ClassNotFoundException;
    public List<String> getAllModels() throws SQLException, ClassNotFoundException;
    RentItemModels passModels(String id) throws SQLException, ClassNotFoundException;
}
