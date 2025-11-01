package com.driverservice.model;


public class Driver {
    private String driverId;
    private String name;
    private String phone;
    private String vehicleType;
    private String vehiclePlate;
    private boolean isActive;

    // --- Constructors ---
    public Driver() {
    }

    public Driver(String driverId, String name, String phone, String vehicleType, String vehiclePlate, boolean isActive) {
        this.driverId = driverId;
        this.name = name;
        this.phone = phone;
        this.vehicleType = vehicleType;
        this.vehiclePlate = vehiclePlate;
        this.isActive = isActive;
    }

    // --- Getters and Setters ---
    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // --- toString (optional, helpful for logs) ---
    @Override
    public String toString() {
        return "Driver{" +
                "driverId='" + driverId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", vehiclePlate='" + vehiclePlate + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
