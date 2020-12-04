package com.desafio.service;

import com.desafio.model.dto.EstatisticaDto;
import com.desafio.model.dto.TransacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstatisticaService {

    @Autowired
    TransacaoService transacaoService;

    public EstatisticaDto getEstatistica(OffsetDateTime now) {
        List<TransacaoDto> transacaoLastMin = transacaoService.getTransacaoLastSeconds(now);

        if(transacaoLastMin.isEmpty()) {
            return new EstatisticaDto();
        }

        List<Double> listAllValor = getValoresDouble(transacaoLastMin);

        DoubleSummaryStatistics estatistica = getEstatistica(listAllValor);

        return new EstatisticaDto(estatistica.getCount(), estatistica.getSum(), estatistica.getAverage(), estatistica.getMin(), estatistica.getMax());
    }

    private List<Double> getValoresDouble(List<TransacaoDto> transacaoList){
        return transacaoList
                .stream()
                .mapToDouble(t -> t.getValor())
                .boxed()
                .collect(Collectors.toList());
    }

    private DoubleSummaryStatistics getEstatistica(List<Double> doubleList){
        DoubleSummaryStatistics estatistica = new DoubleSummaryStatistics();
        Iterator<Double> iterator = doubleList.listIterator();
        while (iterator.hasNext()) {
            estatistica.accept(iterator.next());
        }
        return estatistica;
    }

}
