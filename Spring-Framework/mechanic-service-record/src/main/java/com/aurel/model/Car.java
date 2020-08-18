package com.aurel.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "T_Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private String id;

    private String carPlate;

    private String problemDescription;

    private String carModel;

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id_user) {
        this.userId = id_user;
    }

    @Column(name = "userId")
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }


    public Car(String carPlate, String problemDescription, String carModel) {
        this.carPlate = carPlate;
        this.problemDescription = problemDescription;
        this.carModel = carModel;
    }

    public Car(String carPlate, String problemDescription, String carModel, String id_user) {
        this.carPlate = carPlate;
        this.problemDescription = problemDescription;
        this.carModel = carModel;
        this.userId = id_user;
    }

    public Car(){}

}
