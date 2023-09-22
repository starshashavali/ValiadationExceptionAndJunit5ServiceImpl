package com.gis.service;

import com.gis.dto.LoginRequest;
import com.gis.dto.RegistrationRequest;
import com.gis.dto.UnlockRequest;

public interface UserService {

	String createUser(RegistrationRequest form);

	String unlockAccount(UnlockRequest form);

	//String resetPassword(String email);

	String loginUser(LoginRequest form);

}
