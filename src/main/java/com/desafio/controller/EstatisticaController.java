package com.desafio.controller;

import com.desafio.model.dto.EstatisticaDto;
import com.desafio.service.EstatisticaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private static Logger Logger = LoggerFactory.getLogger(EstatisticaController.class);

    @Autowired
    EstatisticaService estatisticaService;

    @GetMapping
    public ResponseEntity getEstatistica(){
        long inicio = System.currentTimeMillis();
        Logger.info("Solicitação de cálculo de estatistica recebida");
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.of("-03:00"));
        EstatisticaDto estatisticaDto = estatisticaService.getEstatistica(now);
        long fim = System.currentTimeMillis() - inicio;
        Logger.info("O cálculo de estatistica foi feito e levou: " + fim + " milisegundos para ser calculado");
        return ResponseEntity.ok(estatisticaDto);
    }

}
