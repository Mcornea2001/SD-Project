package com.example.Assignment3.repo;

import com.example.Assignment3.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepo extends JpaRepository<Rental, Integer> {
}
