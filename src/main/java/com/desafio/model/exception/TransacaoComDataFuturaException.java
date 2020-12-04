package com.desafio.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class TransacaoComDataFuturaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TransacaoComDataFuturaException(String msg){
        super(msg);
    }
}