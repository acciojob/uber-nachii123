package com.driver.services.impl;

import com.driver.model.Cab;
import com.driver.repository.CabRepository;
import com.driver.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Driver;
import com.driver.repository.DriverRepository;

import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository3;

	@Autowired
	CabRepository cabRepository3;

	@Override
	public void register(String mobile, String password){
		//Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.
		Driver driver = new Driver();
		driver.setMobile(mobile);
		driver.setPassword(password);

		Cab cab=new Cab();
		cab.setAvailable(true);
		cab.setPerKmRate(10);

		driver.setCab(cab);

		driverRepository3.save(driver);

	}

	@Override
	public void removeDriver(Long driverId){
		// Delete driver without using deleteById function
		Optional<Driver> driver = driverRepository3.findById(driverId);
		if(driver.isPresent()){
			driverRepository3.delete(driver.get());
		}

	}

	@Override
	public void updateStatus(Long driverId){
		//Set the status of respective car to unavailable
		// Find the cab associated with the driver ID
		Optional<Driver> optionalDriver = driverRepository3.findById(driverId);
		if (optionalDriver.isPresent()) {
			Driver driver = optionalDriver.get();
			Cab cab = driver.getCab();
			if (cab != null) {
				// Update the availability status of the cab to unavailable
				cab.setAvailable(false);
				// Save the updated cab back to the database
				cabRepository3.save(cab);
			} else {
				System.out.println("No cab associated with the provided driver ID.");
			}
		} else {
			System.out.println("Driver with the provided ID not found.");
		}

	}
}
