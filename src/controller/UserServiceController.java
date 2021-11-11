package controller;

import db.DbConnection;
import model.User;
import view.tm.UserTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceController implements UserService {
    public boolean saveUser(User user) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO `User` VALUES(?,md5(?),?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,user.getUserName());
        stm.setObject(2,user.getPassword());
        stm.setObject(3,user.getName());
        stm.setObject(4,user.getRole());
        return stm.executeUpdate()> 0;

    }

    @Override
    public User getUser(String user) throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `User` WHERE user_name='" + user + "'").executeQuery();
        if (set.next()){
            return new User(set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4));
        }else {

        }
        return null;
    }

    @Override
    public boolean removeUser(String userName) throws SQLException, ClassNotFoundException {

        return DbConnection.getInstance().getConnection().prepareStatement("DELETE  FROM `User` WHERE user_name='" + userName + "'").executeUpdate()> 0;
    }

    @Override
    public boolean updateUser(UserTM user) throws SQLException, ClassNotFoundException {
        PreparedStatement rst = DbConnection.getInstance().getConnection().prepareStatement("UPDATE `User`  SET user_password=?,`name`=?,`role`=? WHERE user_name=?");
        rst.setObject(1, user.getPassword());
        rst.setObject(2, user.getName());
        rst.setObject(3, user.getRole());
        rst.setObject(4, user.getUserName());

        return rst.executeUpdate() > 0;

    }

    public String login(User login) throws SQLException, ClassNotFoundException {
        String userName= login.getUserName();
        String userPassword=login.getPassword();

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `User` WHERE user_name=? AND user_password=md5(?)");
        stm.setObject(1,userName);
        stm.setObject(2,userPassword);
        ResultSet rst =stm.executeQuery();
        if (rst.next()){
            String userRole=rst.getString(4);
            return userRole;
        }else{
            return "";
        }

    }
}
