package controller;

import dao.UsuarioDAO;
import entity.Usuario;
import java.util.List;

public class UsuarioController {

    private final UsuarioDAO dao = new UsuarioDAO();

    public void salvar(Usuario u) throws Exception {
        if (!dao.loginExiste(u.getLogin())) {
            dao.inserir(u);
        } else {
            throw new Exception("Login já existente.");
        }
    }

    public void inserir(Usuario usuario) throws Exception {
        new UsuarioDAO().inserir(usuario);
    }

    public void atualizar(Usuario u) throws Exception {
        dao.atualizar(u);
    }

    public void excluir(int id) throws Exception {
        dao.excluir(id);
    }

    public List<Usuario> listarTodos() throws Exception {
        return dao.listarTodos();
    }
}
