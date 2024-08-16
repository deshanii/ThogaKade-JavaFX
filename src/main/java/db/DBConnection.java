package db;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    public static DBConnection instance;
    private List<Customer> customerList;



    private DBConnection(){
        this.customerList = new ArrayList<>();
    }
    public List<Customer> getConnection(){
        return customerList;
    }
    public static DBConnection getInstance(){
        if(null==instance){
            return instance = new DBConnection();
        }return instance;
    }
}
