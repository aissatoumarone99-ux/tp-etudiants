package sn.isepat.tp_etudiants.exception;

import sn.isepat.tp_etudiants.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailDejaExistantException.class)
    public ResponseEntity<ErrorResponse> handleEmailDejaExistant(EmailDejaExistantException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(LocalDateTime.now(), 409, ex.getMessage()));
    }

    @ExceptionHandler(AuthentificationException.class)
    public ResponseEntity<ErrorResponse> handleAuthentification(AuthentificationException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(LocalDateTime.now(), 401, ex.getMessage()));
    }
}