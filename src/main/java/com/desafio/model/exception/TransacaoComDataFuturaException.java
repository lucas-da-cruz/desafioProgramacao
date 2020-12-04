package com.desafio.model.exception;

public class TransacaoComDataFuturaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TransacaoComDataFuturaException(String msg){
        super(msg);
    }
}