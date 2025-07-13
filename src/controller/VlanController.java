/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ebert
 */
// VlanController.java
import dao.VlanDAO;
import entity.Vlan;
import java.util.List;

public class VlanController {
    private VlanDAO vlanDAO = new VlanDAO();

    public void salvar(Vlan v) throws Exception {
        if (v.getIdVlan() <= 0 || v.getNome().isEmpty()) {
            throw new Exception("ID da VLAN e nome são obrigatórios.");
        }
        vlanDAO.inserir(v);
    }

    public List<Vlan> listarTodas() throws Exception {
        return vlanDAO.listarTodas();
    }

    public boolean existeVlan(int idVlan) throws Exception {
        return vlanDAO.existePorId(idVlan);
    }
}
