package controller;

import model.Customer;
import model.RentItemModels;
import view.tm.CustomerTM;
import view.tm.PackagesTM;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException;
    public List<String> getAllCustomer() throws SQLException, ClassNotFoundException;
   Customer passCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean removeCustomer(String customerId) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerTM customerTM) throws SQLException, ClassNotFoundException;
}
