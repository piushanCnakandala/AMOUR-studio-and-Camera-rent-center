package controller;

import model.PackageCategory;
import model.Photographer;

import java.sql.SQLException;
import java.util.List;

public interface PackageCategoryService {
    boolean savePackageCategory(PackageCategory packageCategory ) throws SQLException, ClassNotFoundException;
    PackageCategory getPackageCategory (String packageCategory ) throws SQLException, ClassNotFoundException;
    boolean removePackageCategory (String packageCategoryId) throws SQLException, ClassNotFoundException;
   public List<String> getAllCategory() throws SQLException, ClassNotFoundException;
    PackageCategory passCategory(String id) throws SQLException, ClassNotFoundException;
}
