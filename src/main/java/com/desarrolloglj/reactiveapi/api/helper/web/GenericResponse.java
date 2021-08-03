package com.desarrolloglj.reactiveapi.api.helper.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {
  private int status;
  private String message;
}
