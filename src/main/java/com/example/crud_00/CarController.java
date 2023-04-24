package com.example.crud_00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarRepository carRepository;
    @PostMapping
    public Car createCar(@RequestBody Car car){
        return carRepository.save(car);
    }

    @GetMapping
    public List<Car> getCars(){
        return (List<Car>) carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Car> getSingleCar(@PathVariable int id){
        if(!carRepository.existsById(id)){
            return Optional.of(new Car());
        }
        return carRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable int id, @RequestParam String modelName, Car car){
        if(!carRepository.existsById(id)){
            return car;
        }
        car.setModelName(modelName);
        return carRepository.save(car);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteCar(@PathVariable int id){
        if(!carRepository.existsById(id)){
            return HttpStatus.CONFLICT;
        }
        carRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }

    @DeleteMapping
    public void deleteAllCars(){
        carRepository.deleteAll();
    }



}
