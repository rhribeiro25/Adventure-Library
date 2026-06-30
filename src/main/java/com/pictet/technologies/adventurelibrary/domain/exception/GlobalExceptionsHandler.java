package com.pictet.technologies.adventurelibrary.domain.exception;

import com.pictet.technologies.adventurelibrary.infrastructure.shared.utils.MessageUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ProblemDetail> handleInternalServerError(
            InternalServerErrorException exception,
            HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problem.setTitle(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        problem.setDetail(resolveOrDefault(exception.getMessage(), "error.internal"));
        problem.setProperty("path", request.getServletPath());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(problem);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetail> handleBusinessException(
            BusinessException exception,
            HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail.forStatus(exception.getStatus());
        problem.setTitle(exception.getStatus().getReasonPhrase());
        problem.setDetail(resolveMessage(exception.getMessage()));
        problem.setProperty("path", request.getServletPath());

        return ResponseEntity
                .status(exception.getStatus())
                .body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationError(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(
                        error.getField(),
                        resolveMessage(error.getDefaultMessage())
                ));

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
        problem.setDetail(MessageUtils.getMessage("error.validation"));
        problem.setProperty("path", request.getServletPath());
        problem.setProperty("errors", errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(problem);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<ProblemDetail> handleOptimisticLockingFailure(
            OptimisticLockingFailureException exception,
            HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setTitle(MessageUtils.getMessage("error.optimistic.locking.title"));
        problem.setDetail(MessageUtils.getMessage("error.optimistic.locking"));
        problem.setProperty("path", request.getServletPath());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(problem);
    }

    private String resolveMessage(String messageOrKey) {
        if (messageOrKey == null || messageOrKey.isBlank()) {
            return messageOrKey;
        }

        try {
            return MessageUtils.getMessage(messageOrKey);
        } catch (Exception ignored) {
            return messageOrKey;
        }
    }

    private String resolveOrDefault(String messageOrKey, String defaultKey) {
        if (messageOrKey == null || messageOrKey.isBlank()) {
            return MessageUtils.getMessage(defaultKey);
        }

        return resolveMessage(messageOrKey);
    }
}