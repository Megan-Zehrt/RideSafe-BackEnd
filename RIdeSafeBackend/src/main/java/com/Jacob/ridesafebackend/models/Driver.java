package com.Jacob.ridesafebackend.models;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Document(collection = "drivers")
public class Driver {

	@Id
	private String id;
	
	private boolean isOnline;
	
	private int DriverRate;
	
	@NotEmpty(message = "First name is required!")
	@Size(min = 3,max = 15,message = "First name must be between 3 and 15 characters")
	private String firstName;
	
	@NotEmpty(message = "Last name is required!")
	@Size(min = 3,max = 15,message = "Last name must be between 3 and 15 characters")
	private String lastName;
	
	@NotEmpty(message = "Email is required!")
	@Email(message = "Please enter a valid email!")
	@Indexed(unique = true)
	 private String email;
	
	@NotEmpty(message = "Password is required!")
	@Size(min = 8,max = 128,message = "Password must be between 8 and 200 characters" )
	private String password;
	
	@Transient
	@NotEmpty(message = "ConfirmPassword is required!")
	@Size(min = 8,max = 128,message = "Confirm Password must be between 8 and 200 characters" )
	private String confirm;
	
	@NotEmpty(message = "License plate is required!")
	@Size(min = 5,max = 9,message = "License plate must be between 3 and 9 characters" )
	private String licensePlate;
	
	private String googleId; 
	

	public int getDriverRate() {
		return DriverRate;
	}

	public void setDriverRate(int driverRate) {
		DriverRate = driverRate;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	//What we call in the routes
	public Driver() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setIsOnline(boolean isOnline) { // This method should be setIsOnline, not setOnline
        this.isOnline = isOnline;
    }

	
	
	}
//Driver model