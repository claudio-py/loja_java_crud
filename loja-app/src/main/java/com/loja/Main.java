package com.loja;

import java.util.List;
import java.util.Scanner;

import com.loja.model.PessoaFisica;
import com.loja.model.PessoaFisicaDAO;
import com.loja.model.PessoaJuridica;
import com.loja.model.PessoaJuridicaDAO;
import com.loja.model.util.ConectorBD;

public class Main {


    public static void main(String[] args) {
        Main app = new Main();
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao;
            do {
                System.out.println("1. Incluir");
                System.out.println("2. Alterar");
                System.out.println("3. Excluir");
                System.out.println("4. Buscar por ID");
                System.out.println("5. Exibir todos");
                System.out.println("0. Finalizar");
                System.out.print("Digite a opção desejada: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Por favor, insira um número válido.");
                    scanner.next();
                }
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (opcao) {
                    case 1:
                        app.handleIncluir(scanner);
                        break;
                    case 2:
                        app.handleAlterar(scanner);
                        break;
                    case 3:
                        app.handleExcluir(scanner);
                        break;
                    case 4:
                        app.handleBuscarPorId(scanner);
                        break;
                    case 5:
                        app.handleExibirTodos(scanner);
                        break;
                    case 0:
                        System.out.println("Finalizado...");
                        ConectorBD.closeConnection();
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 0);
        }
        
    }

    private void handleIncluir(Scanner scanner) {
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Digite a opção desejada: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (opcao == 1) {
            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o CPF: ");
            String cpf = scanner.nextLine();
            testIncluirPessoaFisica(nome, cpf);
        } else if (opcao == 2) {
            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o CNPJ: ");
            String cnpj = scanner.nextLine();
            testIncluirPessoaJuridica(nome, cnpj);
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void handleAlterar(Scanner scanner) {
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Digite a opção desejada: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (opcao == 1) {
            System.out.print("Digite o ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o CPF: ");
            String cpf = scanner.nextLine();
            testAlterarPessoaFisica(id, nome, cpf);
        } else if (opcao == 2) {
            System.out.print("Digite o ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Digite o nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o CNPJ: ");
            String cnpj = scanner.nextLine();
            testAlterarPessoaJuridica(id, nome, cnpj);
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void handleExcluir(Scanner scanner) {
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Digite a opção desejada: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (opcao == 1) {
            System.out.print("Digite o ID: ");
            int id = scanner.nextInt();
            testExcluirPessoaFisica(id);
        } else if (opcao == 2) {
            System.out.print("Digite o ID: ");
            int id = scanner.nextInt();
            testExcluirPessoaJuridica(id);
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void handleBuscarPorId(Scanner scanner) {
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Digite a opção desejada: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (opcao == 1) {
            System.out.print("Digite o ID: ");
            int id = scanner.nextInt();
            testPessoaFisicaPorId(id);
        } else if (opcao == 2) {
            System.out.print("Digite o ID: ");
            int id = scanner.nextInt();
            testPessoaJuridicaPorId(id);
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void handleExibirTodos(Scanner scanner) {
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.print("Digite a opção desejada: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (opcao == 1) {
            testListarPessoasFisicas();
        } else if (opcao == 2) {
            testListarPessoasJuridicas();
        } else {
            System.out.println("Opção inválida.");
        }
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
