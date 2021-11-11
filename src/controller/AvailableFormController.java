package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import view.tm.ItemTm;
import view.tm.PackagesTM;
import view.tm.RentItemOrderDeatilsTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class AvailableFormController {
    public AnchorPane mainPane;
    public TableView tblRentItem;
    public TableColumn colSid;
    public TableColumn colCatogeryName;
    public TableColumn colModelName;
    public TableColumn colItemType;
    public TableColumn colPrice;
    public JFXTextField txtSearch;
    public TableView tblPackages;
    public TableColumn colPid;
    public TableColumn colPCName;
    public TableColumn colPackageType;
    public TableColumn colDeatils;
    public TableColumn colPackagePrice;
    public JFXTextField txtSearch2;
    public Label lblPackage;
    public Label lablItems;


    public void initialize(){
        try {
            showsData();
            showPackages();
            lblPackage.setText(new AvailableServiceController().getCountPackage());
            lablItems.setText(new AvailableServiceController().getCountAvailableItems());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showsData() throws SQLException, ClassNotFoundException {
        ObservableList<ItemTm> itemTmObservableList=new AvailableServiceController().getItemList();
        colSid.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colCatogeryName.setCellValueFactory(new PropertyValueFactory<>("categoryId"));
        colModelName.setCellValueFactory(new PropertyValueFactory<>("modelId"));
        colItemType.setCellValueFactory(new PropertyValueFactory<>("type"));
       colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

       tblRentItem.setItems(itemTmObservableList);
    }

    public void onKeyReleasedOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        List<ItemTm> itemTm = new AvailableServiceController().searchRentItem(txtSearch.getText());
        ObservableList<ItemTm> observableList= FXCollections.observableArrayList();
        for (ItemTm temp :itemTm) {
            observableList.add(new ItemTm(
                    temp.getSerialNumber(),
                    temp.getType(),
                    temp.getPrice(),
                    temp.getCategoryId(),
                    temp.getModelId()
            ));

        }
      tblRentItem.setItems(observableList);
    }

    public  void showPackages() throws SQLException, ClassNotFoundException {
        ObservableList<PackagesTM> packagesTMObservableList=new AvailableServiceController().getPackageList();
        colPid.setCellValueFactory(new  PropertyValueFactory<>("packageId"));
        colPCName.setCellValueFactory(new  PropertyValueFactory<>("packageCategoryId"));
        colPackageType.setCellValueFactory(new  PropertyValueFactory<>("packageType"));
        colDeatils.setCellValueFactory(new  PropertyValueFactory<>("packageDeatils"));
        colPackagePrice.setCellValueFactory(new  PropertyValueFactory<>("price"));

        tblPackages.setItems(packagesTMObservableList);
    }

    public void onKeyReleasedOnAction2(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        List<PackagesTM> packagesTMS = new AvailableServiceController().searchPackages(txtSearch2.getText());
        ObservableList<PackagesTM> observableList= FXCollections.observableArrayList();
        for (PackagesTM temp :packagesTMS) {
            observableList.add(new PackagesTM(
                    temp.getPackageId(),
                    temp.getPackageType(),
                    temp.getPackageDeatils(),
                    temp.getPrice(),
                    temp.getPackageCategoryId()

            ));

        }
        tblPackages.setItems(observableList);
    }
}
