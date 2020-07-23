package br.unitins.wbconstrucoes.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.application.Session;
import br.unitins.wbconstrucoes.model.Usuario;
@Named
@ViewScoped
public class TemplateController implements Serializable{
	
	private static final long serialVersionUID = -3625157056539958909L;
	private Usuario usuarioLogado = null;
	
	public Usuario getUsuarioLogado() {
		if(usuarioLogado == null) 
			usuarioLogado = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String encerrarSessao() {
		Session.getInstance().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}
}
