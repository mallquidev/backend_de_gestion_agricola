package com.mllq.back.core.commons.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mllq.back.core.commons.dto.response.ApiResponse;
import com.mllq.back.core.commons.models.ApiError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
     @ExceptionHandler(NotFound.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(NotFound ex, HttpServletRequest request) {
        ApiError error = ApiError.notFound(ex, true, request);
        return buildErrorResponse(error);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(BadRequest ex, HttpServletRequest request) {
        ApiError error = ApiError.badRequest(ex, true, request);
        return buildErrorResponse(error);
    }

    @ExceptionHandler(Conflict.class)
    public ResponseEntity<ApiResponse<Void>> handleConflict(Conflict ex, HttpServletRequest request) {
        ApiError error = ApiError.dataIntegrityViolation(ex.getMessage(), true);
        return buildErrorResponse(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneral(Exception ex) {
        ApiError error = ApiError.internalServerError(ex);
        return buildErrorResponse(error);
    }

    private ResponseEntity<ApiResponse<Void>> buildErrorResponse(ApiError error) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
            .success(false)
            .message(error.getMessage())
            .data(null)
            .errors(List.of(error))
            .build();
        return ResponseEntity.status(error.getStatus()).body(response);
    }
}
