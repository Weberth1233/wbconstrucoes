package br.unitins.wbconstrucoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.unitins.wbconstrucoes.model.Telefone;

public class TelefoneDao extends DAO<Telefone> {

	@Override
	public boolean create(Telefone entity) {
		Connection conn = getConnetion();
		boolean retorno = false;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO public.telefone ");
		sql.append("	(idusuario, tipotelefone, numero) ");
		sql.append("VALUES ");
		sql.append("	(?, ?, ?) ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			stat.setInt(1, entity.getUsuario().getId());
			stat.setString(2, entity.getTipoTelefone());
			stat.setString(3, entity.getNumero());
			stat.execute();
			
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

	@Override
	public boolean update(Telefone entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Telefone> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Telefone findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}