/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ebert
 */
// PortaDAO.java
import entity.Porta;
import java.sql.*;
import java.util.*;
import util.ConnectionFactory;

public class PortaDAO {
    public void inserir(Porta p) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO porta (numero, tipo, switch_id) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, p.getNumero());
        ps.setString(2, p.getTipo());
        ps.setInt(3, p.getSwitchId());
        ps.executeUpdate();
        conn.close();
    }

    public List<Porta> listarPorSwitch(int switchId) throws SQLException {
        List<Porta> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM porta WHERE switch_id = ? ORDER BY numero";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, switchId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new Porta(
                rs.getInt("id"),
                rs.getInt("numero"),
                rs.getString("tipo"),
                rs.getInt("switch_id")
            ));
        }
        conn.close();
        return lista;
    }

    public void atualizarTipo(int portaId, String novoTipo) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE porta SET tipo = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, novoTipo);
        ps.setInt(2, portaId);
        ps.executeUpdate();
        conn.close();
    }
}

