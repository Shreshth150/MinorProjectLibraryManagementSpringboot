package com.example.library.management.minor_project_library_management.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CustomGlobalException extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public void springHandleNotFound(HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public void springHhandleNotFound2(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }




}
