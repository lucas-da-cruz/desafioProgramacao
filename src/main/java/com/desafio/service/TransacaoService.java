package com.desafio.service;

import com.desafio.model.entity.Transacao;
import com.desafio.model.exception.TransacaoException;
import com.desafio.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository repository;

    public List<Transacao> getTransacaoLastMin(OffsetDateTime now){
        List<Transacao> transacaoList = repository.getAllTransacao();

        List<Transacao> transacaoLastMin = transacaoList
                .stream()
                .filter(t -> t.getDataHora().isAfter(now.minusSeconds(60)))
                .collect(Collectors.toList());

        return transacaoLastMin;
    }

    public void saveTransacao(Transacao transacao){
        if(transacao.getDataHora().isAfter(OffsetDateTime.now(ZoneOffset.of("-03:00")))){
            throw new TransacaoException("A transação NÃO DEVE acontecer no futuro");
        }
        if(transacao.getValor() < 0){
            throw new TransacaoException("A transação DEVE ter valor igual ou maior que 0 (zero)");
        }
        repository.save(transacao);
    }

    private boolean isAfter(OffsetDateTime dataHora){
        ZoneOffset zoneOffSet = ZoneOffset.of("-03:00");
        OffsetDateTime offsetDateTime = OffsetDateTime.now(zoneOffSet);
        if(dataHora.isAfter(OffsetDateTime.now(ZoneOffset.of("-03:00")))){
            return true;
        }
            return false;
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
