package controller;

import dao.PortaVlanDAO;
import java.util.List;

public class PortaVlanController {

    private final PortaVlanDAO dao = new PortaVlanDAO();

    public void atribuirVlan(int portaId, int vlanId) throws Exception {
        dao.inserir(portaId, vlanId); // versão antiga (sem modo)
    }

    public void atribuirVlan(int portaId, int vlanId, String modo) throws Exception {
        dao.atribuirVlan(portaId, vlanId, modo); // nova versão
    }

    public void removerVlan(int portaId, int vlanId) throws Exception {
        dao.removerVlan(portaId, vlanId);
    }

    public List<Integer> listarVlansPorPorta(int portaId) throws Exception {
        return dao.listarVlansPorPorta(portaId);
    }

    public List<String> listarVlansComModoPorPorta(int portaId) throws Exception {
        return dao.listarVlansComModoPorPorta(portaId);
    }
}
