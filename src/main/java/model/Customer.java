package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    private String id;
    private String status;
    private String name;
    private String address;
    private LocalDate birthday;
    private String contactNumber;



    public Customer(String id,String status, String name,String address ,String contactNumber, LocalDate birthday){
        this.id=id;
        this.status=status;
        this.name=status+name;
        this.birthday=birthday;
        this.address=address;
        this.contactNumber=contactNumber;
    }



}
