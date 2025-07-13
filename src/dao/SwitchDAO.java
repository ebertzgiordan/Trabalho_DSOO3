/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ebert
 */
// SwitchDAO.java
import entity.Switch;
import java.sql.*;
import java.util.*;
import util.ConnectionFactory;

public class SwitchDAO {

    public void inserir(Switch s) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO switch (nome, qtd_portas) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, s.getNome());
        ps.setInt(2, s.getQtdPortas());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            s.setId(rs.getInt(1));
        }
        conn.close();
    }

    public List<Switch> listarTodos() throws SQLException {
        List<Switch> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM switch");
        while (rs.next()) {
            lista.add(new Switch(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("qtd_portas")
            ));
        }
        conn.close();
        return lista;
    }

    public void atualizar(Switch s) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE switch SET nome = ?, qtd_portas = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, s.getNome());
        ps.setInt(2, s.getQtdPortas());
        ps.setInt(3, s.getId());
        ps.executeUpdate();
        conn.close();
    }

    public void excluir(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();

        try {
            conn.setAutoCommit(false); // Inicia transação

            // 1. Excluir todas as portas associadas
            String sqlPortas = "DELETE FROM porta WHERE switch_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sqlPortas);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            // 2. Excluir o switch
            String sqlSwitch = "DELETE FROM switch WHERE id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sqlSwitch);
            ps2.setInt(1, id);
            ps2.executeUpdate();

            conn.commit(); // Finaliza transação

        } catch (SQLException e) {
            conn.rollback(); // Volta caso dê erro
            throw new SQLException("Erro ao excluir switch e suas portas: " + e.getMessage());
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

}
