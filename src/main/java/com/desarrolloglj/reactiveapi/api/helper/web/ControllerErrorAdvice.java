package com.desarrolloglj.reactiveapi.api.helper.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ControllerErrorAdvice {
  @ExceptionHandler({WebExchangeBindException.class})
  public final ResponseEntity<ErrorResponse> handleWebExchangeBindExceptions(
          WebExchangeBindException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach((error) -> {
      errors.put(error.getField(), error.getDefaultMessage());
    });
    var errorsStr = errors.keySet().stream()
            .map(k -> k + ": " + errors.get(k))
            .collect(Collectors.joining(", ", "", ""));
    return error(HttpStatus.BAD_REQUEST, errorsStr);
  }

  @ExceptionHandler({ResponseStatusException.class})
  public final ResponseEntity<ErrorResponse> handleResponseStatusExceptions(
          ResponseStatusException ex) {
    return error(ex.getStatus(), ex.getReason());
  }

  @ExceptionHandler({RuntimeException.class})
  public final ResponseEntity<ErrorResponse> handleAllExceptions(RuntimeException ex) {
    log.error(ex.getMessage(), ex);
    return error(HttpStatus.INTERNAL_SERVER_ERROR, ex);
  }

  private ResponseEntity<ErrorResponse> error(HttpStatus status, String message) {
    return ResponseEntity.status(status).body(new ErrorResponse(status.value(), message));
  }

  private ResponseEntity<ErrorResponse> error(HttpStatus status, Exception e) {
    return ResponseEntity.status(status).body(new ErrorResponse(status.value(), e.getMessage()));
  }
}
