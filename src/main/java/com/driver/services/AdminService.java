package com.driver.services;

import java.util.List;

import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;

public interface AdminService {

	public void adminRegister(Admin admin);

	public Admin updatePassword(Long adminId, String password);

	public void deleteAdmin(Long adminId);

	public List<Driver> getListOfDrivers();
	
	public List<Customer> getListOfCustomers();
}
