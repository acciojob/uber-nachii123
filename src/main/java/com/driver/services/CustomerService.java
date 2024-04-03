package com.driver.services;


import java.util.List;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.model.TripBooking;


public interface CustomerService {

	public void register(Customer customer);

	public void deleteCustomer(Long customerId);
	
	// public TripBooking bookedTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception;

//	void deleteCustomer(Long customerId);

	TripBooking bookedTrip(Long customerId, String fromLocation, String toLocation, double distanceInKm) throws Exception;

    public void cancelTrip(Long tripId);

	public void completeTrip(Long tripId);
	
}
