package com.aurel.repository;

import com.aurel.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    public List<Car> findByUserId(String userId);

}
