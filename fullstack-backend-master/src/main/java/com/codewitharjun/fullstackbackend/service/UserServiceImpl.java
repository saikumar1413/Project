package com.codewitharjun.fullstackbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewitharjun.fullstackbackend.model.User;
import com.codewitharjun.fullstackbackend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepo;

	@Override
	public LoginMessage loginemployee(User user) {
		// TODO Auto-generated method stub
		String msg = "";
		User user1 = userrepo.findbyusername(user.getUsername());
		if (user1 != null) {
			String password = user.getPassword();
			if (password.equals(password)) {
				return new LoginMessage("Login Success", true);
			} else {
				return new LoginMessage("Login Failed", false);
			}
		} else {
			return new LoginMessage("password Not Match", false);
		}
	}

}
