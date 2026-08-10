package dev.luizconde.controlefinanceiroapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.exc.InvalidFormatException;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                      HttpServletRequest request){
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problem.setTitle("Invalid Request Body");
        problem.setDetail("The request body contains invalid or malformed data.");
        problem.setProperty("path", request.getRequestURI());

        if(ex.getCause() instanceof InvalidFormatException invalidFormatException &&
                invalidFormatException.getTargetType().isEnum()){
            String fieldName = invalidFormatException.getPath()
                    .stream()
                    .map(JacksonException.Reference::getPropertyName)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse("field");

            Object[] acceptedValues = invalidFormatException
                    .getTargetType()
                    .getEnumConstants();

            problem.setDetail(
                    "Invalid value for field " + fieldName + ". Accepted Values: " + Arrays.toString(acceptedValues)
            );
        }

        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problem.setTitle("Validation error");
        problem.setDetail("Some fields are invalid.");
        problem.setProperty("path", request.getRequestURI());

        Map<String, String> fields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (first, second) -> first
                ));

        problem.setProperty("fields", fields);

        return problem;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(
            UserNotFoundException ex,
            HttpServletRequest request
    ){
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problem.setTitle("User not found");
        problem.setDetail(ex.getMessage());
        problem.setProperty("path", request.getRequestURI());

        return problem;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex,
                                                HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Resource not found");
        problem.setDetail(ex.getMessage());
        problem.setProperty("path", request.getRequestURI());
        return problem;
    }

    @ExceptionHandler(ConflictException.class)
    public ProblemDetail handleConflict(ConflictException ex,
                                        HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setTitle("Conflict");
        problem.setDetail(ex.getMessage());
        problem.setProperty("path", request.getRequestURI());
        return problem;
    }
}
