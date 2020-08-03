package br.unitins.wbconstrucoes.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


import br.unitins.wbconstrucoes.model.Entity;
import br.unitins.wbconstrucoes.model.ItemVenda;
import br.unitins.wbconstrucoes.model.Venda;

public class VendaDao extends DAO<Venda>{

	@Override
	public boolean create(Venda venda) {
		/*Criando um conexão e criando um variavel booleana que vai ser usado como retorno do metodo/ 
		 * após passando os elementos dos campos onde serão inseridos para um StringBuffer 
		 * Criando um PreparedStatement que vai passar os valores que possuem o sinal -> [?] presenta na instancia sql do StringerBiffer*/
		Connection conn = getConnetion();
		boolean retorno = false;

		StringBuffer sql= new StringBuffer();
		sql.append("INSERT INTO public.venda");
		sql.append(" (data, idusuario) ");
		sql.append("VALUES ");
		sql.append(" (?, ?) " );

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			stat.setDate(1, Date.valueOf(venda.getData()));
			stat.setInt(2, venda.getUsuario().getId());
			stat.execute();
			/*Obter o id da venda vindo da propria tabela através ResultSet e passando para o atributo ID na class venda */
			ResultSet rs = stat.getGeneratedKeys();
			rs.next();
			venda.setId(rs.getInt("id"));

			ItemVendaDao dao = new ItemVendaDao();
			for (ItemVenda itemVenda : venda.getListaItemVenda()) {
				itemVenda.setVenda(venda);
				if (createItemVenda(itemVenda, conn) == false) {
					throw new Exception("Erro ao incluir um item de venda");
				}
			}
			conn.commit();
			System.out.println("Inclusão realizada com sucesso.");
			retorno = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
			closeConnection(conn);
		}
		return retorno;	
	}
	
	private boolean createItemVenda(ItemVenda itemVenda, Connection conn) {

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


			retorno = true;
		} catch (SQLException e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			closeStatement(stat);
		}
		return retorno;	
	}

	@Override
	public boolean update(Venda entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Venda> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Venda findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}