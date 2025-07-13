/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ebert
 */
public class PortaVlan {
    private int portaId;
    private int vlanId;

    public PortaVlan() {
    }

    public PortaVlan(int portaId, int vlanId) {
        this.portaId = portaId;
        this.vlanId = vlanId;
    }

    public int getPortaId() {
        return portaId;
    }

    public void setPortaId(int portaId) {
        this.portaId = portaId;
    }

    public int getVlanId() {
        return vlanId;
    }

    public void setVlanId(int vlanId) {
        this.vlanId = vlanId;
    }
}