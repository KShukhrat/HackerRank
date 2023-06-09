package uz.pdp.hackerrank.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.pdp.hackerrank.exception.DataNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
  @ExceptionHandler(value = DataNotFoundException.class)
  public ResponseEntity<String> dataNotFoundExceptionHandler(
          DataNotFoundException e
  ){
      return ResponseEntity.status(404).body(e.getMessage());
  }
}
