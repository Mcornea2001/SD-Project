package com.example.Assignment3.Service;

import com.example.Assignment3.model.*;
import com.example.Assignment3.repo.CarRepo;
import com.example.Assignment3.repo.CustomerRepository;
import com.example.Assignment3.repo.EmployeeRepo;
import com.example.Assignment3.repo.RentalRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final RentalRepo rentalRepo;
    private final CarRepo carRepo;
    private final CustomerRepository customerRepository;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, RentalRepo rentalRepo, CarRepo carRepo, CustomerRepository customerRepository) {
        this.employeeRepo = employeeRepo;
        this.rentalRepo = rentalRepo;
        this.carRepo = carRepo;
        this.customerRepository = customerRepository;
    }

    public List<Employee> getEmployee() {
        return employeeRepo.findAll();
    }

    public void addNewEmployee(Employee employee) {
        employeeRepo.save(employee);
    }

    public void deleteEmployee(int employeeId) {
        boolean exists = employeeRepo.existsById(employeeId);
        if (!exists) {
            throw new IllegalStateException("employee with id " + employeeId + " does not exists");
        }
        employeeRepo.deleteById(employeeId);
    }

    @Transactional
    public void updateEmployee(int employeeId, String name, String username, String password) {
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(() -> new IllegalStateException("employee with id " + employeeId + " does not exists"));

        if (name != null && name.length() > 0 && !employee.getName().equals(name)) {
            employee.setName(name);
        }

        if (username != null && username.length() > 0 && !employee.getUsername().equals(username)) {
            employee.setUsername(username);
        }

        if (password != null && password.length() > 0 && !employee.getPassword().equals(password)) {
            employee.setPassword(password);
        }
    }

    public Employee getEmployeeById(int employeeId){
        return employeeRepo.findById(employeeId).orElseThrow(() -> new IllegalStateException("employee with id " + employeeId + " does not exists"));
    }

    public Contract createContract(int employeeId, int rentalId) {
        Employee employee = employeeRepo.findById(employeeId).orElseThrow(() -> new IllegalStateException("employee with id " + employeeId + " does not exists"));
        Rental rental = rentalRepo.findById(rentalId).orElseThrow(() -> new IllegalStateException("rental with id " + rentalId + " does not exists"));
        Car car = carRepo.findById(rental.getCarId()).orElseThrow(() -> new IllegalStateException("car with id " + rental.getCarId() + " does not exists"));
        car.setRented(true);
        carRepo.save(car);
        Customer customer = customerRepository.findById(rental.getCustomerId()).orElseThrow(() -> new IllegalStateException("customer with id " + rental.getCustomerId() + " does not exists"));
        return new Contract(customer.getName(), employee.getName(), car, rental.getPeriod(), rental.getLocation(), rental.getPrice());
    }
}
