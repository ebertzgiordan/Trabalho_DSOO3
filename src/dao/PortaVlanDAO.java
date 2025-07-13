/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ebert
 */
// PortaVlanDAO.java
import java.sql.*;
import java.util.*;
import util.ConnectionFactory;

public class PortaVlanDAO {

    public void inserir(int portaId, int vlanId) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT OR IGNORE INTO porta_vlan (porta_id, vlan_id) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, portaId);
        ps.setInt(2, vlanId);
        ps.executeUpdate();
        conn.close();
    }

    public void remover(int portaId, int vlanId) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "DELETE FROM porta_vlan WHERE porta_id = ? AND vlan_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, portaId);
        ps.setInt(2, vlanId);
        ps.executeUpdate();
        conn.close();
    }

    public Map<String, List<Integer>> buscarRelatorioVlanPorSwitch() throws SQLException {
        Map<String, List<Integer>> mapa = new HashMap<>();
        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT s.nome as switch_nome, p.numero as porta_numero, v.id_vlan "
                + "FROM porta_vlan pv "
                + "JOIN porta p ON pv.porta_id = p.id "
                + "JOIN switch s ON p.switch_id = s.id "
                + "JOIN vlan v ON pv.vlan_id = v.id "
                + "ORDER BY s.nome, p.numero, v.id_vlan";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String chave = rs.getString("switch_nome") + " - Porta " + rs.getInt("porta_numero");
            int vlanId = rs.getInt("id_vlan");
            mapa.computeIfAbsent(chave, k -> new ArrayList<>()).add(vlanId);
        }

        conn.close();
        return mapa;
    }

    public void atribuirVlan(int portaId, int vlanId, String modo) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT OR REPLACE INTO porta_vlan (porta_id, vlan_id, modo) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, portaId);
        ps.setInt(2, vlanId);
        ps.setString(3, modo.toUpperCase()); // "TAGGED" ou "UNTAGGED"
        ps.executeUpdate();
        conn.close();
    }

    public void removerVlan(int portaId, int vlanId) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "DELETE FROM porta_vlan WHERE porta_id = ? AND vlan_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, portaId);
        ps.setInt(2, vlanId);
        ps.executeUpdate();
        conn.close();
    }

    public List<Integer> listarVlansPorPorta(int portaId) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT vlan_id FROM porta_vlan WHERE porta_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, portaId);
        ResultSet rs = ps.executeQuery();
        List<Integer> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(rs.getInt("vlan_id"));
        }
        conn.close();
        return lista;
    }

    public List<String> listarVlansComModoPorPorta(int portaId) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT vlan_id, modo FROM porta_vlan WHERE porta_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, portaId);
        ResultSet rs = ps.executeQuery();
        List<String> lista = new ArrayList<>();
        while (rs.next()) {
            int vlanId = rs.getInt("vlan_id");
            String modo = rs.getString("modo");
            lista.add(vlanId + " (" + modo + ")");
        }
        conn.close();
        return lista;
    }

    public List<Map<String, String>> gerarRelatorioDetalhadoPorSwitch(int switchId) throws SQLException {
        List<Map<String, String>> resultado = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()) {

            // 1) Buscar nome do switch
            String switchNome = "";
            String sqlSwitch = "SELECT nome FROM switch WHERE id = ?";
            try (PreparedStatement psSwitch = conn.prepareStatement(sqlSwitch)) {
                psSwitch.setInt(1, switchId);
                try (ResultSet rs = psSwitch.executeQuery()) {
                    if (rs.next()) {
                        switchNome = rs.getString("nome");
                    } else {
                        return resultado; // Switch não encontrado
                    }
                }
            }

            // 2) Consultar portas com VLANs associadas
            String sql = """
            SELECT 
                p.numero AS porta_numero,
                p.tipo,
                GROUP_CONCAT(
                    pv.vlan_id || 
                    ' (' || IFNULL(v.nome, 'sem nome') || ', ' || IFNULL(pv.modo, 'modo?') || ')',
                    ', '
                ) AS vlans
            FROM porta p
            LEFT JOIN porta_vlan pv ON p.id = pv.porta_id
            LEFT JOIN vlan v ON pv.vlan_id = v.id_vlan
            WHERE p.switch_id = ?
            GROUP BY p.id
            ORDER BY p.numero
        """;

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, switchId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Map<String, String> linha = new LinkedHashMap<>();
                        linha.put("switch", switchNome);
                        linha.put("porta", String.valueOf(rs.getInt("porta_numero")));
                        linha.put("tipo", rs.getString("tipo"));

                        String vlans = rs.getString("vlans");
                        if (vlans == null || vlans.trim().isEmpty()) {
                            vlans = "Nenhuma atribuída";
                        }
                        linha.put("vlans", vlans);

                        resultado.add(linha);
                    }
                }
            }
        }

        return resultado;
    }

}
