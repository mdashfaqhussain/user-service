package com.hasdedin.user.service;

import org.springframework.http.ResponseEntity;

import com.hasdedin.user.dto.LoginUserdto;
import com.hasdedin.user.dto.RegisterUserdto;
import com.hasdedin.user.dto.ResponseModel;

public interface IAuthenticationService {
	
	 ResponseEntity<ResponseModel>  saveUser(RegisterUserdto registerUserdto);
	 
	 ResponseEntity<ResponseModel> loginUser(LoginUserdto loginUserdto);
	
}
