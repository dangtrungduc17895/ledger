package com.pet.ledger.exceptionhandler;

import com.pet.ledger.constant.CodeResponse;
import com.pet.ledger.constant.MessageConstant;
import com.pet.ledger.exceptionhandler.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(MessageConstant.MEDIA_TYPE_NOT_SP);
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        ErrorFormat errorFormat = new ErrorFormat(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                builder.substring(0, builder.length() - 2), ex);
        return buildResponseEntity(errorFormat);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + MessageConstant.PARAM_MISSING;
        return buildResponseEntity(new ErrorFormat(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String error = MessageConstant.MALFORMED_REQUEST;
        return buildResponseEntity(new ErrorFormat(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = MessageConstant.ERROR_WRITING_JSON;
        return buildResponseEntity(new ErrorFormat(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorFormat errorFormat = new ErrorFormat(HttpStatus.BAD_REQUEST);
        errorFormat.setMessage(MessageConstant.VALIDATION_ERROR);
        errorFormat.addValidationErrors(ex.getBindingResult().getFieldErrors());
        errorFormat.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(errorFormat);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(javax.validation.ConstraintViolationException ex) {
        ErrorFormat errorFormat = new ErrorFormat(HttpStatus.BAD_REQUEST);
        errorFormat.setMessage(MessageConstant.VALIDATION_ERROR);
        errorFormat.addValidationErrors(ex.getConstraintViolations());
        return buildResponseEntity(errorFormat);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorFormat errorFormat = new ErrorFormat(HttpStatus.NOT_FOUND);
        errorFormat.setMessage(ex.getMessage());
        return buildResponseEntity(errorFormat);
    }

    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
        return buildResponseEntity(new ErrorFormat(HttpStatus.NOT_FOUND, ex));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegityViolation(DataIntegrityViolationException ex, WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(new ErrorFormat(HttpStatus.CONFLICT, MessageConstant.DATABASE_ERROR, ex.getCause()));
        }

        return buildResponseEntity(new ErrorFormat(HttpStatus.INTERNAL_SERVER_ERROR, ex));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex
            , WebRequest request) {
        ErrorFormat errorFormat = new ErrorFormat(HttpStatus.BAD_REQUEST);
        errorFormat.setMessage(String.format(MessageConstant.PARAM_COULD_NOT_BE_CONVERT_TO_TYP
                , ex.getName(), ex.getValue(), Objects.requireNonNull(Objects.requireNonNull(ex.getRequiredType()).getSimpleName())));
        errorFormat.setDebugMessage(ex.getMessage());
        return buildResponseEntity(errorFormat);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorFormat errorFormat = new ErrorFormat(HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false), ex);
        return buildResponseEntity(errorFormat);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorFormat errorFormat) {
        ErrorResponse errorResponse = new ErrorResponse(CodeResponse.FAIL_CODE.getCode(), errorFormat, errorFormat.getDebugMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
