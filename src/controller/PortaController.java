/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ebert
 */
// PortaController.java
import dao.PortaDAO;
import entity.Porta;
import java.util.List;

public class PortaController {
    private PortaDAO portaDAO = new PortaDAO();

    public List<Porta> listarPorSwitch(int switchId) throws Exception {
        return portaDAO.listarPorSwitch(switchId);
    }

    public void atualizarTipo(int portaId, String tipo) throws Exception {
        portaDAO.atualizarTipo(portaId, tipo);
    }
}

