package com.Jacob.ridesafebackend.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Jacob.ridesafebackend.dto.PassengerStatusCoordiantesRequest;
import com.Jacob.ridesafebackend.models.LoginPassenger;
import com.Jacob.ridesafebackend.models.Passenger;
import com.Jacob.ridesafebackend.service.GoogleAuthentication;
import com.Jacob.ridesafebackend.service.PassengerService;

import jakarta.servlet.http.HttpSession;

//*FOR PASSENGER PROCESS ROUTES*
@CrossOrigin(origins = "http://localhost:3000")
@RestController // Changed from Controller to RestController
public class PassengerController {
	// TODO need to make passenger service
	@Autowired
	private final PassengerService passengerServ;
	private final GoogleAuthentication GoogleAuth;
	

	public PassengerController(PassengerService passengerServ,GoogleAuthentication GoogleAuth) {
		this.passengerServ = passengerServ;
		this.GoogleAuth = GoogleAuth;
	}

	@PostMapping("/new/passenger")
	public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger, HttpSession session) {

		Passenger createPassenger = passengerServ.createPassenger(passenger);

		return ResponseEntity.ok(createPassenger);
	}

	// Current Driver in session route
	@GetMapping("/passenger")
	public ResponseEntity<List<Passenger>> getAllPassenges() {
		List<Passenger> passenger = passengerServ.getAllPassengers();
		return ResponseEntity.ok(passenger);
	}

	// Getting One Driver
	@GetMapping("/passenger/{id}")
	public ResponseEntity<?> getPassengerById(@PathVariable("id") String id) { // Pass in the drivers Id to send to the
																				// frontend

		Optional<Passenger> passenger = passengerServ.getPassengerById(id); // fetching a driver by its id

		if (passenger.isEmpty()) { // if the driver does not exist, return "Driver not found."
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Passenger not found.");
		}

		return ResponseEntity.ok(passenger.get()); // return the Driver
	}

	@PostMapping("/login/passenger")
	    public ResponseEntity<Map<String, String>> login(@RequestBody LoginPassenger loginPassenger,HttpSession session) {
	        // Fetch the driver by email
		   Passenger existingPassenger = passengerServ.getPassenger(loginPassenger.getEmail());
	        
	        if (existingPassenger == null) {
	            return ResponseEntity
	                    .status(HttpStatus.BAD_REQUEST)
	                    .body(Map.of("message", "Unknown email"));
	        }

	        // Check the password using BCrypt
	        if (!BCrypt.checkpw(loginPassenger.getPassword(), existingPassenger.getPassword())) {
	            return ResponseEntity
	                    .status(HttpStatus.BAD_REQUEST)
	                    .body(Map.of("message", "Incorrect password"));
	        }
	   
	        // Login successful
	        return ResponseEntity.ok(Map.of("message", "Login successful","id",existingPassenger.getId()));
	      
	    }


		//passenger coordiantes request
	@PutMapping("/{id}/status/passenger")
	public ResponseEntity<String> updatePassengerStatus(@PathVariable("id") String id, @RequestBody PassengerStatusCoordiantesRequest request) {
		passengerServ.updatePasengerStatus(id,request.getLongitude(),request.getLatitude());
		System.out.println("Passenger Coordinates" + request);
		return ResponseEntity.ok("Passenger Status updated");
	}
	
	
	// WORKING FOR MEG REPO
}
