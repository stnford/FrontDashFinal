package com.frontdash.backend.service;

import com.frontdash.backend.repository.FrontdashRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
public class FrontdashService {

    private final FrontdashRepository repository;

    public FrontdashService(FrontdashRepository repository) {
        this.repository = repository;
    }

    public void registerRestaurant(String restName, String street1, String street2, String city, String state, String zip,
                                   String contactName, String contactEmail, String contactPhone) {
        int addressId = repository.createAddress(street1, street2, city, state, zip);
        repository.requestRestaurantRegistration(restName, addressId, contactName, contactEmail, contactPhone);
    }

    public void approveRestaurant(String restName, String decision) {
        repository.setRestaurantApproval(restName, decision);
    }

    public void requestWithdrawal(String restName) {
        repository.requestWithdrawal(restName);
    }

    public void resolveWithdrawal(String restName, String decision) {
        repository.setWithdrawalDecision(restName, decision);
    }

    public List<Map<String, Object>> listRestaurants(boolean includePending, boolean includeInactive) {
        return repository.listRestaurants(includePending, includeInactive);
    }

    public List<Map<String, Object>> listPendingRegistrations() {
        return repository.listPendingRegistrations();
    }

    public void createStaff(String username, String password, String firstName, String lastName) {
        repository.createStaff(username, password, firstName, lastName);
    }

    public void setStaffStatus(String username, String status) {
        repository.setStaffStatus(username, status);
    }

    public List<Map<String, Object>> listStaff() {
        return repository.listStaff();
    }

    public void createDriver(String driverName) {
        repository.createDriver(driverName);
    }

    public void setDriverStatus(String driverName, String status) {
        repository.setDriverStatus(driverName, status);
    }

    public List<Map<String, Object>> listDrivers() {
        return repository.listDrivers();
    }

    public int createOrder(String restName) {
        return repository.createOrder(restName);
    }

    public void assignDriver(int orderNumber, String driverName) {
        repository.assignDriver(orderNumber, driverName);
    }

    public void setDelivery(int orderNumber, LocalDate date, LocalTime time) {
        repository.setDeliveryTime(orderNumber, Date.valueOf(date), Time.valueOf(time));
    }

    public List<Map<String, Object>> listOrders() {
        return repository.listOrders();
    }

    public void updateMenuItem(String restName, int itemId, String name, String desc, double price, String isAvailable) {
        repository.updateMenuItem(restName, itemId, name, desc, price, isAvailable);
    }

    public int createMenuItem(String restName, String name, String desc, double price, String isAvailable) {
        return repository.createMenuItem(restName, name, desc, price, isAvailable);
    }

    public void updateHours(String restName, String dayOfWeek, String openTime, String closeTime, String isClosed) {
        repository.updateHours(restName, dayOfWeek, openTime, closeTime, isClosed);
    }

    public Map<String, Object> getOrderSummary(int orderNumber) {
        return repository.getOrderSummary(orderNumber);
    }

    public List<Map<String, Object>> getMenu(String restName) {
        return repository.getMenuByRestaurant(restName);
    }

    public List<Map<String, Object>> getHours(String restName) {
        return repository.getHoursByRestaurant(restName);
    }
}
