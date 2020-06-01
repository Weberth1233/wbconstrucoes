package br.unitins.wbconstrucoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.unitins.wbconstrucoes.model.Usuario;

public class UsuarioDao extends DAO<Usuario> {

	@Override
	public boolean create(Usuario entity) {
		Connection conn = null;
		conn = getConnetion();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO usuario ");
		sql.append("	(nome, datanascimento, login, senha, sexo, cpf, email) ");
		sql.append("VALUES ");
		sql.append("	(?, ?, ?, ?, ?, ?, ?) ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, entity.getNome());
			/*Recebendo uma data em localDate e a convertendo para date para que assim possa ser armazenado no banco de forma correta.*/
			stat.setDate(2, java.sql.Date.valueOf(entity.getDataNascimento()));
			stat.setString(3, entity.getLogin());
			stat.setString(4, entity.getLogin());
			stat.setString(5, entity.getSenha());
			stat.setInt(6, entity.getSexo().getId());
			stat.setString(7, entity.getCpf());
			stat.setString(8, entity.getEmail());
			
			stat.execute();
			
			conn.commit();
			System.out.println("Inserção com sucesso!");
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			closeConnection(conn);
			closeStatement(stat);
		}
		return false;
	}
	@Override
	public boolean update(Usuario entity) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Usuario findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
