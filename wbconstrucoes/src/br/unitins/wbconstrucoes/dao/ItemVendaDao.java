package br.unitins.wbconstrucoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.unitins.wbconstrucoes.model.ItemVenda;

public class ItemVendaDao extends DAO<ItemVenda> {

	@Override
	public boolean create(ItemVenda itemVenda) {
		Connection conn = getConnetion();
		boolean retorno = false;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO public.itemVenda ");
		sql.append("	(id_produto, valor, idvenda) ");
		sql.append("VALUES ");
		sql.append("	(?, ?, ?) ");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());

			stat.setInt(1,itemVenda.getProduto().getId());
			stat.setDouble(2, itemVenda.getValor());
			stat.setInt(3, itemVenda.getVenda().getId());
			stat.execute();
			conn.commit();

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
	public boolean update(ItemVenda entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ItemVenda> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemVenda findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
