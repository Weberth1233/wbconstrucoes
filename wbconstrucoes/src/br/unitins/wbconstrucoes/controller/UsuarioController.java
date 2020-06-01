package br.unitins.wbconstrucoes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.model.Sexo;
import br.unitins.wbconstrucoes.model.Usuario;
@Named
@ViewScoped
public class UsuarioController implements Serializable {
	
	private static final long serialVersionUID = 4229379253264816939L;
	
	private Usuario usu;
	private List<Usuario>lista;
	
	public void adicionar() {
		lista.add(usu);
		limpar();
	}
	public void limpar() {
		usu = null;
	}
	public Usuario getUsu() {
		if(usu == null)
			usu = new Usuario();
			return usu;
	}
	public void setUsu(Usuario usu) {
		this.usu = usu;
	}
	public List<Usuario> getLista() {
		if(lista == null)
			lista = new ArrayList<Usuario>();
		return lista;
	}
	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
	public Sexo[] getlistaSexo() {
		return Sexo.values();
	}
}
