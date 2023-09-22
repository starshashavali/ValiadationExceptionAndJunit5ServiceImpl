package com.gis.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
	
	@NotBlank(message = "Name should not be empty")
	private String name;

	@NotBlank(message = "Email should not be empty")
	private String email;

	@NotNull(message = "phone number is mandator")
	@Size(min = 10,max = 13,message = "Invalid Phone Number" )
	private String phno;

}
