package com.desafio.model.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public class TransacaoDto {

    @NotNull
    @DecimalMin(value = "0.0")
    private Double valor;
    @NotNull
    private OffsetDateTime dataHora;

    public TransacaoDto() {
    }

    public TransacaoDto(Double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
