package br.unitins.wbconstrucoes.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.hibernate.validator.constraints.ISBN;

import br.unitins.wbconstrucoes.application.Util;
import br.unitins.wbconstrucoes.dao.UsuarioDao;
import br.unitins.wbconstrucoes.model.Usuario;
@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;
	private String message;

	public String logar() {
		UsuarioDao dao = new UsuarioDao();
		Usuario usu = dao.verificarLoginSenha(getUsuario().getLogin(), getUsuario().getSenha());

		if(usu != null) {
			return "wbconstrucoes.xhtml?faces-redirect=true";
		}
		message = "Login ou senha inválida!.";
		return message;
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
