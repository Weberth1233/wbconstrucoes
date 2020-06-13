package br.unitins.wbconstrucoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.wbconstrucoes.model.Produto;
import br.unitins.wbconstrucoes.model.Sexo;
import br.unitins.wbconstrucoes.model.Usuario;

public class ProdutoDao extends DAO<Produto> {

	@Override
	public boolean create(Produto entity) {
		Connection conn = getConnetion();
		boolean retorno = false;
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO produtos ");
		sql.append("	(descricao, valor, qtd_estoque, categoria) ");
		sql.append("VALUES ");
		sql.append("	(?, ?, ?, ?) ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, entity.getDescricao());
			stat.setDouble(2, entity.getValor());
			stat.setInt(3, entity.getQtdEstoque());
			stat.setString(4, entity.getCategoria());
			
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
	public boolean update(Produto entity) {
		boolean retorno = false;
		Connection conn = getConnetion();
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE produtos ");
		sql.append("	SET descricao=?, valor=?, qtd_estoque=?, categoria=? ");
		sql.append("WHERE ");
		sql.append("	id_produto = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, entity.getDescricao());
			stat.setDouble(2, entity.getValor());
			stat.setInt(3, entity.getQtdEstoque());
			stat.setString(4, entity.getCategoria());
			stat.setInt(5, entity.getId());
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
		sql.append("	produtos ");
		sql.append(" WHERE ");
		sql.append("	id_produto = ? ");
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
	public List<Produto> findAll() {
		Connection conn = getConnetion();
		List<Produto>listaProduto= new ArrayList<Produto>();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	id_produto, descricao, valor, qtd_estoque, categoria ");
		sql.append(" FROM ");
		sql.append(" 	produtos  ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			ResultSet rs = stat.executeQuery();
			Produto produto = null;
			
			while(rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id_produto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setValor(rs.getDouble("valor"));
				produto.setQtdEstoque(rs.getInt("qtd_estoque"));
				produto.setCategoria(rs.getString("categoria"));
				listaProduto.add(produto);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			closeConnection(conn);
			closeStatement(stat);
		}		
		return listaProduto;
	}
	@Override
	public Produto findById(int id) {
		Connection conn = getConnetion();
		Produto produto = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("	id_produto, descricao, valor, qtd_estoque, categoria ");
		sql.append("FROM ");
		sql.append(" 	produtos  ");
		sql.append("WHERE ");
		sql.append("	id_produto = ? ");
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery(); 
			
			while(rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id_produto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setValor(rs.getDouble("valor"));
				produto.setQtdEstoque(rs.getInt("qtd_Estoque"));
				produto.setCategoria(rs.getString("categoria"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			closeConnection(conn);
			closeStatement(stat);
		}
		return produto;
	}
	public List<Produto> getFiltroCategoria(String categoria){
		Connection conn = getConnetion();
		List<Produto> lista = new ArrayList<Produto>();
		
		StringBuffer sql= new StringBuffer();
		sql.append("SELECT ");
		sql.append("	id_produto, descricao, valor, qtd_estoque, categoria ");
		sql.append("FROM ");
		sql.append("	produtos ");
		sql.append("WHERE ");
		sql.append("	categoria ilike ? ");
		sql.append("ORDER BY categoria ");
		
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + categoria + "%");
			ResultSet rs = stat.executeQuery();
			Produto produto = null;
			while(rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id_produto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setValor(rs.getDouble("valor"));
				produto.setQtdEstoque(rs.getInt("qtd_estoque"));
				produto.setCategoria(rs.getString("categoria"));
				lista.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		}finally {
			closeConnection(conn);
			closeStatement(stat);
		}
		return lista;
	}
}
