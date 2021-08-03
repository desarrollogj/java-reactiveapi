package com.desarrolloglj.reactiveapi.api.helper.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse extends GenericResponse {
  private String errorCode;
  private String error;

  public ErrorResponse(int status, String message) {
    super(status, message);
  }
}
