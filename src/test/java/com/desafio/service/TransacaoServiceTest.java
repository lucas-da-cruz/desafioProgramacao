package com.desafio.service;

import com.desafio.model.dto.TransacaoDto;
import com.desafio.model.exception.TransacaoComDataFuturaException;
import com.desafio.model.exception.TransacaoComValorNegativoException;
import com.desafio.repository.TransacaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;
    @Mock
    TransacaoRepository transacaoRepository;

    /**
     * Objetivo do teste: simular o caminho feliz da inserção de uma transação,
     * validando que o método de salvar foi invocado uma vez.
     *
     * Resultado esperado: que passe na validação e chame o método salvar com o argumento esperado.
     */
    @Test
    public void testHappyWayTransacao(){
        //Etapa de Mockagem
        TransacaoDto transacaoMock = new TransacaoDto(25.0, OffsetDateTime.now(ZoneOffset.of("-03:00")));
        doNothing().when(transacaoRepository).save(transacaoMock);
        //Etapa de Ação
        transacaoService.saveTransacao(transacaoMock);
        //Etapa de Assertividade
        verify(transacaoRepository, times(1)).save(transacaoMock);
    }

    /**
     * Objetivo do teste: simular o caminho feliz da inserção de uma transação cujo valor é igual a 0,
     * validando que o método de salvar foi invocado uma vez.
     *
     * Resultado esperado: que passe na validação e chame o método salvar com o argumento esperado.
     */
    @Test
    public void testHappyWayTransacaoWithValorIgualA0(){
        //Etapa de Mockagem
        TransacaoDto transacaoMock = new TransacaoDto(0.0, OffsetDateTime.now(ZoneOffset.of("-03:00")));
        doNothing().when(transacaoRepository).save(transacaoMock);
        //Etapa de Ação
        transacaoService.saveTransacao(transacaoMock);
        //Etapa de Assertividade
        verify(transacaoRepository, times(1)).save(transacaoMock);
    }

    /**
     * Objetivo do teste: simular a tratativa quando receber uma transação com o atributo valor negativo,
     * validando que o método acione uma exceção.
     *
     * Resultado esperado: que o método saveTransacao da classe TransacaoService invoque a exception do tipo TransacaoException.
     */
    @Test(expected = TransacaoComValorNegativoException.class)
    public void testBadWayTransacaoWithValorNegativo(){
        //Etapa de Mockagem
        TransacaoDto transacaoMock = new TransacaoDto(-1.0, OffsetDateTime.now(ZoneOffset.of("-03:00")));
        //Etapa de Ação
        transacaoService.saveTransacao(transacaoMock);
    }

    /**
     * Objetivo do teste: simular a tratativa quando receber uma transação com o atributo dataHora com um segundo futuro,
     * validando que o método acione uma exceção.
     *
     * Resultado esperado: que o método saveTransacao da classe TransacaoService invoque a exception do tipo TransacaoException.
     */
    @Test(expected = TransacaoComDataFuturaException.class)
    public void testBadWayTransacaoWithPlusOneSecond(){
        //Etapa de Mockagem
        OffsetDateTime dateFuture = OffsetDateTime.now(ZoneOffset.of("-03:00")).plusSeconds(1);
        TransacaoDto transacaoMock = new TransacaoDto(25.0, dateFuture);
        //Etapa de Ação
        transacaoService.saveTransacao(transacaoMock);
    }

    /**
     * Objetivo do teste: simular a tratativa quando receber uma transação com o atributo dataHora com um minuto futuro,
     * validando que o método acione uma exceção.
     *
     * Resultado esperado: que o método saveTransacao da classe TransacaoService invoque a exception do tipo TransacaoException.
     */
    @Test(expected = TransacaoComDataFuturaException.class)
    public void testBadWayTransacaoWithPlusOneMinute(){
        //Etapa de Mockagem
        OffsetDateTime dateFuture = OffsetDateTime.now(ZoneOffset.of("-03:00")).plusMinutes(1);
        TransacaoDto transacaoMock = new TransacaoDto(25.0, dateFuture);
        //Etapa de Ação
        transacaoService.saveTransacao(transacaoMock);
    }

    /**
     * Objetivo do teste: simular a tratativa quando receber uma transação com o atributo dataHora com uma hora futura,
     * validando que o método acione uma exceção.
     *
     * Resultado esperado: que o método saveTransacao da classe TransacaoService invoque a exception do tipo TransacaoException.
     */
    @Test(expected = TransacaoComDataFuturaException.class)
    public void testBadWayTransacaoWithPlusOneHour(){
        //Etapa de Mockagem
        OffsetDateTime dateFuture = OffsetDateTime.now(ZoneOffset.of("-03:00")).plusHours(1);
        TransacaoDto transacaoMock = new TransacaoDto(25.0, dateFuture);
        //Etapa de Ação
        transacaoService.saveTransacao(transacaoMock);
    }

}
