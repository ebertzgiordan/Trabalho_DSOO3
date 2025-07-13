/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ebert
 */
// SwitchController.java
import dao.PortaDAO;
import dao.SwitchDAO;
import entity.Porta;
import entity.Switch;
import java.util.List;

public class SwitchController {
    private SwitchDAO switchDAO = new SwitchDAO();
    private PortaDAO portaDAO = new PortaDAO();

    public void salvar(Switch s) throws Exception {
        if (s.getNome().isEmpty() || s.getQtdPortas() <= 0) {
            throw new Exception("Nome e quantidade de portas são obrigatórios.");
        }
        switchDAO.inserir(s);
        for (int i = 1; i <= s.getQtdPortas(); i++) {
            portaDAO.inserir(new Porta(0, i, "ACCESS", s.getId()));
        }
    }

    public List<Switch> listarTodos() throws Exception {
        return switchDAO.listarTodos();
    }
}
