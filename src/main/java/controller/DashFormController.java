package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DashFormController implements Initializable {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnViewTable;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContactNumber;

    @FXML
    private DatePicker txtDOB;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXComboBox<String> cmbTitle;
    
    @FXML
    void btnAddOnAction(ActionEvent event) {
        Customer customer = new Customer(txtId.getText(),cmbTitle.getValue(),txtName.getText(),txtAddress.getText(),txtContactNumber.getText(),txtDOB.getValue());

        List<Customer> customerList = DBConnection.getInstance().getConnection();

        customerList.add(customer);

        clearText();
        System.out.println(customer);

    }

    private void clearText(){
        txtId.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtContactNumber.setText(null);
        cmbTitle.setValue(null);
        txtDOB.setValue(null);
    }



    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        List<Customer> customerList = DBConnection.getInstance().getConnection();

        String delete = txtId.getText();

        for(Customer customer: customerList){
            if(customer.getId().equals(delete)){
                customerList.remove(customer);
                clearText();
                System.out.println("Customer deleted successfully");
                return;
            }

        }
        System.out.println("customer not found");
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        List<Customer> customerList = DBConnection.getInstance().getConnection();
        String searchId = txtId.getText();

        for (Customer customer : customerList) {
            if (customer.getId().equals(searchId)) {
                String name = customer.getName();
                String [] parts = name.split("\\.");
                cmbTitle.setValue(customer.getStatus());
                txtName.setText(parts[1]);
                txtAddress.setText(customer.getAddress());
                txtContactNumber.setText(customer.getContactNumber());
                txtDOB.setValue(customer.getBirthday());
                return;
            }
        }
        System.out.println("Customer not found!");
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        List<Customer> customerList = DBConnection.getInstance().getConnection();
        String updateId = txtId.getText();

        for (Customer customer : customerList) {
            if (customer.getId().equals(updateId)) {
                customer.setStatus(cmbTitle.getValue());
                customer.setName(cmbTitle.getValue()+txtName.getText());
                customer.setAddress(txtAddress.getText());
                customer.setContactNumber(txtContactNumber.getText());
                customer.setBirthday(txtDOB.getValue());

                clearText();
                System.out.println("Customer updated successfully!");
                return;
            }
        }

        System.out.println("Customer not found!");
    }


    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clearText();
    }

    @FXML
    void btnViewTableOnAction(ActionEvent event) {


        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/view_form.fxml"))));
            stage.show();
        }catch (IOException e){
            throw new RuntimeException(e);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> status = FXCollections.observableArrayList("Master.","Mr.","Miss.","Mrs.","Ven.","Rev.");

        cmbTitle.setItems(status);
    }
}
