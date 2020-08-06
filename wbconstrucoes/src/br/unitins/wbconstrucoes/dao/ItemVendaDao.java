package br.unitins.wbconstrucoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.unitins.wbconstrucoes.model.ItemVenda;
import br.unitins.wbconstrucoes.model.Produto;
import br.unitins.wbconstrucoes.model.Venda;

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
	public List<ItemVenda> findByVenda(Venda venda) {
		List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();
		Connection conn = getConnetion();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  v.id, ");
		sql.append("  v.valor, ");
		sql.append("  v.id_produto, ");
		sql.append("  p.descricao, ");
		sql.append("  p.valor, ");
		sql.append("  p.qtd_estoque, ");
		sql.append("  p.categoria ");
		sql.append("FROM ");
		sql.append("  public.itemvenda v, ");
		sql.append("  public.produtos p ");
		sql.append("WHERE ");
		sql.append("  v.id_produto = p.id_produto AND ");
		sql.append("  v.idvenda = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, venda.getId());
			
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				ItemVenda item = new ItemVenda();
				item.setId(rs.getInt("id"));
				item.setValor(rs.getDouble("valor"));
				
				Produto produto = new Produto();
				produto.setId(rs.getInt("id_produto"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setValor(rs.getDouble("valor"));
				produto.setQtdEstoque(rs.getInt("qtd_estoque"));
				produto.setCategoria(rs.getString("categoria"));
				
				item.setProduto(produto);
				
				item.setVenda(venda);
				
				listaItemVenda.add(item);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return listaItemVenda;
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
