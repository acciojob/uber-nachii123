package com.driver.controllers;

import com.driver.services.impl.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/driver")
public class DriverController {

	@Autowired
	private DriverServiceImpl driverService;
	
	@PostMapping(value = "/register")
	public ResponseEntity<String> registerDriver(@RequestParam String mobile, @RequestParam String password){
//		ResponseEntity<>(driverService.register(mobile,password),HttpStatus.OK);
		driverService.register(mobile,password);
		return new ResponseEntity<>("has been saved",HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete")
	public void deleteDriver(@RequestParam Long driverId){
		driverService.removeDriver(driverId);

	}

	@PutMapping("/status")
	public void updateStatus(@RequestParam Long driverId){
		driverService.updateStatus(driverId);
	}
}
