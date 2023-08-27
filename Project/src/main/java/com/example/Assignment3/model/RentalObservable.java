package com.example.Assignment3.model;

import com.example.Assignment3.repo.Observer;

import java.util.ArrayList;
import java.util.List;

public class RentalObservable {
    private List<Observer> admins = new ArrayList<>();

    public void addObserver(Observer observer) {admins.add(observer);}

    public void removeObserver(Observer observer) {admins.remove(observer);}

    public void notifyObservers(Rental rental) {
        for (Observer observer : admins) {
            observer.update(rental);
        }
    }
}
