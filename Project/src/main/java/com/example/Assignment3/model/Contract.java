package com.example.Assignment3.model;

public class Contract {
    private String customerName;
    private String employeeName;
    private Car car;
    private String period;
    private String location;
    private double price;

    public Contract(String customerName, String employeeName, Car car, String period, String location, double price) {
        this.customerName = customerName;
        this.employeeName = employeeName;
        this.car = car;
        this.period = period;
        this.location = location;
        this.price = price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "customerName='" + customerName + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", car=" + car.getBrand() + car.getModel() +
                ", period='" + period + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                '}';
    }
}
