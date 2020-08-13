package br.unitins.wbconstrucoes.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.dao.DAO;
import br.unitins.wbconstrucoes.dao.ProdutoDao;
import br.unitins.wbconstrucoes.dao.TelefoneDao;
import br.unitins.wbconstrucoes.model.Telefone;
import br.unitins.wbconstrucoes.model.Usuario;

@Named
@ViewScoped
public class TelefoneController extends Controller<Telefone> implements Serializable {
	
	private static final long serialVersionUID = 5981413424714276613L;

	public TelefoneController() {
		super(new TelefoneDao());
	}

	@Override
	public Telefone getEntity() {
		if(entity == null) {
			entity = new Telefone();
		}
		return entity;
	}
	

}
