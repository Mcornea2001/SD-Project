package com.example.Assignment3.controller;

import com.example.Assignment3.Service.CarService;
import com.example.Assignment3.Service.EmployeeService;
import com.example.Assignment3.Service.PDFService;
import com.example.Assignment3.Service.RentalService;
import com.example.Assignment3.model.Car;
import com.example.Assignment3.model.Contract;
import com.example.Assignment3.model.ContractModel;
import com.example.Assignment3.model.Rental;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final RentalService rentalService;
    private final EmployeeService employeeService;
    private final PDFService pdfService;
    private Contract contract;
    private final CarService carService;

    @Autowired
    public EmployeeController(RentalService rentalService, EmployeeService employeeService, PDFService pdfService, CarService carService) {
        this.rentalService = rentalService;
        this.employeeService = employeeService;
        this.pdfService = pdfService;
        this.carService = carService;
    }

    @GetMapping("/all")
    public List<Rental> getRental() {
        return rentalService.getRental();
    }

    @Transactional
    @PostMapping("/add")
    public void registerNewRental(@RequestBody Rental rental) {
        rentalService.addNewRental(rental);
    }

    @DeleteMapping(path = "/delete/{rentalId}")
    public void deleteRental(@PathVariable("rentalId") Integer rentalId) {
        rentalService.deleteRental(rentalId);
    }

    @PutMapping(path = "/update/{rentalId}")
    public void updateRental(
            @PathVariable("rentalId") Integer rentalId,
            @RequestBody Rental rental) {
        rentalService.updateRental(rentalId, rental.getCarId(), rental.getCustomerId(), rental.getPrice(), rental.getPeriod(), rental.getLocation());
    }

    @PutMapping(path = "/return/{carId}")
    public void returnCar(
            @PathVariable("carId") Integer carId) {
        Car car = carService.findCarById(carId);
        car.setRented(false);
        carService.updateCar(carId, car.getBrand(), car.getModel(), car.getKm(), car.getRented(), car.getLocation());
    }

    @PostMapping("/sign")
    public void signRental(@RequestBody ContractModel contractModel) {
        contract = employeeService.createContract(contractModel.getEmployeeId(), contractModel.getRentalId());
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadPdf() throws IOException {
        byte[] pdfBytes = pdfService.generatePdfWithObjectDetails(contract);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=object_details.pdf")
                .body(pdfBytes);
    }

}
