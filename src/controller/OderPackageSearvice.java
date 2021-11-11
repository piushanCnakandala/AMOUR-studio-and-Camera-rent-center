package controller;

import model.Item;
import model.OderPackageDeatils;
import model.OrderPackage;
import model.Packages;
import view.tm.ItemTm;
import view.tm.PackagesTM;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OderPackageSearvice {
    boolean savePackage(OrderPackage orderPackage) throws SQLException, ClassNotFoundException;
    boolean savePackageDeatils(ArrayList<OderPackageDeatils>orderPackage) throws SQLException, ClassNotFoundException;
    Packages getPackage(String packages) throws SQLException, ClassNotFoundException;
    boolean removePackage(String packageId) throws SQLException, ClassNotFoundException;
    boolean updatePackage(PackagesTM packagesTM) throws SQLException, ClassNotFoundException;
    String getOrderId() throws SQLException, ClassNotFoundException;
}
