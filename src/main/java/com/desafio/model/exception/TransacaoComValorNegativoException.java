package com.desafio.model.exception;

public class TransacaoComValorNegativoException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public TransacaoComValorNegativoException(String msg){
        super(msg);
    }

}