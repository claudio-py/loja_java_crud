package com.loja.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/* Classe PessoaFisicaDAO, com os métodos getPessoa, retornando
uma pessoa física a partir do seu id, getPessoas, para retorno de
todas as pessoas físicas do banco de dados, incluir, para inclusão de
uma pessoa física, fornecida como parâmetro, nas tabelas Pessoa e
PessoaFisica, alterar, para alteração dos dados de uma pessoa física,
e excluir, para remoção da pessoa do banco em ambas as tabelas. 
Utilizar na classe objetos dos tipos ConectorBD e
SequenceManager.
*/

import com.loja.model.util.ConectorBD;



public class PessoaFisicaDAO {
    private static final String SQL_GET_BY_ID = "SELECT * FROM vw_pessoa_fisica WHERE id_pessoa = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM vw_pessoa_fisica";
    private static final String SQL_INCLUIR = "INSERT INTO vw_pessoa_fisica (nome, cpf) VALUES (?, ?)";
    private static final String SQL_ALTERAR = "UPDATE vw_pessoa_fisica SET nome = ?, cpf = ? WHERE id_pessoa = ?";
    private static final String SQL_EXCLUIR = "DELETE FROM vw_pessoa_fisica WHERE id_pessoa = ?";
    
    // Implementação dos métodos...
    public PessoaFisica getPessoa(int id) {
        try (
        		Connection connection = ConectorBD.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID)
    		) { 
        	preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new PessoaFisica(
                        resultSet.getInt("id_pessoa"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
                  
        }
        return null;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        try (Connection connection = ConectorBD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL)) {
            while (resultSet.next()) {
                pessoas.add(new PessoaFisica(
                        resultSet.getInt("id_pessoa"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return pessoas;
    }
    public void incluir(PessoaFisica pessoa) {
        try (
        	Connection connection = ConectorBD.getConnection();
        	PreparedStatement preparedStatement = connection.prepareStatement(SQL_INCLUIR);
        	) {
            preparedStatement.setString(1, pessoa.nome);
            preparedStatement.setString(2, pessoa.cpf);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void alterar(int id, String nome, String cpf) {
    	PessoaFisica pessoa = new PessoaFisica(id, nome, cpf);
        try (
        	Connection connection = ConectorBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ALTERAR)
            ) {
            preparedStatement.setString(1, pessoa.nome);
            preparedStatement.setString(2, pessoa.cpf);
            preparedStatement.setInt(3, pessoa.id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void excluir(int id) {
        try (
        	Connection connection = ConectorBD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_EXCLUIR)
            ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
