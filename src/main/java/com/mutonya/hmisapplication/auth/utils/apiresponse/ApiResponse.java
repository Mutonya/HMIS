package com.mutonya.hmisapplication.auth.utils.apiresponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String timestamp;
    private int status;
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    public String getTimestamp() { return timestamp; }
    public int getStatus() { return status; }

    // ✅ Proper constructor assigning all fields
    public ApiResponse(boolean success, String message, T data, String timestamp, int status) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp;
        this.status = status;
    }

    // ✅ Static success factory
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(
                true,
                "Success",
                data,
                LocalDateTime.now().toString(),
                HttpStatus.OK.value()
        );
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(
                true,
                message,
                data,
                LocalDateTime.now().toString(),
                HttpStatus.OK.value()
        );
    }

    // ✅ Static error factory
    public static ApiResponse<?> error(String message, HttpStatus status) {
        return new ApiResponse<>(
                false,
                message,
                null,
                LocalDateTime.now().toString(),
                status.value()
        );
    }
}
