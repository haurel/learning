package com.aurel.service;

import com.aurel.model.Car;
import com.aurel.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public void setupCarRepository(CarRepository carRepository){ this.carRepository = carRepository; };

    public List<Car> getAllCars(String id) {
        return carRepository.findByUserId(id);
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }
}
