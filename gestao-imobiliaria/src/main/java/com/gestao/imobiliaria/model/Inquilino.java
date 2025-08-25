package com.gestao.imobiliaria.model;

public class Inquilino {
    private int idInquilino;
    private String cpfInquilino;
    private String nomeInquilino;
    private String emailInquilino;

    // Construtor padrão
    public Inquilino() {}

    // Construtor com parâmetros
    public Inquilino(String cpfInquilino, String nomeInquilino, String emailInquilino) {
        this.cpfInquilino = cpfInquilino;
        this.nomeInquilino = nomeInquilino;
        this.emailInquilino = emailInquilino;
    }

    // Getters e Setters
    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public String getCpfInquilino() {
        return cpfInquilino;
    }

    public void setCpfInquilino(String cpfInquilino) {
        this.cpfInquilino = cpfInquilino;
    }

    public String getNomeInquilino() {
        return nomeInquilino;
    }

    public void setNomeInquilino(String nomeInquilino) {
        this.nomeInquilino = nomeInquilino;
    }

    public String getEmailInquilino() {
        return emailInquilino;
    }

    public void setEmailInquilino(String emailInquilino) {
        this.emailInquilino = emailInquilino;
    }

    @Override
    public String toString() {
        return "Inquilino{" +
                "idInquilino=" + idInquilino +
                ", cpfInquilino='" + cpfInquilino + '\'' +
                ", nomeInquilino='" + nomeInquilino + '\'' +
                ", emailInquilino='" + emailInquilino + '\'' +
                '}';
    }
}

