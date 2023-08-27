package com.example.Assignment3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonKey;
import jakarta.persistence.*;

@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @SequenceGenerator(name = "rental_sequence",
            sequenceName = "rental_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "reantal_sequence"
    )
    private Integer id;
    @JoinColumn(name = "car_id", referencedColumnName = "cid", insertable = true, updatable = false)
    private Integer carId;
    @JoinColumn(name = "customer_id", referencedColumnName = "cuid", insertable = true, updatable = false)
    private Integer customerId;
    private double price;
    private String period;
    private String location;

    public Rental(){}
    public Rental(Integer id, Integer carId, Integer customerId, double price, String period, String location) {
        this.id = id;
        this.carId = carId;
        this.customerId = customerId;
        this.price = price;
        this.period = period;
        this.location = location;
    }

    public Rental(Integer carId, Integer customerId, double price, String period, String location) {
        this.carId = carId;
        this.customerId = customerId;
        this.price = price;
        this.period = period;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
