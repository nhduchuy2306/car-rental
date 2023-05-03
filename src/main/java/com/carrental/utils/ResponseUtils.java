package com.carrental.utils;

import org.springframework.http.HttpStatus;

import com.carrental.models.response.ApiResponse;
import com.carrental.models.response.Meta;

public class ResponseUtils {
    public static <T> ApiResponse<T> success(T data, String message) {
        Meta meta = Meta.builder()
            .status(HttpStatus.OK.value())
            .message(message)
            .error("")
            .build();
        return new ApiResponse<>(data, meta);
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String message, Object error) {
        Meta meta = Meta.builder()
            .status(status.value())
            .message(message)
            .error(error)
            .build();
        return new ApiResponse<>(null, meta);
    }
}
