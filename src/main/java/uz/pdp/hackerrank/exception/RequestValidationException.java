package uz.pdp.hackerrank.exception;

import org.springframework.validation.ObjectError;

import java.util.List;

public class RequestValidationException extends RuntimeException {
    private String message;
    public RequestValidationException(List<ObjectError> errors) {
      StringBuilder objectErrors=new StringBuilder();
        for (ObjectError error : errors) {
            objectErrors.append(error.getDefaultMessage()).append("\n");
        }
        this.message=objectErrors.toString();
    }
    @Override
    public String getMessage() {
        return message;
    }

}
