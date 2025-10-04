package com.openclassrooms.mddapi.configuration;

import com.openclassrooms.mddapi.dtos.ErrorResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ProjectExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Generic exception handler for all non specifically handled exceptions
     *
     * @param e the exception
     * @return the error response
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleException(Exception e) {
        return new ErrorResponseDto(e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDto handleAuthenticationException(AuthenticationException e) {
        return new ErrorResponseDto(e.getMessage());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseDto handleAuthorizationDeniedException(AuthorizationDeniedException e) {
        return new ErrorResponseDto(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleEntityNotFoundException(EntityNotFoundException e) {
        return new ErrorResponseDto(e.getMessage());
    }

    /**
     * Modifying spring exceptions response to be project specific
     *
     * @param body       the body to use for the response
     * @param headers    the headers to use for the response
     * @param statusCode the status code to use for the response
     * @param request    the current request
     * @return the response in ErrorResponseDto format
     */
    @Override
    protected ResponseEntity<Object> createResponseEntity(
            @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        var message = "Unnknown error";
        if (body instanceof String bodyMessage) {
            message = bodyMessage;
        }
        if (body instanceof ProblemDetail problemDetail) {
            message = problemDetail.getTitle() + ": " + problemDetail.getDetail();
        }

        var errorBody = new ErrorResponseDto(message);
        return super.createResponseEntity(errorBody, headers, statusCode, request);
    }
}
