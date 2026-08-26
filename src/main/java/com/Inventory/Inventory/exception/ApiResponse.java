package com.Inventory.Inventory.exception;

import java.time.LocalDateTime;

public record ApiResponse<T>(
        String status,
        String message,
        T data,
        LocalDateTime timestamp
) {

    public static <T> ApiResponse<T> success(
            String message,
            T data) {

        return new ApiResponse<>(
                "SUCCESS",
                message,
                data,
                LocalDateTime.now()
        );
    }
}
