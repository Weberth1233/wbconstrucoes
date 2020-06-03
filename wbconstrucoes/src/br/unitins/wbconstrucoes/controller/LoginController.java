package br.unitins.wbconstrucoes.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.dao.UsuarioDao;
import br.unitins.wbconstrucoes.model.Usuario;
@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;
	
	public String logar() {
		UsuarioDao dao = new UsuarioDao();
		Usuario usu = dao.verificarLoginSenha(getUsuario().getLogin(), getUsuario().getSenha());
		
		if(usu != null) 
			return "wbconstrucoes.xhtml?faces-redirect=true";
		System.out.println("Informações invalidas");
		return " ";
	}
	public void limpar() {
		usuario = null;
	}
	
	public Usuario getUsuario() {
		if(usuario == null) 
			usuario = new Usuario();
		return usuario;
	}
	public void setUsuario(Usuario usu) {
		this.usuario = usu;
	}
}
