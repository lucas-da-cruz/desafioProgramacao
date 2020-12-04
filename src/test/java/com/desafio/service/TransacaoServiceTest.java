package com.desafio.service;

import com.desafio.model.entity.Transacao;
import com.desafio.model.exception.TransacaoException;
import com.desafio.repository.TransacaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;
    @Mock
    TransacaoRepository transacaoRepository;

    @Test
    public void testHappyWayTransacao(){
        //Etapa de Mockagem
        Transacao transacaoMock = new Transacao(25.0, OffsetDateTime.now(ZoneOffset.of("-03:00")));
        doNothing().when(transacaoRepository).save(transacaoMock);
        //Etapa de Ação
        transacaoService.saveTransacao(transacaoMock);
        //Etapa de Assertividade
        verify(transacaoRepository, times(1)).save(transacaoMock);
    }

    @Test
    public void testHappyWayTransacaoWithValorIgualA0(){
        //Etapa de Mockagem
        Transacao transacaoMock = new Transacao(0, OffsetDateTime.now(ZoneOffset.of("-03:00")));
        doNothing().when(transacaoRepository).save(transacaoMock);
        //Etapa de Ação
        transacaoService.saveTransacao(transacaoMock);
        //Etapa de Assertividade
        verify(transacaoRepository, times(1)).save(transacaoMock);
    }

    @Test(expected = TransacaoException.class)
    public void testBadWayTransacaoWithPlusOneSecond(){
        //Etapa de Mockagem
        OffsetDateTime dateFuture = OffsetDateTime.now(ZoneOffset.of("-03:00")).plusSeconds(1);
        Transacao transacaoMock = new Transacao(25.0, dateFuture);
        //Etapa de Ação
        transacaoService.saveTransacao(transacaoMock);
    }

    @Test(expected = TransacaoException.class)
    public void testBadWayTransacaoWithPlusOneMinute(){
        //Etapa de Mockagem
        OffsetDateTime dateFuture = OffsetDateTime.now(ZoneOffset.of("-03:00")).plusMinutes(1);
        Transacao transacaoMock = new Transacao(25.0, dateFuture);
        //Etapa de Ação
        transacaoService.saveTransacao(transacaoMock);
    }

    @Test(expected = TransacaoException.class)
    public void testBadWayTransacaoWithPlusOneHour(){
        //Etapa de Mockagem
        OffsetDateTime dateFuture = OffsetDateTime.now(ZoneOffset.of("-03:00")).plusHours(1);
        Transacao transacaoMock = new Transacao(25.0, dateFuture);
        //Etapa de Ação
        transacaoService.saveTransacao(transacaoMock);
    }

    @Test
    public void testBadWayTransacaoWithValorNegativo(){
        //Etapa de Mockagem
        Transacao transacaoMock = new Transacao(-1.0, OffsetDateTime.now(ZoneOffset.of("-03:00")));
        //Etapa de Ação e assertividade
        Exception exception = assertThrows(TransacaoException.class, () -> transacaoService.saveTransacao(transacaoMock));
        assertEquals("A transação DEVE ter valor igual ou maior que 0 (zero)", exception.getMessage());
        verify(transacaoRepository, never()).save(transacaoMock);
    }

}
