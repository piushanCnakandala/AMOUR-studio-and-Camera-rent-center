package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import view.tm.ItemTm;
import view.tm.RentItemOrderDeatilsTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentItemHistoryFormController {

    public TableView<RentItemOrderDeatilsTM> tblRentItem;
    public TableColumn colOId;
    public TableColumn colSNumber;
    public TableColumn colCustId;
    public TableColumn colTPrice;
    public TableColumn colTDays;
    public TableColumn colRentDate;
    public TableColumn colRetuenDate;
    public TableColumn colStaus;
    public JFXTextField txtSearchOrder;

    public ObservableList<RentItemOrderDeatilsTM> getItemList() throws SQLException, ClassNotFoundException {
        ObservableList<RentItemOrderDeatilsTM> observableList = FXCollections.observableArrayList();
        Connection con = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM `Rent_Item_Order_Deatils`";
        PreparedStatement stm = con.prepareStatement(query);
        ResultSet rst = stm.executeQuery();
        while (rst.next()) {
            observableList.add(new RentItemOrderDeatilsTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)

            ));
        }
      tblRentItem.setItems(observableList);
        return observableList;
    }

    public  void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<RentItemOrderDeatilsTM> observableList = getItemList();
        colOId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colSNumber.setCellValueFactory(new PropertyValueFactory<>("serialNum"));
      colTPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colTDays.setCellValueFactory(new PropertyValueFactory<>("totalDate"));
        colRentDate.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
        colRetuenDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colStaus.setCellValueFactory(new PropertyValueFactory<>("status"));

    }


    public List<RentItemOrderDeatilsTM> searchRentHistory(String value) throws SQLException, ClassNotFoundException {
        List<RentItemOrderDeatilsTM> rentItemOrderDeatils = new ArrayList<>();
        ResultSet set = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM `Rent_Item_Order_Deatils` WHERE o_id LIKE '%" +value+ "%'").executeQuery();
        while (set.next()) {
            rentItemOrderDeatils.add(new RentItemOrderDeatilsTM(
                    set.getString(1),
                    set.getString(2),
                    set.getString(4),
                    set.getString(6),
                    set.getString(7)
            ));
        }
        return rentItemOrderDeatils;
    }

    public void searchONaction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        List<RentItemOrderDeatilsTM> rentItemOrderDeatils = new RentItemHistoryFormController().searchRentHistory(txtSearchOrder.getText());
        ObservableList<RentItemOrderDeatilsTM> observableList=FXCollections.observableArrayList();
        for (RentItemOrderDeatilsTM temp :rentItemOrderDeatils) {
            observableList.add(new RentItemOrderDeatilsTM(
                    temp.getOrderId(),
                    temp.getSerialNum(),
                    temp.getCustId(),
                    temp.getRentDate(),
                    temp.getReturnDate()
            ));

        }
        tblRentItem.setItems(observableList);

    }
}
