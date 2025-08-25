package com.gestao.imobiliaria.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Locacao {
    private int idLocacao;
    private int idInquilino;
    private int idPropriedade;
    private BigDecimal valor;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean ativo;

    // Construtor padrão
    public Locacao() {}

    // Construtor com parâmetros
    public Locacao(int idInquilino, int idPropriedade, BigDecimal valor, LocalDate dataInicio, LocalDate dataFim) {
        this.idInquilino = idInquilino;
        this.idPropriedade = idPropriedade;
        this.valor = valor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.ativo = true;
    }

    // Getters e Setters
    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public int getIdPropriedade() {
        return idPropriedade;
    }

    public void setIdPropriedade(int idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Locacao{" +
                "idLocacao=" + idLocacao +
                ", idInquilino=" + idInquilino +
                ", idPropriedade=" + idPropriedade +
                ", valor=" + valor +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", ativo=" + ativo +
                '}';
    }
}

