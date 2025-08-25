package com.gestao.imobiliaria.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Propriedade {
    private int idPropriedade;
    private String tipo;
    private String descricao;
    private String regiao;
    private String endereco;
    private BigDecimal areaM2;
    private LocalDateTime dataCriacao;

    // Construtor padrão
    public Propriedade() {}

    // Construtor com parâmetros
    public Propriedade(String tipo, String descricao, String regiao, String endereco, BigDecimal areaM2) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.regiao = regiao;
        this.endereco = endereco;
        this.areaM2 = areaM2;
        this.dataCriacao = LocalDateTime.now();
    }

    // Getters e Setters
    public int getIdPropriedade() {
        return idPropriedade;
    }

    public void setIdPropriedade(int idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(BigDecimal areaM2) {
        this.areaM2 = areaM2;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String toString() {
        return "Propriedade{" +
                "idPropriedade=" + idPropriedade +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", regiao='" + regiao + '\'' +
                ", endereco='" + endereco + '\'' +
                ", areaM2=" + areaM2 +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}

