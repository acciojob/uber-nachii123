package com.driver.services.impl;

import com.driver.model.*;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Optional<Customer> customer = customerRepository2.findById(customerId);
		if(customer.isPresent()){
			customerRepository2.delete(customer.get());
		}

	}


	@Override
	public TripBooking bookedTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception {
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		List<Driver> availableDrivers = driverRepository2.findByAvailableTrueOrderByDriverIdAsc();
		if (availableDrivers.isEmpty()) {
			throw new Exception("No cab available!");
		}
		Driver driver= availableDrivers.get(0);
		double rate = driver.getCab().getPerKmRate() * distanceInKm;

		TripBooking tripBooking = new TripBooking(fromLocation, toLocation, distanceInKm);
		tripBooking.setDriver(driver);
		tripBooking.setCustomer(customerRepository2.getById(customerId));
		tripBooking.setStatus(TripStatus.CONFIRMED);
		tripBooking.setBill(rate);

		driver.setAvailable(false);
		driverRepository2.save(driver);


		// Save trip booking
		return tripBookingRepository2.save(tripBooking);
	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> booking = tripBookingRepository2.findById(tripId);
		if(booking.isPresent()){
			TripBooking tripBooking = booking.get();
			tripBooking.setStatus(TripStatus.CANCELED);
			tripBooking.getDriver().setAvailable(true);
			tripBookingRepository2.save(tripBooking);
		}

	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> tripBooking = tripBookingRepository2.findById(tripId);
		TripBooking tripBooking1 = tripBooking.get();
		tripBooking1.setStatus(TripStatus.COMPLETED);
		tripBooking1.getDriver().setAvailable(true);
		tripBookingRepository2.save(tripBooking1);


	}


}
