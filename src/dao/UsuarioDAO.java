package dao;

import entity.Usuario;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void inserir(Usuario usuario) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "INSERT INTO usuario (nome, login, senha, nivel) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usuario.getNome());
        ps.setString(2, usuario.getLogin());
        ps.setString(3, usuario.getSenha());
        ps.setString(4, usuario.getNivel());
        ps.executeUpdate();
        conn.close();
    }

    public void atualizar(Usuario u) throws Exception {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "UPDATE usuario SET nome=?, senha=?, nivel=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, u.getNome());
        ps.setString(2, u.getSenha());
        ps.setString(3, u.getNivel());
        ps.setInt(4, u.getId());
        ps.executeUpdate();
        conn.close();
    }

    public void excluir(int id) throws Exception {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "DELETE FROM usuario WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        conn.close();
    }

    public List<Usuario> listarTodos() throws Exception {
        List<Usuario> lista = new ArrayList<>();
        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM usuario";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Usuario u = new Usuario();
            u.setId(rs.getInt("id"));
            u.setNome(rs.getString("nome"));
            u.setLogin(rs.getString("login"));
            u.setSenha(rs.getString("senha"));
            u.setNivel(rs.getString("nivel"));
            lista.add(u);
        }
        conn.close();
        return lista;
    }

    public boolean loginExiste(String login) throws Exception {
        Connection conn = ConnectionFactory.getConnection();
        String sql = "SELECT COUNT(*) FROM usuario WHERE login = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, login);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next() && rs.getInt(1) > 0;
        conn.close();
        return existe;
    }

    public Usuario buscarPorLogin(String login) {
        String sql = "SELECT * FROM usuario WHERE login = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setNivel(rs.getString("nivel"));
                return u;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário: " + e.getMessage(), e);
        }
        return null;
    }
}
