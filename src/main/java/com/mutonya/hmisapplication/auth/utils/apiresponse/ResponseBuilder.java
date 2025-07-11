package com.mutonya.hmisapplication.auth.utils.apiresponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Map;

public class ResponseBuilder {


    public static <T> ResponseEntity<ApiResponse<T>> buildSuccessResponse(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> buildSuccessResponse(T data, String message) {
        return ResponseEntity.ok(ApiResponse.success(data, message));
    }

    public static <T> ResponseEntity<ApiResponse<T>> buildCreatedResponse(T data, String message) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(data, message));
    }

    public static ResponseEntity<ApiResponse<?>> buildErrorResponse(String message, HttpStatus status) {
//        ApiResponse<Object> response = new ApiResponse<>(
//                false,
//                message,
//                null,
//                LocalDateTime.now().toString(),
//                status.value()
//        );
        return ResponseEntity.status(status).body(ApiResponse.error(message, status));
    }

    public static ResponseEntity<ApiResponse<?>> buildValidationErrorResponse(Map<String, String> errors) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(
                        false,
                        "Validation failed",
                        errors,
                        LocalDateTime.now().toString(),
                        HttpStatus.BAD_REQUEST.value()
                ));
    }
}
