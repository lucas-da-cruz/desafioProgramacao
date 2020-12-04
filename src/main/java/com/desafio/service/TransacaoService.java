package com.desafio.service;

import com.desafio.model.entity.Transacao;
import com.desafio.model.exception.TransacaoException;
import com.desafio.repository.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    private static Logger Logger = LoggerFactory.getLogger(TransacaoService.class);

    @Value("${segundos.estatistica}")
    private int segundos;

    @Autowired
    TransacaoRepository repository;

    public List<Transacao> getTransacaoLastSeconds(OffsetDateTime now){
        List<Transacao> transacaoList = repository.getAllTransacao();

        List<Transacao> transacaoLastMin = transacaoList
                .stream()
                .filter(t -> t.getDataHora().isAfter(now.minusSeconds(segundos)))
                .collect(Collectors.toList());

        return transacaoLastMin;
    }

    public void saveTransacao(Transacao transacao){
        if(isFutureDate(transacao.getDataHora())){
            Logger.error("Tentativa de inserção de transação com data futura");
            throw new TransacaoException("A transação NÃO DEVE acontecer no futuro");
        }
        if(transacao.getValor() < 0){
            Logger.error("Tentativa de inserção de transação com valor negativo");
            throw new TransacaoException("A transação DEVE ter valor igual ou maior que 0 (zero)");
        }
        repository.save(transacao);
    }

    private boolean isFutureDate(OffsetDateTime dataHora){
        if(dataHora.isAfter(OffsetDateTime.now(ZoneOffset.of("-03:00")))){
            return true;
        }
            return false;
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
