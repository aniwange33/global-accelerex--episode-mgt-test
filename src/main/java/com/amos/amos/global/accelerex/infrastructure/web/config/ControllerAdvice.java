package com.amos.amos.global.accelerex.infrastructure.web.config;

import com.amos.amos.global.accelerex.Usecase.api.exception.InValidRequest;
import com.amos.amos.global.accelerex.infrastructure.web.model.ApplicationApiResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
class RestApiControllerAdvice {
    @ExceptionHandler(InValidRequest.class)
    public ResponseEntity<ApplicationApiResponse<String>> handleKnownException(InValidRequest e) {
        val apiResponse = new ApplicationApiResponse<String>("error", e.getMessage());
        log.info("error message: {}", e.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
