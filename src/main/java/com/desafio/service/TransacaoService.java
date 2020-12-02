package com.desafio.service;

import com.desafio.model.entity.Transacao;
import com.desafio.model.exception.TransacaoException;
import com.desafio.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    public void saveTransacao(Transacao transacao){
            if(isAfter(transacao.getDataHora())){
                throw new TransacaoException("A transação NÃO DEVE acontecer no futuro");
            }
            if(transacao.getValor() < 0){
                throw new TransacaoException("A transação DEVE ter valor igual ou maior que 0 (zero)");
            }
            transacaoRepository.save(transacao);
    }

    private boolean isAfter(OffsetDateTime dataHora){
        if(dataHora.isAfter(OffsetDateTime.now(Clock.systemDefaultZone()))){
            return true;
        }
            return false;
    }
}
