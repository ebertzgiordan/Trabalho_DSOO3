/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ebert
 */
public class Porta {
    private int id;
    private int numero;
    private String tipo; // ACCESS, TRUNK, HYBRID
    private int switchId;

    public Porta() {
    }

    public Porta(int id, int numero, String tipo, int switchId) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.switchId = switchId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getSwitchId() {
        return switchId;
    }

    public void setSwitchId(int switchId) {
        this.switchId = switchId;
    }
}
