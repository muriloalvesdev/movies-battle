package br.com.moviesbattle.exception.handler;

import br.com.moviesbattle.dto.data.ApiException;
import br.com.moviesbattle.exception.KeyInvalidException;
import br.com.moviesbattle.exception.TitleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity<ApiException> handleTitleNotFoundExceptionn(final TitleNotFoundException exception) {
        return ResponseEntity.status(NOT_FOUND)
                .body(createResponse(exception.getMessage(), NOT_FOUND.value()));
    }

    @ExceptionHandler(KeyInvalidException.class)
    public ResponseEntity<ApiException> handleTitleNotFoundExceptionn(final KeyInvalidException exception) {
        return ResponseEntity.status(NOT_FOUND)
                .body(createResponse(exception.getMessage(), NOT_FOUND.value()));
    }

    private ApiException createResponse(String message, int status) {
        return ApiException.builder()
                .message(message)
                .status(status)
                .build();
    }
}
