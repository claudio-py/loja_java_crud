package com.loja.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.loja.model.util.ConectorBD;


/* Classe PessoaJuridicaDAO, com os métodos getPessoa, retornando
uma pessoa jurídica a partir do seu id, getPessoas, para retorno de
todas as pessoas jurídicas do banco de dados, incluir, para inclusão de
uma pessoa jurídica, fornecida como parâmetro, nas tabelas Pessoa e
PessoaJuridica, alterar, para alteração dos dados de uma pessoa
jurídica, e excluir, para remoção da pessoa do banco em ambas as
tabelas.Utilizar na classe objetos dos tipos ConectorBD e
SequenceManager
*/



public class PessoaJuridicaDAO {
    private static final String SQL_GET_BY_ID = "SELECT * FROM vw_pessoa_juridica WHERE id_pessoa = ?";
    private static final String SQL_GET_ALL = "SELECT * FROM vw_pessoa_juridica";
    private static final String SQL_INCLUIR = "INSERT INTO vw_pessoa_juridica (nome, cnpj) VALUES (?, ?)";
    private static final String SQL_ALTERAR = "UPDATE vw_pessoa_juridica SET nome = ?, cnpj = ? WHERE id_pessoa = ?";
    private static final String SQL_EXCLUIR = "DELETE FROM vw_pessoa_juridica WHERE id_pessoa = ?";
    
    // Implementação dos métodos...
   
    public PessoaJuridica getPessoa(int id) {
        try (
        		Connection connection = ConectorBD.getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID)
			) { 
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new PessoaJuridica(
                        resultSet.getInt("id_pessoa"),
                        resultSet.getString("nome"),
                        resultSet.getString("cnpj")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return null;
    }
    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        try (Connection connection = ConectorBD.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL)) {
            while (resultSet.next()) {
                pessoas.add(new PessoaJuridica(
                        resultSet.getInt("id_pessoa"),
                        resultSet.getString("nome"),
                        resultSet.getString("cnpj")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
                  
        }
        return pessoas;
    }
    public void incluir(PessoaJuridica pessoa) {
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INCLUIR)) {
            preparedStatement.setString(1, pessoa.nome);
            preparedStatement.setString(2, pessoa.cnpj);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
                  
        }
    }
    public void alterar(int id, String nome, String cnpj) {
    	PessoaJuridica pessoa = new PessoaJuridica(id, nome, cnpj);
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_ALTERAR)) {
            preparedStatement.setString(1, pessoa.nome);
            preparedStatement.setString(2, pessoa.cnpj);
            preparedStatement.setInt(3, pessoa.id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
                  
        }
    }
    public void excluir(int id) {
        try (Connection connection = ConectorBD.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_EXCLUIR)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
                  
        }
    }
}
