package com.aurel.model;


import org.hibernate.annotations.GenericGenerator;
//import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "T_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private String id;

    private String email;

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String password;

    private String role;

    public User(String email, String firstName, String lastName, String mobileNumber, String password, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.role = role;
    }

    public User(){}

    public User(String email, String firstName, String lastName, String mobileNumber, String password, String role, Car car) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.role = role;
        this.car.add(car);
    }

    @OneToMany
    @JoinColumn(name = "userId")
    private List<Car> car;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCar(Car car) { this.car.add(car); }

    public List<Car> getCar(){ return this.car; }

    public String getFullName(){
        return firstName != null ? firstName.concat(" ").concat(lastName) : "";
    }

}
