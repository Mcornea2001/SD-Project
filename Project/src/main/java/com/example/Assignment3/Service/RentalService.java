package com.example.Assignment3.Service;

import com.example.Assignment3.model.Rental;
import com.example.Assignment3.model.RentalObservable;
import com.example.Assignment3.repo.RentalRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService extends RentalObservable {
    private final RentalRepo rentalRepo;
    private final RentalObservable rentalObservable = new RentalObservable();
    private final AdminService adminService;

    @Autowired
    public RentalService(RentalRepo rentalRepo, AdminService adminService) {
        this.rentalRepo = rentalRepo;
        this.adminService = adminService;
        addObserver(adminService);
    }

    public List<Rental> getRental() {
        return rentalRepo.findAll();
    }

    public void addNewRental(Rental rental) {
        rentalRepo.save(rental);
        notifyObservers(rental);
    }

    public void deleteRental(Integer rentalId) {
        boolean exists = rentalRepo.existsById(rentalId);
        if (!exists) {
            throw new IllegalStateException("rental with id " + rentalId + " does not exists");
        }
        rentalRepo.deleteById(rentalId);
    }

    @Transactional
    public void updateRental(Integer rentalId, Integer carId, Integer customerId, double price, String period, String location) {
        Rental rental = rentalRepo.findById(rentalId).orElseThrow(() ->
                new IllegalStateException("rental with id " + rentalId + " does not exists"));

        if (period != null && period.length() >= 0 && !rental.getPeriod().equals(period)) {
            rental.setPeriod(period);
        }

        if (rentalId != null && rentalId > 0 && rental.getId() != rentalId) {
            rental.setId(rentalId);
        }

        if (carId != null && carId > 0 && rental.getCarId() != carId) {
            rental.setCarId(carId);
        }

        if (customerId != null && customerId > 0 && rental.getCustomerId() != customerId) {
            rental.setCustomerId(customerId);
        }

        if (price > 0 && rental.getPrice() != price) {
            rental.setPrice(price);
        }

        if (location != null && location.length() >= 0 && !rental.getLocation().equals(location)) {
            rental.setLocation(location);
        }

        rentalRepo.save(rental);
    }

    public Rental findRentalById(int id) {
        return rentalRepo.findById(id).orElse(null);
    }
}
