package com.example.Assignment3.Service;

import com.example.Assignment3.model.Car;
import com.example.Assignment3.model.CarContainer;
import com.example.Assignment3.model.Iterator;
import com.example.Assignment3.repo.CarRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public List<Car> getCar() {
        return carRepo.findAll();
    }
    public List<Car> readCar() {
        return carRepo.findAll();
    }

    public void addNewCar(Car car) {
        carRepo.save(car);
    }

    public void deleteCar(int carId) {
        boolean exists = carRepo.existsById(carId);
        if (!exists) {
            throw new IllegalStateException("customer with id " + carId + " does not exists");
        }
        carRepo.deleteById(carId);
    }

    @Transactional
    public void updateCar(int carId, String brand, String model, int km, boolean rented, String location) {
        Car car = carRepo.findById(carId).orElseThrow(() -> new IllegalStateException("car with id " + carId + " does not exists"));
        car.setKm(km);
        car.setBrand(brand);
        car.setModel(model);
        car.setRented(rented);
        car.setLocation(location);
        carRepo.save(car);
    }

    public Car findCarById(int id) {
        CarContainer carContainer = new CarContainer(readCar().toArray((Car[]::new)));
        Iterator<Car> iterator = carContainer.getIterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getCid() == id)
                return car;
        }
        return null;
    }

    public List<Car> search(String location) {
        return carRepo.findCarsByLocation(location);
    }
}
