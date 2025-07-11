package com.mutonya.hmisapplication.auth.utils.error;


import com.mutonya.hmisapplication.auth.utils.apiresponse.ApiResponse;
import com.mutonya.hmisapplication.auth.utils.apiresponse.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle general exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {
        return ResponseBuilder.buildErrorResponse(
                "An unexpected error occurred: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // Handle validation errors (e.g. @Valid failures)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage
                ));

        return ResponseBuilder.buildValidationErrorResponse(errors);
    }

    // Handle malformed JSON or bad input format
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleUnreadableMessage(HttpMessageNotReadableException ex) {
        return ResponseBuilder.buildErrorResponse(
                "Invalid request format: " + ex.getMostSpecificCause().getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeException(RuntimeException ex) {
        return ResponseBuilder.buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    // Add more specific exception handlers as needed
}
