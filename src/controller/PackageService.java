package controller;

import model.Item;
import model.Packages;
import view.tm.PackagesTM;

import java.sql.SQLException;
import java.util.List;


public interface PackageService {
    boolean savePackage(Packages packages) throws SQLException, ClassNotFoundException;
    Packages getPackages(String packages) throws SQLException, ClassNotFoundException;
    boolean removePackage(String packageId) throws SQLException, ClassNotFoundException;
    boolean updatePackage(PackagesTM packages) throws SQLException, ClassNotFoundException;
    public List<String> getAllPackage() throws SQLException, ClassNotFoundException ;
    public Packages passPackage(String packages) throws SQLException, ClassNotFoundException ;
}
