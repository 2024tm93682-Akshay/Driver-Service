package com.driverservice.controller;



import com.driverservice.dto.ApiResponse;
import com.driverservice.model.*;
import com.driverservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.driverservice.dto.ApiResponse;
import java.util.List;

@RestController
@RequestMapping("/v1/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping
    public ResponseEntity<ApiResponse> createDriver(@RequestBody Driver driver) {
        driverService.createDriver(driver);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Driver created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDriver(@PathVariable("id") String id) {
        return driverService.getDriverById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable("id") String id, @RequestParam boolean active) {
        driverService.updateDriverStatus(id, active);
        return ResponseEntity.ok(new ApiResponse(true, "Driver status updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDriver(@PathVariable("id") String id) {
        driverService.deleteDriver(id);
        return ResponseEntity.ok(new ApiResponse(true, "Driver deleted successfully"));
    }
}

