package com.desafio.service;

import com.desafio.model.dto.EstatisticaDto;
import com.desafio.model.entity.Transacao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EstatisticaServiceTest {

    @InjectMocks
    EstatisticaService estatisticaService;
    @Mock
    TransacaoService transacaoService;

    /**
     * Objetivo do teste: simular o caminho feliz do calculo de estatistica,
     * validando os valores de total, soma, média, máximo e minimo do resultado.
     *
     * Resultado esperado: O objeto estatisticaDto com os valores corretos.
     */
    @Test
    public void testEstatisticaHappyWay(){
        //Etapa de Mockagem
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.of("-03:00"));
        List<Transacao> transacaoListLastMinute = new ArrayList<>(Arrays.asList(
                new Transacao(40.0, now.minusSeconds(10)),
                new Transacao(45.0, now.minusSeconds(20)),
                new Transacao(35.0, now.minusSeconds(30)),
                new Transacao(35.0, now.minusSeconds(45)),
                new Transacao(15.0, now.minusSeconds(50)),
                new Transacao(30.0, now.minusSeconds(59))
        ));
        when(transacaoService.getTransacaoLastSeconds(now)).thenReturn(transacaoListLastMinute);
        //Etapa de Ação
        EstatisticaDto estatisticaDto = estatisticaService.getEstatistica(now);
        //Etapa de Assertividade
        verify(transacaoService, times(1)).getTransacaoLastSeconds(now);
        Assert.assertEquals(6, estatisticaDto.getCount());
        Assert.assertEquals(200.0, estatisticaDto.getSum(), 0.000001);
        Assert.assertEquals(33.333333, estatisticaDto.getAvg(), 0.000001);
        Assert.assertEquals(15.0, estatisticaDto.getMin(), 0.000001);
        Assert.assertEquals(45.0, estatisticaDto.getMax(), 0.000001);
    }

    /**
     * Objetivo do teste: simular o calculo de estatistica, quando apenas uma transação foi retornada,
     * o teste valida os valores de total, soma, média, máximo e minimo do resultado.
     *
     * Resultado esperado: O objeto estatisticaDto com os valores corretos.
     */
    @Test
    public void testEstatisticaComUmaTransacao(){
        //Etapa de Mockagem
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.of("-03:00"));
        List<Transacao> transacaoListLastMinute = new ArrayList<>(Arrays.asList(
                new Transacao(40.0, now.minusSeconds(10))
        ));
        when(transacaoService.getTransacaoLastSeconds(now)).thenReturn(transacaoListLastMinute);
        //Etapa de Ação
        EstatisticaDto estatisticaDto = estatisticaService.getEstatistica(now);
        //Etapa de Assertividade
        verify(transacaoService, times(1)).getTransacaoLastSeconds(now);
        Assert.assertEquals(1, estatisticaDto.getCount());
        Assert.assertEquals(40.0, estatisticaDto.getSum(), 0.000001);
        Assert.assertEquals(40.0, estatisticaDto.getAvg(), 0.000001);
        Assert.assertEquals(40.0, estatisticaDto.getMin(), 0.000001);
        Assert.assertEquals(40.0, estatisticaDto.getMax(), 0.000001);
    }

    /**
     * Objetivo do teste: simular o calculo de estatistica, quando nenhuma transação foi retornada,
     * o teste valida os valores de total, soma, média, máximo e minimo do resultado.
     *
     * Resultado esperado: O objeto estatisticaDto com os valores iguais a 0, conforme esperado.
     */
    @Test
    public void testEstatisticaSemTransacao(){
        //Etapa de Mockagem
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.of("-03:00"));
        List<Transacao> transacaoListLastMinute = new ArrayList<>();
        when(transacaoService.getTransacaoLastSeconds(now)).thenReturn(transacaoListLastMinute);
        //Etapa de Ação
        EstatisticaDto estatisticaDto = estatisticaService.getEstatistica(now);
        //Etapa de Assertividade
        verify(transacaoService, times(1)).getTransacaoLastSeconds(now);
        Assert.assertEquals(0, estatisticaDto.getCount());
        Assert.assertEquals(0, estatisticaDto.getSum(), 0.000001);
        Assert.assertEquals(0, estatisticaDto.getAvg(), 0.000001);
        Assert.assertEquals(0, estatisticaDto.getMin(), 0.000001);
        Assert.assertEquals(0, estatisticaDto.getMax(), 0.000001);
    }

}
