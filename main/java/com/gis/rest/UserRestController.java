package com.gis.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gis.dto.RegistrationRequest;
import com.gis.service.UserService;

@RestController
public class UserRestController {
	@Autowired
	UserService userService;

  

}