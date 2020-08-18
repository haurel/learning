package com.aurel.controller;

import com.aurel.model.Car;
import com.aurel.model.User;
import com.aurel.service.CarService;
import com.aurel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class CarController {

    private CarService carService;

    @Autowired
    public void setupCarService(CarService carService){ this.carService = carService; }


    @GetMapping("/{id}/cars")
    public List<Car> getAllCars(@PathVariable String id){
        return carService.getAllCars(id);
    }

    @PostMapping("/{id}/add")
    public Car addCar(@PathVariable String id, @RequestBody Car car){
        car.setUserId(id);
        return carService.addCar(car);
    }



}
