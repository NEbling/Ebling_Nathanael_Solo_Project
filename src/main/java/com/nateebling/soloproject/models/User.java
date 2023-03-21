package com.nateebling.soloproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
    
@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="First name cannot be blank")
    @Size(min=3, max=30, message="First name must be between 3 and 30 characters")
    private String firstName;
    

	@NotEmpty(message="Last name cannot be blank")
    @Size(min=3, max=30, message="Last name must be between 3 and 30 characters")
    private String lastName;
    
    @NotEmpty(message="Email cannot be blank")
    @Email(message="Please enter a valid email")
    private String email;
    
    @NotEmpty(message="Password cannot be blank")
    @Size(min=8, max=255, message="Password must be between 8-255 characters")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm password cannot be blank")
    @Size(min=8, max=255, message="Confirm Password must be between 8-255 characters")
    private String confirmPassword;
    

    public User() {
    	
    }
    


	public Long getId() {
		return id;
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
	
	public void setId(Long id) {
		this.id = id;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

  
}