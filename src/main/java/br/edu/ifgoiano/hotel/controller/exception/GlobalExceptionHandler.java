package br.edu.ifgoiano.hotel.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.FieldError;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> notFoundException(ResourceNotFoundException ex, WebRequest webRequest){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorDetails errorDetails = new ErrorDetails(new Date(),status.value(), ex.getMessage(), getRequestPath());
        return ResponseEntity.status(status).body(errorDetails);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ErrorDetails> badCredentialsException(InternalAuthenticationServiceException ex, WebRequest webRequest){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDetails errorDetails = new ErrorDetails(new Date(),status.value(), "Credenciais inválidas. Verifique se o login e a senha estão corretos.", getRequestPath());
        return ResponseEntity.status(status).body(errorDetails);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErrorDetails> badRequestException(ResourceBadRequestException ex, WebRequest webRequest){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDetails errorDetails = new ErrorDetails(new Date(),status.value(), ex.getMessage(), getRequestPath());
        return ResponseEntity.status(status).body(errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)
            error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> ((FieldError) error).getDefaultMessage())
                .collect(Collectors.joining(", "));

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDetails errorDetails = new ErrorDetails(new Date(),status.value(), errorMessage, getRequestPath());

        return ResponseEntity.status(status).body(errorDetails);
    }

    public String getRequestPath(){
        return request.getRequestURI();
    }
}
