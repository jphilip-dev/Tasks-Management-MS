package com.jphilip.tm.task.exception;

import com.jphilip.tm.task.ErrorCode;
import com.jphilip.tm.task.dto.ExceptionResponseDTO;
import com.jphilip.tm.task.exception.custom.TaskException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(TaskException.class)
    public ResponseEntity<ExceptionResponseDTO> handleTaskExceptions(TaskException ex, WebRequest request){
        return createExceptionResponse(ex.getErrorCode(), ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleOtherExceptions(Exception ex, WebRequest request){

        log.error("Unhandled exception: {}", ex.getMessage(), ex);

        return createExceptionResponse(ErrorCode.ERROR_INTERNAL_SERVER_ERROR,null, request);
    }
    /*
     *
     *  Helper method/s
     *
     */

    private ResponseEntity<ExceptionResponseDTO> createExceptionResponse(ErrorCode errorCode, String message, WebRequest request){

        String responseMessage = (message == null || message.isBlank())
                ? errorCode.getHttpStatus().name()
                : errorCode.getHttpStatus().name() + ", " + message;

        var exceptionResponseDTO = new ExceptionResponseDTO(
                LocalDateTime.now(),
                errorCode.getHttpStatus().value(),
                errorCode.getHttpStatus().getReasonPhrase(),
                responseMessage,
                request.getDescription(false).replace("uri=/", "")
        );

        return new ResponseEntity<>(exceptionResponseDTO,errorCode.getHttpStatus());
    }

}
