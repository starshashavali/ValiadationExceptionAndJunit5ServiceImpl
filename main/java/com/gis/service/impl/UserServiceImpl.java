package com.gis.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gis.domain.User;
import com.gis.dto.LoginRequest;
import com.gis.dto.RegistrationRequest;
import com.gis.dto.UnlockRequest;
import com.gis.repo.UserRepo;
import com.gis.service.UserService;
import com.gis.utils.EmailUtils;
import com.gis.utils.PwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	private PwdUtils pwdUtils;

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private HttpSession session;

	@Override
	public String createUser(RegistrationRequest request) {
		User entity = userRepo.findByEmail(request.getEmail());
		if (entity != null) {
			if ("Locked".equalsIgnoreCase(entity.getAccountStatus())) {
				return "Your account need to be unlocked";
			} else if ("Unlocked".equalsIgnoreCase(entity.getAccountStatus())) {
				return "Email already exists ,Please login !";
			}
		}

		User user = new User();
		BeanUtils.copyProperties(request, user);

		String pwd = pwdUtils.generatePwd(6);

		StringBuffer sb = new StringBuffer("");

		sb.append("<h1>Hey, " + request.getName() + "</h1>");

		sb.append("<h3>Unlock your account with this temporary password</h3>");

		sb.append("<br>");

		sb.append("<p>Temporary password : <B>" + pwd + "</B> </p>");

		sb.append("<br>");

		sb.append("<a href=\"http://localhost:9090/unlock?mail=" + request.getEmail()
				+ "\">Click here to unlock your account </a>");

		emailUtils.sendEmail(request.getEmail(), "Unlock your account ", String.valueOf(sb));

		user.setAccountStatus("Locked");
		user.setPassword(pwd);

		userRepo.save(user);

		return "Mail sent to " + request.getEmail();
	}

	@Override
	public String unlockAccount(UnlockRequest form) {

		User user = userRepo.findByEmail(form.getEmail());

		if (!form.getTempPassword().equals(user.getPassword())) {
			return "Temporary Password is not matching";
		}
		if (!form.getNewPassword().equals(form.getConfirmPassword())) {
			return "Password not matching";
		}
		user.setPassword(form.getNewPassword());
		user.setAccountStatus("Unlocked");

		userRepo.save(user);
		return "Account unlocked";
	}



	@Override
	public String loginUser(LoginRequest form) {
		User entity = userRepo.findByEmailAndPassword(form.getEmail(), form.getPassword());
		if (entity == null) {
			return "Invalid credentials";
		}
		if (entity.getAccountStatus().equalsIgnoreCase("locked")) {
			return "Your account need to be unlocked";
		}
		session.setAttribute("userID", entity.getUserId());
		return "success";
	}

}
