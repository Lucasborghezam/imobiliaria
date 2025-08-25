package com.gestao.imobiliaria.dao;

import com.gestao.imobiliaria.model.Locacao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocacaoDAO {

    public boolean cadastrarLocacao(Locacao locacao) {
        String sql = "INSERT INTO LOCACAO (id_inquilino, id_propriedade, valor, data_inicio, data_fim, ativo) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, locacao.getIdInquilino());
            stmt.setInt(2, locacao.getIdPropriedade());
            stmt.setBigDecimal(3, locacao.getValor());
            stmt.setDate(4, Date.valueOf(locacao.getDataInicio()));
            stmt.setDate(5, Date.valueOf(locacao.getDataFim()));
            stmt.setBoolean(6, locacao.isAtivo());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Locação cadastrada com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar locação: " + e.getMessage());
        }
        return false;
    }

    public List<Object[]> listarContratosAtivos() {
        String sql = "SELECT l.id_locacao, i.nome_inquilino, p.endereco, p.tipo, l.valor, l.data_inicio, l.data_fim " +
                     "FROM LOCACAO l " +
                     "JOIN INQUILINO i ON l.id_inquilino = i.id_inquilino " +
                     "JOIN PROPRIEDADE p ON l.id_propriedade = p.id_propriedade " +
                     "WHERE l.ativo = TRUE " +
                     "ORDER BY l.data_inicio DESC";
        
        List<Object[]> contratos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Object[] contrato = new Object[7];
                contrato[0] = rs.getInt("id_locacao");
                contrato[1] = rs.getString("nome_inquilino");
                contrato[2] = rs.getString("endereco");
                contrato[3] = rs.getString("tipo");
                contrato[4] = rs.getBigDecimal("valor");
                contrato[5] = rs.getDate("data_inicio").toLocalDate();
                contrato[6] = rs.getDate("data_fim").toLocalDate();
                
                contratos.add(contrato);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar contratos ativos: " + e.getMessage());
        }
        
        return contratos;
    }

    public List<Object[]> listarContratosExpirandoProximos30Dias() {
        LocalDate dataLimite = LocalDate.now().plusDays(30);
        LocalDate dataAtual = LocalDate.now();
        
        String sql = "SELECT l.id_locacao, i.nome_inquilino, p.endereco, p.tipo, l.valor, l.data_inicio, l.data_fim " +
                     "FROM LOCACAO l " +
                     "JOIN INQUILINO i ON l.id_inquilino = i.id_inquilino " +
                     "JOIN PROPRIEDADE p ON l.id_propriedade = p.id_propriedade " +
                     "WHERE l.ativo = TRUE AND l.data_fim <= ? AND l.data_fim >= ? " +
                     "ORDER BY l.data_fim ASC";
        
        List<Object[]> contratos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(dataLimite));
            stmt.setDate(2, Date.valueOf(dataAtual));
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Object[] contrato = new Object[7];
                contrato[0] = rs.getInt("id_locacao");
                contrato[1] = rs.getString("nome_inquilino");
                contrato[2] = rs.getString("endereco");
                contrato[3] = rs.getString("tipo");
                contrato[4] = rs.getBigDecimal("valor");
                contrato[5] = rs.getDate("data_inicio").toLocalDate();
                contrato[6] = rs.getDate("data_fim").toLocalDate();
                
                contratos.add(contrato);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar contratos expirando: " + e.getMessage());
        }
        
        return contratos;
    }

    public boolean verificarAtivacaoContratos() {
        LocalDate dataAtual = LocalDate.now();
        String sql = "UPDATE LOCACAO SET ativo = FALSE WHERE data_fim < ? AND ativo = TRUE";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(dataAtual));
            
            int rowsAffected = stmt.executeUpdate();
            System.out.println("Status dos contratos atualizado. Contratos desativados: " + rowsAffected);
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status dos contratos: " + e.getMessage());
        }
        return false;
    }

    public List<Object[]> listarTodasLocacoes() {
        String sql = "SELECT l.id_locacao, i.nome_inquilino, p.endereco, p.tipo, l.valor, l.data_inicio, l.data_fim, l.ativo " +
                     "FROM LOCACAO l " +
                     "JOIN INQUILINO i ON l.id_inquilino = i.id_inquilino " +
                     "JOIN PROPRIEDADE p ON l.id_propriedade = p.id_propriedade " +
                     "ORDER BY l.data_inicio DESC";
        
        List<Object[]> locacoes = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Object[] locacao = new Object[8];
                locacao[0] = rs.getInt("id_locacao");
                locacao[1] = rs.getString("nome_inquilino");
                locacao[2] = rs.getString("endereco");
                locacao[3] = rs.getString("tipo");
                locacao[4] = rs.getBigDecimal("valor");
                locacao[5] = rs.getDate("data_inicio").toLocalDate();
                locacao[6] = rs.getDate("data_fim").toLocalDate();
                locacao[7] = rs.getBoolean("ativo");
                
                locacoes.add(locacao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar todas as locações: " + e.getMessage());
        }
        
        return locacoes;
    }
}

