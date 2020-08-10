package com.example.swaggerrestapi.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private String phone;
    private Integer age;

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }

    public Integer getAge(){
        return age;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setAge(Integer age){
        this.age = age;
    }

}
