
package br.unitins.wbconstrucoes.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.application.Session;
import br.unitins.wbconstrucoes.application.Util;
import br.unitins.wbconstrucoes.dao.DAO;
import br.unitins.wbconstrucoes.dao.UsuarioDao;
import br.unitins.wbconstrucoes.model.Entity;
import br.unitins.wbconstrucoes.model.Sexo;
import br.unitins.wbconstrucoes.model.TipoUsuario;
import br.unitins.wbconstrucoes.model.Usuario;
@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> implements Serializable {

	private static final long serialVersionUID = 4229379253264816939L;
	
	public UsuarioController() {
		super(new UsuarioDao());
	}
	
	@Override
	public Usuario getEntity() {
		if(entity == null) {
			entity = new Usuario();
		}	
		return entity;
	}
	
	public TipoUsuario[] getListaTipoUsuario() {
		return TipoUsuario.values();
	}
	
	public Sexo[] getlistaSexo() {
		return Sexo.values();
	}
	@Override
	public boolean validar() {
		if(getEntity().getNome().isBlank()) {
			Util.addErrorMessage("Campod nome deve ser informado!.");
			return false;
		}
		String senha = Util.hashSHA256(getEntity().getSenha());
		getEntity().setSenha(senha);
		return true;
	}
}