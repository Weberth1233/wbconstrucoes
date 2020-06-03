package br.unitins.wbconstrucoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.wbconstrucoes.model.Sexo;
import br.unitins.wbconstrucoes.model.Usuario;

public class UsuarioDao extends DAO<Usuario> {

	@Override
	public boolean create(Usuario entity) {
		Connection conn = getConnetion();
		boolean retorno = false;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO usuario ");
		sql.append("	(nome, datanascimento, login, senha, cpf, email, sexo) ");
		sql.append("VALUES ");
		sql.append("	(?, ?, ?, ?, ?, ?, ?) ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, entity.getNome());
			/*Recebendo uma data em localDate e a convertendo para date para que assim possa ser armazenado no banco de forma correta.*/
			stat.setDate(2, java.sql.Date.valueOf(entity.getDataNascimento()));
			stat.setString(3, entity.getLogin());
			stat.setString(4, entity.getSenha());
			stat.setString(5, entity.getCpf());
			stat.setString(6, entity.getEmail());
			stat.setInt(7, entity.getSexo().getId());
			
			stat.execute();
			conn.commit();
			System.out.println("Inserção com sucesso!");
			
			retorno = true;
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			closeConnection(conn);
			closeStatement(stat);
		}
		return retorno;
	}
	public boolean update(Usuario usuario) {
		boolean retorno = false;
		Connection conn = getConnetion();
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE usuario ");
		sql.append("	SET nome=?, datanascimento=?, login=?, senha=?, cpf=?, email=?, sexo=? ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, usuario.getNome());
			stat.setDate(2, java.sql.Date.valueOf(usuario.getDataNascimento()));
			stat.setString(3, usuario.getLogin());
			stat.setString(4, usuario.getSenha());
			stat.setString(5, usuario.getCpf());
			stat.setString(6, usuario.getEmail());
			stat.setInt(7, usuario.getSexo().getId());
			stat.setInt(8, usuario.getId());
			stat.execute();
			
			conn.commit();

			System.out.println("AlteraÃ§Ã£o realizada com sucesso.");
			
			retorno = true;

		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;		
		
	}
	@Override
	public boolean delete(int id) {
		Connection conn= getConnetion();
		boolean retorno = false;
		StringBuffer sql= new StringBuffer();
		sql.append(" DELETE FROM ");
		sql.append("	usuario ");
		sql.append(" WHERE ");
		sql.append("	id = ? ");
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			stat.execute();
			conn.commit();
			retorno = true;
		}catch (SQLException e) {
			rollback(conn);
			e.printStackTrace();
		}finally {
			closeConnection(conn);
			closeStatement(stat);
		}
		return retorno;
	}
	@Override
	public List<Usuario> findAll() {
		Connection conn = getConnetion();
		List<Usuario>listaUsuario= new ArrayList<Usuario>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	id, nome, datanascimento, login, senha, sexo, cpf, email ");
		sql.append(" FROM ");
		sql.append(" 	usuario  ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			ResultSet rs = stat.executeQuery();
			Usuario usu = null;
			
			while(rs.next()) {
				usu = new Usuario();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
				usu.setLogin(rs.getString("login"));
				usu.setSenha(rs.getString("senha"));
				usu.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usu.setCpf(rs.getString("cpf"));
				usu.setEmail(rs.getString("email"));
				listaUsuario.add(usu);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			closeConnection(conn);
			closeStatement(stat);
		}		
		return listaUsuario;
	}
	@Override
	public Usuario findById(int id) {
		Connection conn = getConnetion();
		Usuario usu = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	id, nome, datanascimento, login, senha, sexo, cpf, email ");
		sql.append("FROM ");
		sql.append(" 	usuario  ");
		sql.append("WHERE ");
		sql.append("	id = ? ");
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery(); 
			
			while(rs.next()) {
				usu = new Usuario();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
				usu.setLogin(rs.getString("login"));
				usu.setSenha(rs.getString("senha"));
				usu.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usu.setCpf(rs.getString("cpf"));
				usu.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			closeConnection(conn);
			closeStatement(stat);
		}
		return usu;
	}
	public Usuario verificarLoginSenha(String login, String senha) {
		Usuario usu = null;
		Connection conn = getConnetion();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append(" 	id, nome, datanascimento, login, senha, sexo, cpf, email ");
		sql.append("FROM ");
		sql.append("	usuario ");
		sql.append("WHERE ");
		sql.append("	login = ? ");
		sql.append("	AND senha = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, login);
			stat.setString(2, senha);
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				usu = new Usuario();
				usu.setId(rs.getInt("id"));
				usu.setNome(rs.getString("nome"));
				usu.setDataNascimento(rs.getDate("datanascimento").toLocalDate());
				usu.setLogin(rs.getString("login"));
				usu.setSenha(rs.getString("senha"));
				usu.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usu.setCpf(rs.getString("cpf"));
				usu.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return usu;
	}
}
