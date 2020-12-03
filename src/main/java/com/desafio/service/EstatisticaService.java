package com.desafio.service;

import com.desafio.model.dto.EstatisticaDto;
import com.desafio.model.entity.Transacao;
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
        List<Transacao> transacaoLastMin = transacaoService.getTransacaoLastMin(now);

        if(transacaoLastMin.isEmpty()) {
            return new EstatisticaDto();
        }

        List<Double> listAllValor = transacaoLastMin
                                        .stream()
                                        .mapToDouble(t -> t.getValor())
                                        .boxed()
                                        .collect(Collectors.toList());

        DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();

        Iterator<Double> iterator = listAllValor.listIterator();

        while (iterator.hasNext()) {
            statistics.accept(iterator.next());
        }

        return new EstatisticaDto(statistics.getCount(), statistics.getSum(), statistics.getAverage(), statistics.getMin(), statistics.getMax());
    }

}
