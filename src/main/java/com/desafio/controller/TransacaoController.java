package com.desafio.controller;

import com.desafio.model.entity.Transacao;
import com.desafio.model.exception.TransacaoException;
import com.desafio.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid Transacao transacao){
        try{
            transacaoService.saveTransacao(transacao);
            return ResponseEntity.status(201).build();
        }
        catch(TransacaoException e){
            return ResponseEntity.status(422).build();
        }
    }

}
