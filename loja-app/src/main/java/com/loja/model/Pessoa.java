package com.loja.model;
import java.io.Serializable;

/* Classe Pessoa, com os campos id, nome, além de método
exibir, para impressão dos dados no console */
class Pessoa implements Serializable {
    
	private static final long serialVersionUID = 1L;
	protected int id;
    protected String nome;
    

    public Pessoa(String nome) {
        // Construtor padrão
    	this.nome = nome;
    }

    public Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;

    }

    public void exibir() {
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + this.nome);
    }

    
} 
