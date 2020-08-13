package br.unitins.wbconstrucoes.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.application.Session;
import br.unitins.wbconstrucoes.dao.UsuarioDao;
import br.unitins.wbconstrucoes.model.Usuario;
@Named
@ViewScoped
public class UsuarioLogadoController extends Controller<Usuario> implements Serializable{


	private static final long serialVersionUID = 7416445568628639416L;
	
	public UsuarioLogadoController() {
		super(new UsuarioDao());
		// TODO Auto-generated constructor stub
	}
	@Override
	public Usuario getEntity() {
		if(entity == null) {
			entity = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
		}	
		return entity;
	}

}
