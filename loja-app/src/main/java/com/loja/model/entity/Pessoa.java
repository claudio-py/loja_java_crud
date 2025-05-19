package com.loja.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED) // Defines inheritance mapping
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented primary key
    @Column(name = "id_pessoa")
    protected int id;

    @Column(nullable = false)
    protected String nome;

    public Pessoa() {}

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters and Setters
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
}
