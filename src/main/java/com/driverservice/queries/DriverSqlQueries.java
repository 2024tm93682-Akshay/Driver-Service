package com.driverservice.queries;


public class DriverSqlQueries {

    public static final String INSERT_DRIVER = """
        INSERT INTO drivers (driver_id, name, phone, vehicle_type, vehicle_plate, is_active)
        VALUES (:driverId, :name, :phone, :vehicleType, :vehiclePlate, :isActive)
    """;

    public static final String GET_DRIVER_BY_ID = """
        SELECT * FROM drivers WHERE driver_id = :driverId
    """;

    public static final String GET_ALL_DRIVERS = """
        SELECT * FROM drivers
    """;

    public static final String UPDATE_DRIVER_STATUS = """
        UPDATE drivers SET is_active = :isActive WHERE driver_id = :driverId
    """;

    public static final String DELETE_DRIVER = """
        DELETE FROM drivers WHERE driver_id = :driverId
    """;
}
