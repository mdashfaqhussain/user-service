package com.hasdedin.user.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hasdedin.user.constants.ProjectContants;
import com.hasdedin.user.dto.ResponseModel;

@ControllerAdvice
public class AuthorisationExceptionHandler {
	
	
	
	@ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<ResponseModel> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		 ResponseModel responseModel = new ResponseModel();
	        responseModel.setStatuscode(ProjectContants.USER_ALREADY_EXISTS_STATUS_CODE);
	        responseModel.setStatus(ProjectContants.USER_ALREADY_EXISTS_STATUS);
	        responseModel.setMessage(ProjectContants.USER_ALREADY_EXISTS_MESSAGE);
        return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body(responseModel);
    }

}
