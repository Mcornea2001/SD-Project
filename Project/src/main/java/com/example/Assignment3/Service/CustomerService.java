package com.example.Assignment3.Service;

import com.example.Assignment3.model.Customer;
import com.example.Assignment3.repo.CarRepo;
import com.example.Assignment3.repo.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(int customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if (!exists) {
            throw new IllegalStateException("customer with id " + customerId + " does not exists");
        }
        customerRepository.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(int customerId, String name, String email, String phone, Integer age, String username, String password) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("customer with id " + customerId + " does not exists"));

        if (name != null && name.length() > 0 && !customer.getName().equals(name)) {
            customer.setName(name);
        }

        if (email != null && email.length() > 0 && !customer.getEmail().equals(email)) {
            Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
            if (customerOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            customer.setEmail(email);
        }

        if (phone != null && phone.length() > 0 && !customer.getPhone().equals(phone)) {
            customer.setPhone(phone);
        }

        if (age != null && age > 0 && customer.getAge() != age) {
            customer.setAge(age);
        }

        if (username != null && username.length() > 0 && !customer.getUsername().equals(username)) {
            customer.setUsername(username);
        }

        if (password != null && password.length() > 0 && !customer.getPassword().equals(password)) {
            customer.setPassword(password);
        }
    }

    public  Customer getCustomerById(int customerId){
        return customerRepository.findById(customerId).orElseThrow(() -> new IllegalStateException("customer with id " + customerId + " does not exists"));
    }
}
