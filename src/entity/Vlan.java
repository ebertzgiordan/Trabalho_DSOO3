/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ebert
 */
public class Vlan {
    private int id;
    private int idVlan;
    private String nome;

    public Vlan() {
    }

    public Vlan(int id, int idVlan, String nome) {
        this.id = id;
        this.idVlan = idVlan;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVlan() {
        return idVlan;
    }

    public void setIdVlan(int idVlan) {
        this.idVlan = idVlan;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return idVlan + " - " + nome;
    }
}

