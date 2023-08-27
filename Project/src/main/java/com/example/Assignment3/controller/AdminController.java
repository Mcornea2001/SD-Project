package com.example.Assignment3.controller;

import com.example.Assignment3.Service.AdminService;
import com.example.Assignment3.Service.CarService;
import com.example.Assignment3.Service.CustomerService;
import com.example.Assignment3.Service.EmployeeService;
import com.example.Assignment3.model.Admin;
import com.example.Assignment3.model.Car;
import com.example.Assignment3.model.Customer;
import com.example.Assignment3.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
    private final AdminService adminService;
    private final CarService carService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    @Autowired
    public AdminController(AdminService adminService, CarService carService, CustomerService customerService, EmployeeService employeeService) {
        this.adminService = adminService;
        this.carService = carService;
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    @GetMapping("/allCar")
    public List<Car> getCar() {
        return carService.getCar();
    }

    @PostMapping("/addCar")
    public void registerNewCar(@RequestBody Car car) {
        carService.addNewCar(car);
    }

    @DeleteMapping(path = "/deleteCar/{carId}")
    public void deleteCar(@PathVariable("carId") int carId) {
        carService.deleteCar(carId);
    }

    @PutMapping(path = "/updateCar/{carId}")
    public void updateCar(
            @PathVariable("carId") int carId,
            @RequestBody Car car) {
        carService.updateCar(carId, car.getBrand(), car.getModel(), car.getKm(), car.getRented(), car.getLocation());
    }

    @GetMapping("/findCar/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable("carId") int id) {
        Car car = carService.findCarById(id);
        if (car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allCustomer")
    public List<Customer> getCustomer() {
        return customerService.getCustomer();
    }

    @PostMapping("/addCustomer")
    public void registerNewCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
    }

    @DeleteMapping(path = "/deleteCustomer/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "/updateCustomer/{customerId}")
    public void updateCustomer(
            @PathVariable("customerId") int customerId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {
        customerService.updateCustomer(customerId, name, email, phone, age, username, password);
    }

    @GetMapping("/allEmployee")
    public List<Employee> getEmployee() {
        return employeeService.getEmployee();
    }

    @PostMapping("/addEmployee")
    public void registerNewEmployee(@RequestBody Employee employee) {
        employeeService.addNewEmployee(employee);
    }

    @DeleteMapping(path = "/deleteEmployee/{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    @PutMapping(path = "/updateEmployee/{employeeId}")
    public void updateEmployee(
            @PathVariable("employeeId") int employeeId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password) {
        employeeService.updateEmployee(employeeId, name, username, password);
    }
}
