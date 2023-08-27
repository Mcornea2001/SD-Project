package com.example.Assignment3.controller;

import com.example.Assignment3.Service.CarService;
import com.example.Assignment3.Service.CustomerService;
import com.example.Assignment3.Service.RentalService;
import com.example.Assignment3.model.Car;
import com.example.Assignment3.model.Rental;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CarService carService;
    private final RentalService rentalService;

    @Autowired
    public CustomerController(CustomerService customerService, CarService carService, RentalService rentalService) {
        this.customerService = customerService;
        this.carService = carService;
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> search(@RequestParam("query") String query) {
        return ResponseEntity.ok(carService.search(query));
    }

    @Transactional
    @PostMapping("/add")
    public void registerNewRental(@RequestBody Rental rental) {
        rentalService.addNewRental(rental);
    }

}
