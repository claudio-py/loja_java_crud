package com.loja;
/* Consultar todas as pessoas físicas do banco de dados e listar no
console. */

import java.util.List;

import com.loja.model.PessoaFisica;
import com.loja.model.PessoaFisicaDAO;
import com.loja.model.PessoaJuridica;
import com.loja.model.PessoaJuridicaDAO;

class Main {
    public static void main(String[] args) {
    	//testIncluirPessoaFisica("JHON RICHA", "3SD78900");
    	//testExcluirPessoaFisica(19);
    	testAlterarPessoaFisica(1, "gerivaldo", "3SD7dddd9");
    //	testListarPessoasFisicas();
    	//testPessoaFisicaPorId(19);
    	
    	
    	//testIncluirPessoaJuridica("comercio de roupas", "12345678901234");
    	//testExcluirPessoaJuridica(20);
    	//testAlterarPessoaJuridica(20, "comercio de roupas", "12345678901234");
//    	testListarPessoasJuridicas();
    	//testPessoaJuridicaPorId(20);
    }
    
    /* Método para listar todas as pessoas físicas do banco de dados */
    static void testListarPessoasFisicas() {
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        List<PessoaFisica> pessoas = pessoaFisicaDAO.getPessoas();

        for (PessoaFisica pessoa : pessoas) {
            pessoa.exibir();
            System.out.println("-----------------------------");
        }
    }
    
    /* Método para listar todas as pessoas jurídicas do banco de dados */
    static void testListarPessoasJuridicas() {
		PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
		List<PessoaJuridica> pessoas = pessoaJuridicaDAO.getPessoas();

		for (PessoaJuridica pessoa : pessoas) {
			pessoa.exibir();
			System.out.println("-----------------------------");
		}
	}
    /* Método para listar uma pessoaFísica pelo id */
    static void testPessoaFisicaPorId(int id) {
		PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
		PessoaFisica pessoa = pessoaFisicaDAO.getPessoa(id);
		if (pessoa != null) {
			pessoa.exibir();
		} else {
			System.out.println("Pessoa não encontrada.");
		}
	}
    /* Método para listar uma pessoaJuridica pelo id */
	static void testPessoaJuridicaPorId(int id) {
		PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
		PessoaJuridica pessoa = pessoaJuridicaDAO.getPessoa(id);
		if (pessoa != null) {
			pessoa.exibir();
		} else {
			System.out.println("Pessoa não encontrada.");
		}
	}
	
	/* Método para incluir uma pessoa física */
	static void testIncluirPessoaFisica(String nome, String cpf) {
		PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
		PessoaFisica novaPessoa = new PessoaFisica(nome, cpf);
		pessoaFisicaDAO.incluir(novaPessoa);
		System.out.println("Pessoa física incluída com sucesso.");
	}
	
	/* Método para incluir uma pessoa jurídica */
	static void testIncluirPessoaJuridica(String nome, String cnpj) {
		PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
		PessoaJuridica novaPessoa = new PessoaJuridica(nome, cnpj);
		pessoaJuridicaDAO.incluir(novaPessoa);
		System.out.println("Pessoa jurídica incluída com sucesso.");
	}
    
	/* Método para excluir uma pessoa física */
	static void testExcluirPessoaFisica(int id) {
		PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
		pessoaFisicaDAO.excluir(id);
		System.out.println("Pessoa física excluída com sucesso.");
	}
	
	/* Método para excluir uma pessoa jurídica */
	static void testExcluirPessoaJuridica(int id) {
		PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
		pessoaJuridicaDAO.excluir(id);
		System.out.println("Pessoa jurídica excluída com sucesso.");
	}
	/* Método para alterar uma pessoa física */
	static void testAlterarPessoaFisica(int id, String nome, String cpf) {
		PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
		pessoaFisicaDAO.alterar(id, nome, cpf);
		System.out.println("Pessoa física alterada com sucesso.");
	}
	
	/* Método para alterar uma pessoa jurídica */
	static void testAlterarPessoaJuridica(int id, String nome, String cnpj) {
		PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
		pessoaJuridicaDAO.alterar(id, nome, cnpj);
		System.out.println("Pessoa jurídica alterada com sucesso.");
	}
}

