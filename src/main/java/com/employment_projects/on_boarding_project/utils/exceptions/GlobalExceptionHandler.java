package com.employment_projects.on_boarding_project.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle Employee Not Found Exception
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex, WebRequest request) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(

                ex.getStatus(), ex.getStatus().value(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    // Handle Generic Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {

        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status for unknown exceptions

        CustomErrorResponse errorResponse = new CustomErrorResponse(

                status, status.value(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(errorResponse, status);
    }
}
