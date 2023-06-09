package uz.pdp.hackerrank.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.pdp.hackerrank.exception.DataNotFoundException;
import uz.pdp.hackerrank.exception.RequestValidationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
  @ExceptionHandler(value = DataNotFoundException.class)
  public ResponseEntity<String> dataNotFoundExceptionHandler(
          DataNotFoundException e
  ){
      return ResponseEntity.status(404).body(e.getMessage());
  }
  @ExceptionHandler(value = RequestValidationException.class)
    public ResponseEntity<String> requestValidationException(
            RequestValidationException e
  ){
      return ResponseEntity.status(400).body(e.getMessage());
  }
}
