package br.unitins.wbconstrucoes.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.application.Session;
import br.unitins.wbconstrucoes.model.Usuario;
@Named
@ViewScoped
public class TemplateController implements Serializable{

	private static final long serialVersionUID = -3625157056539958909L;
	private Usuario usuarioLogado = null;
	private String auxpesquisa;
	private Integer qtdcarrinho;
	
	public TemplateController() {
		this.qtdcarrinho = 0;
	}
	
	public Usuario getUsuarioLogado() {
		if(usuarioLogado == null) 
			usuarioLogado = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public String getAuxpesquisa() {
		return auxpesquisa;
	}

	public void setAuxpesquisa(String auxpesquisa) {
		this.auxpesquisa = auxpesquisa;
	}
	
	public Integer getQtdcarrinho() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("qtdproduto");
		qtdcarrinho = (int) flash.get("qtdproduto");
		return qtdcarrinho;
	}

	public void setQtdcarrinho(Integer qtdcarrinho) {
		this.qtdcarrinho = qtdcarrinho;
	}

	public String pesquisa() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("chave", auxpesquisa);
		return "venda.xhtml?faces-redirect=true";
	}
	public String encerrarSessao() {
		Session.getInstance().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}
}
