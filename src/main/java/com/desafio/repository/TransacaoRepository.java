package com.desafio.repository;

import com.desafio.model.entity.Transacao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {

    List<Transacao> transacaoList = new ArrayList<>();

    public List<Transacao> getAllTransacao() {
        return transacaoList;
    }

    public void save(Transacao transacao){
        transacaoList.add(transacao);
    }

    public void deleteAll(){
        transacaoList.clear();
    }

}
