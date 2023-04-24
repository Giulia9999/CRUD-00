package com.example.crud_00;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Optional<Car> updateCar(@PathVariable int id, @RequestParam String type){
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setType(type);
            Car updatedCar = carRepository.save(car);
            return Optional.of(updatedCar);
        } else {
            return Optional.empty();
        }
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
