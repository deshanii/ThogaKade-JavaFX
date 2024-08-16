package controller;

import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ViewFormController implements Initializable {

    public TableView<Customer> tbl;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtContactNumber;
    public DatePicker txtDOB;


    @FXML
    private JFXButton btnReLoad;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn colBirthday;

private ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

    @FXML
    void btnReLoadOnAction(ActionEvent event) {
        loadTable();
    }

    private void loadTable(){

        List<Customer> customerList = DBConnection.getInstance().getConnection();


             customerObservableList.setAll(customerList);
        tbl.setItems(customerObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));

//        List<Customer> customerList = DBConnection.getInstance().getConnection();
//        customerList.add(new Customer("C001","Miss","Miss.Deshani","Ragama", LocalDate.of(2004,7,5),"0770095678"));
//        customerList.add(new Customer("C002","Master","Nimal","Panadura", LocalDate.of(2002,5,23),"0786567890"));
//        customerList.add(new Customer("C003","Master","Arun","Colombo", LocalDate.of(2000,3,9),"0723456666"));
//        customerList.add(new Customer("C004","Mr","Kamal","Galle", LocalDate.of(1990,8,17),"0756667890"));
//        customerList.add(new Customer("C005","Mrs","Ann","Wattala", LocalDate.of(1987,9,13),"0112453200"));
//        customerList.add(new Customer("C006","Mr","saman","Kandana", LocalDate.of(1992,10,7),"0112678096"));

 loadTable();
    }
}
