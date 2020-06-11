package br.unitins.wbconstrucoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.unitins.wbconstrucoes.model.Produto;

public class ProdutoDao extends DAO<Produto> {

	@Override
	public boolean create(Produto entity) {
		Connection conn = getConnetion();
		boolean verificando = false;
		
		StringBuffer sql= new StringBuffer();
		sql.append("INSERT INTO produtos ");
		sql.append(" 	(descricao, valor, qtdEstoque, categoria) ");
		sql.append("VALUES ");
		sql.append(" 	(?, ?, ?, ?, ?) ");
		
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, entity.getDescricao());
			stat.setFloat(2, entity.getValor());
			stat.setInt(3, entity.getQtdEstoque());
			stat.setString(4, entity.getCategoria());
			stat.execute();
			conn.commit();
			verificando = true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		}
		finally {
			closeConnection(conn);
			closeStatement(stat);
		}
		return verificando;
	}

	@Override
	public boolean update(Produto entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Produto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produto findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
