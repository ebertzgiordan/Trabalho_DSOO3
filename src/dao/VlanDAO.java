/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ebert
 */
// VlanDAO.java
import entity.Vlan;
import java.sql.*;
import java.util.*;
import util.ConnectionFactory;

public class VlanDAO {

    public void inserir(Vlan v) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO vlan (id_vlan, nome) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, v.getIdVlan());
        ps.setString(2, v.getNome());
        ps.executeUpdate();
        conn.close();
    }

    public List<Vlan> listarTodas() throws SQLException {
        List<Vlan> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM vlan ORDER BY id_vlan");
        while (rs.next()) {
            lista.add(new Vlan(
                    rs.getInt("id"),
                    rs.getInt("id_vlan"),
                    rs.getString("nome")
            ));
        }
        conn.close();
        return lista;
    }

    public boolean existePorId(int idVlan) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT COUNT(*) FROM vlan WHERE id_vlan = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idVlan);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next() && rs.getInt(1) > 0;
        conn.close();
        return existe;
    }

    public void excluir(int id) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "DELETE FROM vlan WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        conn.close();
    }

    public void atualizar(Vlan v) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE vlan SET id_vlan = ?, nome = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, v.getIdVlan());
        ps.setString(2, v.getNome());
        ps.setInt(3, v.getId());
        ps.executeUpdate();
        conn.close();
    }

}
