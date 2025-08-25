package com.gestao.imobiliaria.dao;

import com.gestao.imobiliaria.model.Propriedade;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropriedadeDAO {

    public boolean cadastrarPropriedade(Propriedade propriedade) {
        String sql = "INSERT INTO PROPRIEDADE (tipo, descricao, regiao, endereco, area_m2) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, propriedade.getTipo());
            stmt.setString(2, propriedade.getDescricao());
            stmt.setString(3, propriedade.getRegiao());
            stmt.setString(4, propriedade.getEndereco());
            stmt.setBigDecimal(5, propriedade.getAreaM2());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Propriedade cadastrada com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar propriedade: " + e.getMessage());
        }
        return false;
    }

    public List<Propriedade> listarPropriedadesDisponiveis() {
        String sql = "SELECT p.id_propriedade, p.tipo, p.descricao, p.regiao, p.endereco, p.area_m2, p.data_criacao " +
                     "FROM PROPRIEDADE p " +
                     "WHERE p.id_propriedade NOT IN ( " +
                     "    SELECT l.id_propriedade " +
                     "    FROM LOCACAO l " +
                     "    WHERE l.ativo = TRUE " +
                     ") " +
                     "ORDER BY p.data_criacao DESC";
        
        List<Propriedade> propriedades = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Propriedade propriedade = new Propriedade();
                propriedade.setIdPropriedade(rs.getInt("id_propriedade"));
                propriedade.setTipo(rs.getString("tipo"));
                propriedade.setDescricao(rs.getString("descricao"));
                propriedade.setRegiao(rs.getString("regiao"));
                propriedade.setEndereco(rs.getString("endereco"));
                propriedade.setAreaM2(rs.getBigDecimal("area_m2"));
                propriedade.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                
                propriedades.add(propriedade);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar propriedades disponíveis: " + e.getMessage());
        }
        
        return propriedades;
    }

    public List<Propriedade> listarTodasPropriedades() {
        String sql = "SELECT id_propriedade, tipo, descricao, regiao, endereco, area_m2, data_criacao FROM PROPRIEDADE ORDER BY data_criacao DESC";
        
        List<Propriedade> propriedades = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Propriedade propriedade = new Propriedade();
                propriedade.setIdPropriedade(rs.getInt("id_propriedade"));
                propriedade.setTipo(rs.getString("tipo"));
                propriedade.setDescricao(rs.getString("descricao"));
                propriedade.setRegiao(rs.getString("regiao"));
                propriedade.setEndereco(rs.getString("endereco"));
                propriedade.setAreaM2(rs.getBigDecimal("area_m2"));
                propriedade.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                
                propriedades.add(propriedade);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar todas as propriedades: " + e.getMessage());
        }
        
        return propriedades;
    }

    public Propriedade buscarPropriedadePorId(int id) {
        String sql = "SELECT id_propriedade, tipo, descricao, regiao, endereco, area_m2, data_criacao FROM PROPRIEDADE WHERE id_propriedade = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Propriedade propriedade = new Propriedade();
                propriedade.setIdPropriedade(rs.getInt("id_propriedade"));
                propriedade.setTipo(rs.getString("tipo"));
                propriedade.setDescricao(rs.getString("descricao"));
                propriedade.setRegiao(rs.getString("regiao"));
                propriedade.setEndereco(rs.getString("endereco"));
                propriedade.setAreaM2(rs.getBigDecimal("area_m2"));
                propriedade.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
                
                return propriedade;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar propriedade por ID: " + e.getMessage());
        }
        
        return null;
    }
}

