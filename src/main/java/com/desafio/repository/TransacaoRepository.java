package com.desafio.repository;

import com.desafio.model.dto.TransacaoDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {

    List<TransacaoDto> transacaoList = new ArrayList<>();

    public List<TransacaoDto> getAllTransacao() {
        return transacaoList;
    }

    public void save(TransacaoDto transacao){
        transacaoList.add(transacao);
    }

    public void deleteAll(){
        transacaoList.clear();
    }

}
