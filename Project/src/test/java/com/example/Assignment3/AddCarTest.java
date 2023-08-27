package com.example.Assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import com.example.Assignment3.Service.CarService;
import com.example.Assignment3.model.Car;
import com.example.Assignment3.repo.CarRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AddCarTest {
    @InjectMocks
    private CarService carService;
    @Mock
    private CarRepo carRepo;
    @Test
    public void testCreateProduct(){
        Car car = new Car(4,"porsche","911",5000, false, "cluj");
        when(carRepo.save(car)).thenReturn(car);
        carService.addNewCar(car);
        assertEquals(car, carRepo.save(car));

    }
}