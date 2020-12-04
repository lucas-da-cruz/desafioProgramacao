package com.desafio.service;

import com.desafio.model.dto.TransacaoDto;
import com.desafio.model.exception.TransacaoComDataFuturaException;
import com.desafio.model.exception.TransacaoComValorNegativoException;
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

    public List<TransacaoDto> getTransacaoLastSeconds(OffsetDateTime now){
        List<TransacaoDto> transacaoList = repository.getAllTransacao();

        List<TransacaoDto> transacaoLastMin = transacaoList
                .stream()
                .filter(t -> t.getDataHora().isAfter(now.minusSeconds(segundos)))
                .collect(Collectors.toList());

        return transacaoLastMin;
    }

    public void saveTransacao(TransacaoDto transacao){
        if(isFutureDate(transacao.getDataHora())){
            Logger.warn("Tentativa de inserção de transação com data futura");
            throw new TransacaoComDataFuturaException("A transação NÃO DEVE acontecer no futuro");
        }
        if(transacao.getValor() < 0){
            Logger.warn("Tentativa de inserção de transação com data futura");
            throw new TransacaoComValorNegativoException("A transação NÃO DEVE ter valor negativo");
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
