package com.driverservice.service;

import com.driverservice.model.Driver;
import com.driverservice.repository.DriverRepository;
import com.driverservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public void createDriver(Driver driver) {
        driverRepository.createDriver(driver);
    }

    @Override
    public Optional<Driver> getDriverById(String driverId) {
        return driverRepository.getDriverById(driverId);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.getAllDrivers();
    }

    @Override
    public void updateDriverStatus(String driverId, boolean isActive) {
        driverRepository.updateDriverStatus(driverId, isActive);
    }

    @Override
    public void deleteDriver(String driverId) {
        driverRepository.deleteDriver(driverId);
    }
}
