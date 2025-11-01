package com.driverservice.repository;



import com.driverservice.model.Driver;
import java.util.List;
import java.util.Optional;

public interface DriverRepository {
    void createDriver(Driver driver);
    Optional<Driver> getDriverById(String driverId);
    List<Driver> getAllDrivers();
    void updateDriverStatus(String driverId, boolean isActive);
    void deleteDriver(String driverId);
}

