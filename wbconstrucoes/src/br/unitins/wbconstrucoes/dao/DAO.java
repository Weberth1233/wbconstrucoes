package br.unitins.wbconstrucoes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public  abstract class DAO<T>{
	public abstract boolean create(T entity);
	public abstract boolean update(T entity);
	public abstract boolean delete(int id);
	public abstract List<T>findAll();
	public abstract T findById(int id);
	
	public static Connection getConnetion() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/booksdb\", \"topicos1\", \"123456");
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}