package com.driverservice.repository;


import com.driverservice.model.Driver;
import com.driverservice.queries.DriverSqlQueries;
import com.driverservice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class DriverRepositoryImpl implements DriverRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<Driver> driverRowMapper = new RowMapper<>() {
        @Override
        public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
            Driver d = new Driver();
            d.setDriverId(rs.getString("driver_id"));
            d.setName(rs.getString("name"));
            d.setPhone(rs.getString("phone"));
            d.setVehicleType(rs.getString("vehicle_type"));
            d.setVehiclePlate(rs.getString("vehicle_plate"));
            d.setActive(rs.getBoolean("is_active"));
            return d;
        }
    };

    @Override
    public void createDriver(Driver driver) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("driverId", driver.getDriverId())
                .addValue("name", driver.getName())
                .addValue("phone", driver.getPhone())
                .addValue("vehicleType", driver.getVehicleType())
                .addValue("vehiclePlate", driver.getVehiclePlate())
                .addValue("isActive", driver.isActive());
        jdbcTemplate.update(DriverSqlQueries.INSERT_DRIVER, params);
    }

    @Override
    public Optional<Driver> getDriverById(String driverId) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("driverId", driverId);
        List<Driver> result = jdbcTemplate.query(DriverSqlQueries.GET_DRIVER_BY_ID, params, driverRowMapper);
        return result.stream().findFirst();
    }

    @Override
    public List<Driver> getAllDrivers() {
        return jdbcTemplate.query(DriverSqlQueries.GET_ALL_DRIVERS, driverRowMapper);
    }

    @Override
    public void updateDriverStatus(String driverId, boolean isActive) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("driverId", driverId)
                .addValue("isActive", isActive);
        jdbcTemplate.update(DriverSqlQueries.UPDATE_DRIVER_STATUS, params);
    }

    @Override
    public void deleteDriver(String driverId) {
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("driverId", driverId);
        jdbcTemplate.update(DriverSqlQueries.DELETE_DRIVER, params);
    }
}

