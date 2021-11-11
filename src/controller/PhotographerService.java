package controller;

import model.PackageCategory;
import model.Photographer;
import model.User;
import view.tm.PhotographerTm;
import view.tm.UserTM;

import java.sql.SQLException;
import java.util.List;

public interface PhotographerService {
    boolean savePhotographer(Photographer photographer) throws SQLException, ClassNotFoundException;
    Photographer getPhotographer(String photographer) throws SQLException, ClassNotFoundException;
    boolean removePhotographer(String photographerId) throws SQLException, ClassNotFoundException;
    boolean updatePhotographer(PhotographerTm photographer) throws SQLException, ClassNotFoundException;
    public List<String> getAllPhotographerIds() throws SQLException, ClassNotFoundException;
    Photographer passphotographer (String id) throws SQLException, ClassNotFoundException;
}
