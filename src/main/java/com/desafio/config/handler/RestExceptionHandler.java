package com.desafio.config.handler;

import com.desafio.model.exception.TransacaoComDataFuturaException;
import com.desafio.model.exception.TransacaoComValorNegativoException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    private static org.slf4j.Logger Logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(TransacaoComDataFuturaException.class)
    public void handleResourceComDataFutura(TransacaoComDataFuturaException exception){
        Logger.warn("Exceção gerada: " + exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(TransacaoComValorNegativoException.class)
    public void handleResourceComValorNegativoException(TransacaoComValorNegativoException exception){
        Logger.warn("Exceção gerada: " + exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void methodArgumentNotValidException(MethodArgumentNotValidException exception){
        Logger.warn("Exceção gerada: " + exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleResourceSemBody(HttpMessageNotReadableException exception){
        Logger.warn("Exceção gerada: " + exception.getMessage());
    }


}
