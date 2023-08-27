package com.example.Assignment3.Service;

import com.example.Assignment3.model.Admin;
import com.example.Assignment3.model.Customer;
import com.example.Assignment3.model.Rental;
import com.example.Assignment3.repo.AdminRepo;
import com.example.Assignment3.repo.CarRepo;
import com.example.Assignment3.repo.CarRepo;
import com.example.Assignment3.repo.Observer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements Observer {
    @Autowired
    private final AdminRepo adminRepo;

    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }


    public List<Admin> getAdmin() {
        return adminRepo.findAll();
    }

    public void addNewAdmin(Admin admin) {
        adminRepo.save(admin);
    }

    public void deleteAdmin(int adminId) {
        boolean exists = adminRepo.existsById(adminId);
        if (!exists) {
            throw new IllegalStateException("customer with id " + adminId + " does not exists");
        }
        adminRepo.deleteById(adminId);
    }

    @Transactional
    public void updateAdmin(int adminId, String username, String password) {
        Admin admin = adminRepo.findById(adminId).orElseThrow(() -> new IllegalStateException("admin with id " + adminId + " does not exists"));

        if (username != null && username.length() > 0 && !admin.getUsername().equals(username)) {
            admin.setUsername(username);
        }

        if (password != null && password.length() > 0 && !admin.getPassword().equals(password)) {
            admin.setPassword(password);
        }
    }

    @Override
    public void update(Rental rental) {
        File file = new File("log.txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("Rental with id " + rental.getId() + " was added to the database\n");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
