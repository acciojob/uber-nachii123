package com.driver.services;

import com.driver.model.Driver;

public interface DriverService {

		public void register(String mobile, String password);
		public void removeDriver(Long driverId);
		public void updateStatus(Long driverId);
}
