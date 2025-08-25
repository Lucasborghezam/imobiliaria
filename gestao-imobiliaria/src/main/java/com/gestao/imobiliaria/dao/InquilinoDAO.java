package com.gestao.imobiliaria.dao;

import com.gestao.imobiliaria.model.Inquilino;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InquilinoDAO {

    public boolean cadastrarInquilino(Inquilino inquilino) {
        String sql = "INSERT INTO INQUILINO (cpf_inquilino, nome_inquilino, email_inquilino) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, inquilino.getCpfInquilino());
            stmt.setString(2, inquilino.getNomeInquilino());
            stmt.setString(3, inquilino.getEmailInquilino());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Inquilino cadastrado com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar inquilino: " + e.getMessage());
        }
        return false;
    }

    public List<Inquilino> listarInquilinos() {
        String sql = "SELECT id_inquilino, cpf_inquilino, nome_inquilino, email_inquilino FROM INQUILINO ORDER BY nome_inquilino";
        
        List<Inquilino> inquilinos = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Inquilino inquilino = new Inquilino();
                inquilino.setIdInquilino(rs.getInt("id_inquilino"));
                inquilino.setCpfInquilino(rs.getString("cpf_inquilino"));
                inquilino.setNomeInquilino(rs.getString("nome_inquilino"));
                inquilino.setEmailInquilino(rs.getString("email_inquilino"));
                
                inquilinos.add(inquilino);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar inquilinos: " + e.getMessage());
        }
        
        return inquilinos;
    }

    public List<Object[]> listarInquilinosComMaisContratos() {
        String sql = "SELECT i.id_inquilino, i.nome_inquilino, i.email_inquilino, COUNT(l.id_locacao) as total_contratos " +
                     "FROM INQUILINO i " +
                     "LEFT JOIN LOCACAO l ON i.id_inquilino = l.id_inquilino " +
                     "GROUP BY i.id_inquilino, i.nome_inquilino, i.email_inquilino " +
                     "ORDER BY total_contratos DESC, i.nome_inquilino";
        
        List<Object[]> resultado = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Object[] linha = new Object[4];
                linha[0] = rs.getInt("id_inquilino");
                linha[1] = rs.getString("nome_inquilino");
                linha[2] = rs.getString("email_inquilino");
                linha[3] = rs.getInt("total_contratos");
                
                resultado.add(linha);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar inquilinos com mais contratos: " + e.getMessage());
        }
        
        return resultado;
    }

    public Inquilino buscarInquilinoPorId(int id) {
        String sql = "SELECT id_inquilino, cpf_inquilino, nome_inquilino, email_inquilino FROM INQUILINO WHERE id_inquilino = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Inquilino inquilino = new Inquilino();
                inquilino.setIdInquilino(rs.getInt("id_inquilino"));
                inquilino.setCpfInquilino(rs.getString("cpf_inquilino"));
                inquilino.setNomeInquilino(rs.getString("nome_inquilino"));
                inquilino.setEmailInquilino(rs.getString("email_inquilino"));
                
                return inquilino;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar inquilino por ID: " + e.getMessage());
        }
        
        return null;
    }
}

