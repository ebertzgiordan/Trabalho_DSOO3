/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ebert
 */
public class Switch {
    private int id;
    private String nome;
    private int qtdPortas;

    public Switch() {
    }

    public Switch(int id, String nome, int qtdPortas) {
        this.id = id;
        this.nome = nome;
        this.qtdPortas = qtdPortas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdPortas() {
        return qtdPortas;
    }

    public void setQtdPortas(int qtdPortas) {
        this.qtdPortas = qtdPortas;
    }

    @Override
    public String toString() {
        return nome; // para exibir no JComboBox
    }
}

