package com.desafio.controller;

import com.desafio.model.entity.Transacao;
import com.desafio.service.TransacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private static Logger Logger = LoggerFactory.getLogger(TransacaoController.class);

    @Autowired
    TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid Transacao transacao){
        Logger.info("Transação recebida");
        transacaoService.saveTransacao(transacao);
        Logger.info("Nova transação gravada");
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping
    public ResponseEntity deleteAll(){
        Logger.info("Solicitação de exclusão de transação");
        transacaoService.deleteAll();
        Logger.info("Todas as transação excluídas");
        return ResponseEntity.ok().build();
    }

}
