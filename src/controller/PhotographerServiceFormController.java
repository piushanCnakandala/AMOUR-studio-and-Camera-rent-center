package controller;

import db.DbConnection;
import model.PackageCategory;
import model.Photographer;
import model.User;
import view.tm.PhotographerTm;
import view.tm.UserTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhotographerServiceFormController implements PhotographerService {

    @Override
    public boolean savePhotographer(Photographer photographer) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query="INSERT INTO Photographer VALUES(?,?,?,?,?)";
        PreparedStatement stm=con.prepareStatement(query);
        stm.setObject(1,photographer.getPhotographerId());
        stm.setObject(2,photographer.getPhotographerName());
        stm.setObject(3,photographer.getPhotographerAddress());
        stm.setObject(4,photographer.getPhotographerTpNumber());
        stm.setObject(5,photographer.getPhotographerSalary());

        return stm.executeUpdate()> 0;
    }

    @Override
    public Photographer getPhotographer(String photographer) throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Photographer WHERE photographer_id ='" + photographer + "'").executeQuery();
        if (set.next()){
            return new Photographer(set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getDouble(5)
                    );
        }else {

        }
        return null;
    }

    @Override
    public boolean removePhotographer(String photographerId) throws SQLException, ClassNotFoundException {
        return DbConnection.getInstance().getConnection().prepareStatement("DELETE  FROM Photographer WHERE photographer_id='" + photographerId + "'").executeUpdate()> 0;
    }

    @Override
    public boolean updatePhotographer(PhotographerTm photographer) throws SQLException, ClassNotFoundException {
        PreparedStatement rst = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Photographer  SET photographer_name=?,photographer_address=?,photographer_number=?,photographer_salary=? WHERE photographer_id=?");

        rst.setObject(1, photographer.getPhotographerName());
        rst.setObject(2, photographer.getPhotographerAddress());
        rst.setObject(3, photographer.getPhotographerTpNumber());
        rst.setObject(4, photographer.getPhotographerSalary());
        rst.setObject(5, photographer.getPhotographerId());

        return rst.executeUpdate() > 0;
    }

    @Override
    public List<String> getAllPhotographerIds() throws SQLException, ClassNotFoundException {
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Photographer").executeQuery();
        List<String> ids=new ArrayList<>();
        while (set.next()){
            ids.add(set.getString(1)
            );
        }
        return ids;

    }

    @Override
    public Photographer passphotographer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Photographer WHERE  photographer_id='" + id + "'").executeQuery();
        if (rst.next()) {
            return new Photographer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5)

            );
        } else {
            return null;
        }
    }
}
