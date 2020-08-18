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

    private String mobilePhone;

    private String role;

    public User(String id, String email, String firstName, String lastName, String mobilePhone, String role){
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.role = role;
    }

    @OneToMany//(mappedBy = "id_user")
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String email, String firstName, String lastName, String mobilePhone, String role) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.role = role;
    }
    
    public User(){}

    public String getFullName(){
        return firstName != null ? firstName.concat(" ").concat(lastName) : "";
    }

    public User(String email, String firstName, String lastName, String mobilePhone, String role, Car car) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.role = role;
        this.car.add(car);
    }

    public void setCar(Car car) {
        this.car.add(car);
    }

    public List<Car> getCar(){
        return this.car;
    }

}
