package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import view.tm.OrderPackageTM;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PackageOrderFormController {

    static ObservableList<OrderPackageTM> parentList = FXCollections.observableArrayList();
    public Label iblDate;
    public Label lblTime;
    public ComboBox<String> cmbSelectPackage;
    public JFXTextField txtPId;
    public JFXTextField txtModelId;
    public JFXTextField txtPPrice;
    public ComboBox<String> cmbAddPhotographer;
    public JFXTextField txtPhotographerId;
    public JFXTextField txtPhotographerName;
    public TableColumn colPackagePrice;
    public ComboBox<String> cmbCustomer;
    public JFXTextField txtCustId;
    public JFXTextField txtCustName;
    public TableColumn colCustomerId;
    public Label lblOderId;
    public TableView<OrderPackageTM> tblPackages;
    public TableColumn colPId;
    public TableColumn colCid;
    public TableColumn colPhId;
    public Label lblTOtal;
    private OrderPackage orderPackageSell;

    private void loadPackages() throws SQLException, ClassNotFoundException {
        List<String> packageId = new PackageServiceController().getAllPackage();
        cmbSelectPackage.getItems().addAll(packageId);
    }

    private void loadPhotographer() throws SQLException, ClassNotFoundException {
        List<String> photographerId = new PhotographerServiceFormController().getAllPhotographerIds();
        cmbAddPhotographer.getItems().addAll(photographerId);
    }

    private void loadCustomers() throws SQLException, ClassNotFoundException {
        List<String> customerId = new CustomerServiceController().getAllCustomer();
        cmbCustomer.getItems().addAll(customerId);
    }

    public void setTable() throws SQLException, ClassNotFoundException {

    }

    private void setOrderIds() {
        try {
            lblOderId.setText(new OrderPackageSearviceController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        setOrderIds();
        try {
            loadPackages();
            loadPhotographer();
            loadCustomers();
            loadDateAndTime();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbSelectPackage.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setPackageData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbAddPhotographer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setPhotographerData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        showPackages();
    }

    private void showPackages() {
        colPId.setCellValueFactory(new PropertyValueFactory<>("packageId"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("packageCatogeryId"));
        colPackagePrice.setCellValueFactory(new PropertyValueFactory<>("packagePrice"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colPhId.setCellValueFactory(new PropertyValueFactory<>("photographerId"));
        parentList.clear();
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        iblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() + " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    private void setPackageData(String packageData) throws SQLException, ClassNotFoundException {
        Packages packages = new PackageServiceController().passPackage(packageData);
        if (packageData == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();

        } else {
            txtPId.setText(packages.getPackageId());
            txtModelId.setText(packages.getPackageCategoryId());
            txtPPrice.setText(String.valueOf(packages.getPrice()));

        }
    }

    private void setPhotographerData(String photographerData) throws SQLException, ClassNotFoundException {
        //String[] photographersData = photographerData.split("-");
        Photographer photographer = new PhotographerServiceFormController().passphotographer(photographerData);
        if (photographerData == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();

        } else {
            txtPhotographerId.setText(photographer.getPhotographerId());
            txtPhotographerName.setText(photographer.getPhotographerName());

        }
    }

    private void setCustomerData(String customerData) throws SQLException, ClassNotFoundException {
        Customer customer = new CustomerServiceController().passCustomer(customerData);
        if (customer == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();

        } else {
            txtCustId.setText(customer.getCustomerId());
            txtCustName.setText(customer.getCustomerName());

        }
    }


    public void OderFormOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        ArrayList<OderPackageDeatils> oderPackageDeatils = new ArrayList<>();


        for (OrderPackageTM tempTm : parentList) {
            oderPackageDeatils.add(new OderPackageDeatils(
                    lblOderId.getText(),
                    tempTm.getPackageId(),
                    tempTm.getPackagePrice(),
                    tempTm.getPackageCatogeryId(),
                    tempTm.getCustomerId(),
                    lblTOtal.getText(),
                    tempTm.getPhotographerId()
            ));


        }

        for (OderPackageDeatils tm : oderPackageDeatils) {
            OrderPackage orderPackage = new OrderPackage(
                    tm.getOrderId(),
                    tm.getPackageId(),
                    tm.getPackageCatogeryId(),
                    tm.getPhotographerId(),
                    tm.getPackagePrice(),
                    tm.getCustomerId(),
                    oderPackageDeatils
            );
            orderPackageSell = orderPackage;
        }


        if (new OrderPackageSearviceController().savePackage(orderPackageSell)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            setOrderIds();

            try {
                JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("../view/reports/ServiceBill.jrxml"));
                JasperReport compileReport = JasperCompileManager.compileReport(design);

                double totalAmount = Double.parseDouble(lblTOtal.getText());
                HashMap map2 = new HashMap();
                map2.put("totalPrice", totalAmount);

                ObservableList<OrderPackageTM> deatilsNw = tblPackages.getItems();
                JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map2, new JRBeanArrayDataSource(deatilsNw.toArray()));
                JasperViewer.viewReport(jasperPrint, false);
            } catch (JRException e) {
                e.printStackTrace();
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

    }


    public void AddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AddCustomerForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.centerOnScreen();
        stage.show();

    }


    public void addOderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String packageId = txtPId.getText();
        String packageCatogeryId = txtModelId.getText();
        String photographerId = txtPhotographerId.getText();
        Double packagePrice = Double.parseDouble(txtPPrice.getText());
        String customerId = txtCustId.getText();

        OrderPackageTM tm = new OrderPackageTM(
                packageId,
                packageCatogeryId,
                photographerId,
                packagePrice,
                customerId
        );
        int rowNumber = isExists(tm);
        if (rowNumber == -1) {
            parentList.add(tm);
        } else {
            OrderPackageTM newTm = new OrderPackageTM(
                    packageId,
                    packageCatogeryId,
                    photographerId,
                    packagePrice,
                    customerId
            );
            parentList.remove(rowNumber);
            parentList.add(newTm);
        }
        tblPackages.setItems(parentList);
        clearFiled();
    }

    private int isExists(OrderPackageTM orderPackageTM) { //show old data (dupilicte data thiynwda)
        for (int i = 0; i < parentList.size(); i++) {
            if (orderPackageTM.getPackageId().equals(parentList.get(i).getPackageId())) {
                return i;
            }
        }
        return -1;
    }

    public void removeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        OrderPackageTM orderPackageTM = tblPackages.getSelectionModel().getSelectedItem();
        String orderId = orderPackageTM.getPackageId();


        if (new OrderPackageSearviceController().removePackage(orderId)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Delete Selected Item").show();
            showPackages();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
        clearFiled();
    }

    private void clearFiled() {
        txtPPrice.clear();
        txtModelId.clear();
        txtPhotographerId.clear();
        txtCustName.clear();
        txtCustId.clear();
        txtPhotographerId.clear();

    }

    public void clearFieldsOnAction(ActionEvent actionEvent) {
        clearFiled();
    }
}
