package br.unitins.wbconstrucoes.controller;

import java.io.Serializable;

import br.unitins.wbconstrucoes.application.Util;
import br.unitins.wbconstrucoes.dao.DAO;
import br.unitins.wbconstrucoes.dao.TelefoneDao;
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
		if(validar()) {
			if(dao.create(getEntity())) {
				limpar();
				Util.addInfoMessage("Cadastro realizado com sucesso!");
			}else {
				Util.addErrorMessage("Erro ao inserir no banco de dados!");
			}
		}
	}
	public void atualizar() {
		if(validar()) {
			if(dao.update(getEntity())) {
				limpar();
				Util.addInfoMessage("Atualização realizado com sucesso!");
			}else {
				Util.addErrorMessage("Erro ao atualizar informações!");
			}
		}
	}
	public void editar(T entity) {
		entity = dao.findById(entity.getId());
		setEntity(entity);
	}
	public void deletar() {
		if(dao.delete(getEntity().getId())){
			limpar();
			Util.addInfoMessage("Deletado com sucesso!");
		}else {
			Util.addErrorMessage("Erro ao deletar informações!");
		}
	}
	public boolean validar() {
		return true;
	}
	public void limpar() {
		entity = null;
	}
}
