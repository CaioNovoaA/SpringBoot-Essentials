package academy.devdojo.springboot2essentials.handler;

import academy.devdojo.springboot2essentials.exception.BadRequestException;
import academy.devdojo.springboot2essentials.exception.BadRequestExceptionDetails;
import academy.devdojo.springboot2essentials.exception.ValidationExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> HandlerBadRequestException(BadRequestException bre) {
        return new ResponseEntity<>(BadRequestExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request Exception, Check the Documentation")
                .details(bre.getMessage())
                .developerMessage(bre.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> HandlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        List<FieldError> fildErros = exception.getBindingResult().getFieldErrors();
        String fields = fildErros.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessage = fildErros.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return new ResponseEntity<>(
                ValidationExceptionDetails.builder().
                        timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Invalid fields")
                        .details("read about argument failed")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }
}