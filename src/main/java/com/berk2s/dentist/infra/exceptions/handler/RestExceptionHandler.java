package com.berk2s.dentist.infra.exceptions.handler;

import com.berk2s.dentist.domain.role.exception.RoleNameTakenException;
import com.berk2s.dentist.infra.adapters.user.exceptions.InsufficientAuthorityException;
import com.berk2s.dentist.infra.exceptions.EntityNotFoundException;
import com.berk2s.dentist.domain.error.ErrorDesc;
import com.berk2s.dentist.infra.exceptions.ErrorType;
import com.berk2s.dentist.infra.exceptions.UniquenessException;
import com.berk2s.dentist.infra.exceptions.handler.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.warn("HttpMessageNotReadableException: {}", ex.getMessage());
        return createErrorResponse(ErrorType.INVALID_REQUEST.getType(), ErrorDesc.INVALID_REQUEST.getDesc(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.warn("MethodArgumentNotValidException: {}", ex.getMessage());
        var errorList = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return createErrorResponse(ErrorType.INVALID_REQUEST.getType(), ErrorDesc.INVALID_REQUEST.getDesc(), errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse>  handleConstraintViolation(ConstraintViolationException ex) {
        log.warn("ConstraintViolationException: {}", ex.getSQLException().getMessage());
        return createErrorResponse(ErrorType.INVALID_REQUEST.getType(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.warn("UsernameNotFoundException: {}", ex.getMessage());
        return createErrorResponse(ErrorType.INVALID_GRANT.getType(), ErrorDesc.INVALID_CREDENTIALS.getDesc(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.warn("EntityNotFoundException: {}", ex.getMessage());
        return createErrorResponse(ErrorType.NOT_FOUND.getType(), ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        log.warn("BadCredentialsException: {}", ex.getMessage());
        return createErrorResponse(ErrorType.INVALID_GRANT.getType(), ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InsufficientAuthorityException.class)
    protected ResponseEntity<ErrorResponse> handleInsufficientAuthorityException(InsufficientAuthorityException ex) {
        log.warn("InsufficientAuthorityException: {}", ex.getMessage());
        return createErrorResponse(ErrorType.INVALID_GRANT.getType(), ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UniquenessException.class)
    protected ResponseEntity<ErrorResponse> handleUniquenessException(UniquenessException ex) {
        log.warn("UniquenessException: {}", ex.getMessage());
        return createErrorResponse(ErrorType.INVALID_REQUEST.getType(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleNameTakenException.class)
    protected ResponseEntity<ErrorResponse> handleRoleNameTakenException(RoleNameTakenException ex) {
        log.warn("RoleNameTakenException: {}", ex.getMessage());
        return createErrorResponse(ErrorType.INVALID_REQUEST.getType(), ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(String type, String desc, HttpStatus status) {
        return new ResponseEntity<>(createError(type, desc, ErrorDesc.getCodeFormDesc(desc), List.of()), status);
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(String type, String desc, List<String> errors, HttpStatus status) {
        return new ResponseEntity<>(createError(type, desc, ErrorDesc.getCodeFormDesc(desc), errors), status);
    }

    private ErrorResponse createError(String type, String desc, String code, List<String> errorMessages) {
        return ErrorResponse.builder()
                .error(type)
                .errorDescription(desc)
                .code(code)
                .details(errorMessages)
                .build();
    }
}
