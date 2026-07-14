package sn.isepat.tp_etudiants.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import sn.isepat.tp_etudiants.dto.ErrorResponse;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(
            DuplicateException ex){

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                    new ErrorResponse(
                        LocalDateTime.now(),
                        409,
                        ex.getMessage()
                    )
                );
    }



    @ExceptionHandler(EtudiantIntrouvableException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            EtudiantIntrouvableException ex){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                    new ErrorResponse(
                        LocalDateTime.now(),
                        404,
                        ex.getMessage()
                    )
                );
    }

}