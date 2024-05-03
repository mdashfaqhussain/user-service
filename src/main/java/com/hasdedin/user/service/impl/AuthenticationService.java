package com.hasdedin.user.service.impl;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hasdedin.user.config.exception.UserAlreadyExistsException;
import com.hasdedin.user.constants.ProjectContants;
import com.hasdedin.user.dto.LoginUserdto;
import com.hasdedin.user.dto.RegisterUserdto;
import com.hasdedin.user.dto.ResponseModel;
import com.hasdedin.user.entity.BudgetRole;
import com.hasdedin.user.entity.BudgetUser;
import com.hasdedin.user.repository.IUserRepository;
import com.hasdedin.user.service.IAuthenticationService;
import com.hasdedin.user.utility.Mapper;
import com.hasdedin.user.utility.ResponseUtility;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AuthenticationService implements IAuthenticationService {
	
	
	@Autowired
	private IUserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	

	
	@Autowired
	private ResponseUtility responseUtility;
	
	@Autowired
	private JwtService jwtService;
	


	

	@Override
	public ResponseEntity<ResponseModel> saveUser(RegisterUserdto registerUserdto) {
		
	    try {
	    	log.info(repository.existsByName(registerUserdto.getName()).toString());
	    	log.info("inside Service method");
	        if(!repository.existsByName(registerUserdto.getName())) {
	        	log.info("Registered--------1");
	            BudgetUser newuser = Mapper.convertToBudgetUser(registerUserdto);
	            log.info("Registered--------2"+newuser.toString());
	            log.info(registerUserdto.getPassword());
	            newuser.setPassword(encoder.encode(registerUserdto.getPassword()));
	            log.info("Registered--------3");
	            
	            BudgetRole defaultRole = new BudgetRole();
	            defaultRole.setRole("ROLE_USER");
	            defaultRole.setRoleId(2);
	            newuser.getRole().add(defaultRole);
	            log.info("---------inside try"+newuser.toString());
	            repository.save(newuser);
	            log.info("Registered--------");
	            ResponseModel model = new ResponseModel();
	            model.setStatuscode(ProjectContants.USER_CREATED_STATUS_CODE);
	            model.setStatus(ProjectContants.USER_CREATED_MESSAGE);
	            model.setMessage(ProjectContants.USER_CREATED_MESSAGE);
	            
	            return responseUtility.createResponse(model);
	        } else {
	            // If user already exists, return appropriate error response
	            ResponseModel errorModel = new ResponseModel();
	            errorModel.setStatuscode(ProjectContants.USER_ALREADY_EXISTS_STATUS_CODE);
	            errorModel.setStatus(ProjectContants.USER_ALREADY_EXISTS_MESSAGE);
	            errorModel.setMessage(ProjectContants.USER_ALREADY_EXISTS_MESSAGE);
	            
	            log.info("Inside Else");
	            return responseUtility.createResponse(errorModel);
	        }
	    } catch (Exception e) {
	        log.info("inside catch!!!");
	        ResponseModel errorModel = new ResponseModel();
	        errorModel.setStatuscode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        errorModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	        errorModel.setMessage("An error occurred while processing the request.");
	        
	        return responseUtility.createResponse(errorModel);
	    }
	
	}

	@Override
	public ResponseEntity<ResponseModel> loginUser(LoginUserdto loginUserdto) {
		String token = jwtService.generateToken(loginUserdto.getName());
		ResponseModel model = new ResponseModel();
		model.setData(token);
		model.setStatus(ProjectContants.USER_LOGIN_STATUS);
		model.setMessage(ProjectContants.USER_LOGIN_MESSAGE);
		model.setStatuscode(ProjectContants.USER_LOGIN_STATUS_CODE);
		
		return responseUtility.createResponse(model);
	}

}
