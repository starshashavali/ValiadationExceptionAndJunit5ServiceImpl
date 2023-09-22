package com.gis.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NotBlank(message = "email should not be null")
	private String email;

	@NotBlank(message = "email should not be null")
	private String password;

}
