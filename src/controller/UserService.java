package controller;

import model.User;
import view.tm.UserTM;

import java.sql.SQLException;

public interface UserService {
    boolean saveUser(User user) throws SQLException, ClassNotFoundException;
    User getUser(String user) throws SQLException, ClassNotFoundException;
    boolean removeUser(String UserName) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserTM user) throws SQLException, ClassNotFoundException;

}
