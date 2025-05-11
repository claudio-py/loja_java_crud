package com.loja.model;
/* Classe PessoaJuridica, herdando de Pessoa, com acréscimo do campo
cnpj, além da reescrita dos construtores e uso de polimorfismo em
exibir */
public class PessoaJuridica extends Pessoa {
    private static final long serialVersionUID = 1L;
	protected String cnpj;

    public PessoaJuridica(String nome, String cnpj) {
    	// Construtor padrão
		super(nome);
		this.cnpj = cnpj;
    }

    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + this.cnpj);
    }

}
