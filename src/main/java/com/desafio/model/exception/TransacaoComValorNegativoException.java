package com.desafio.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class TransacaoComValorNegativoException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public TransacaoComValorNegativoException(String msg){
        super(msg);
    }

}