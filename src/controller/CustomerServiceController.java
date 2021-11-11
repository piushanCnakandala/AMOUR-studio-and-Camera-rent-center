package controller;

import db.DbConnection;
import model.Customer;
import model.Packages;
import view.tm.CustomerTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceController implements CustomerService {
    @Override
    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO Customer VALUES(?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,customer.getCustomerId());
        stm.setObject(2,customer.getCustomerName());
        stm.setObject(3,customer.getCustomerAddress());
        stm.setObject(4,customer.getCustomerNic());
        stm.setObject(5,customer.getCustomerTPNumber());

        return stm.executeUpdate()> 0;
    }

    @Override
    public List<String> getAllCustomer() throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String> ids=new ArrayList<>();
        while (set.next()){
            ids.add(set.getString(1)
            );
        }
        return ids;
    }

    @Override
    public Customer passCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Customer WHERE customer_id ='" + id + "'").executeQuery();
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        } else {
            return null;
        }
    }

    @Override
    public boolean removeCustomer(String customerId) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE  FROM Customer WHERE customer_id='" + customerId + "'").executeUpdate()> 0;
    }

    @Override
    public boolean updateCustomer(CustomerTM customerTM) throws SQLException, ClassNotFoundException {
        PreparedStatement rst = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer  SET customer_name=?,customer_address=?,customer_nic=?,customer_number=? WHERE customer_id =?");
        rst.setObject(1, customerTM.getCustomerName());
        rst.setObject(2, customerTM.getCustomerAddress());
        rst.setObject(3, customerTM.getCustomerNic());
        rst.setObject(4, customerTM.getCustomerTPNumber());
        rst.setObject(5, customerTM.getCustomerId());

        return rst.executeUpdate() > 0;

    }
}
