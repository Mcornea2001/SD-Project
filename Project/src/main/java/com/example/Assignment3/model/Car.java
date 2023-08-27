package com.example.Assignment3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @SequenceGenerator(name = "car_sequence",
            sequenceName = "car_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "car_sequence"
    )
    @Column(name = "cid")
    private Integer cid;
    private String brand;
    private String model;
    private int km;
    private boolean rented;
    private String location;

    public Car(){}
    public Car(Integer cid, String brand, String model, int km, boolean rented, String location) {
        this.cid = cid;
        this.brand = brand;
        this.model = model;
        this.km = km;
        this.rented = rented;
        this.location = location;
    }

    public Car(String brand, String model, int km, boolean rented, String location) {
        this.brand = brand;
        this.model = model;
        this.km = km;
        this.rented = rented;
        this.location = location;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer id) {
        this.cid = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public boolean getRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
