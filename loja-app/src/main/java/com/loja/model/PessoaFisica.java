package com.loja.model;
/* Classe PessoaFisica, herdando de Pessoa, com acréscimo do campo
cpf, além da reescrita dos construtores e uso de polimorfismo em
exibir */

public class PessoaFisica extends Pessoa {
    private static final long serialVersionUID = 1L;
	protected String cpf;

    public PessoaFisica(String nome, String cpf) {
    	// Construtor padrão
		super(nome);
		this.cpf = cpf;
    }

    public PessoaFisica(int id, String nome, String cpf) {
        super(id, nome);
        this.cpf = cpf;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + this.cpf);
    }

} 
