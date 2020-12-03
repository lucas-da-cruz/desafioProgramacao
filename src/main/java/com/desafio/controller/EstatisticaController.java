package com.desafio.controller;

import com.desafio.model.dto.EstatisticaDto;
import com.desafio.service.EstatisticaService;
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

    @Autowired
    EstatisticaService estatisticaService;

    @GetMapping
    public ResponseEntity getEstatistica(){
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.of("-03:00"));
        EstatisticaDto estatisticaDto = estatisticaService.getEstatistica(now);
        return ResponseEntity.ok(estatisticaDto);
    }


}
