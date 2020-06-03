package br.unitins.wbconstrucoes.controller;

import java.io.Serializable;

import br.unitins.wbconstrucoes.dao.DAO;
import br.unitins.wbconstrucoes.model.Entity;

public abstract class Controller<T extends Entity<T>> implements Serializable{
	private static final long serialVersionUID = 5858410398146195215L;
	
	protected T entity = null;
	protected DAO<T>dao = null;
	
	/*Contrutor do controller recebe um dao como parametro e o atributo dao a este.. */
	public Controller(DAO<T>dao) {
		super();
		this.dao = dao;
	}
	public abstract T getEntity();
	
	public void setEntity(T entity) {
		this.entity = entity;
	}
	public void adicionar() {
		if(dao.create(getEntity())) {
			limpar();
			System.out.println("Inserido no banco com sucesso");
		}else {
			System.out.println("Não foi possivel");
		}
	}
	public void atualizar() {
		if(dao.update(getEntity())) {
			limpar();
			System.out.println("Atualização feita com sucesso");
		}else {
			System.out.println("Erro na Atualização");
		}
	}
	public void editar(T entity) {
		entity = dao.findById(entity.getId());
		setEntity(entity);
	}
	public void deletar() {
		if(dao.delete(getEntity().getId())){
			limpar();
			System.out.println("Deletado com sucesso");
		}else {
			System.out.println("Erro na deletação");
		}
	}
	public void limpar() {
		entity = null;
	}
}
