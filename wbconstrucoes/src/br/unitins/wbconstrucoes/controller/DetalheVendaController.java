package br.unitins.wbconstrucoes.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.dao.VendaDao;
import br.unitins.wbconstrucoes.model.ItemVenda;
import br.unitins.wbconstrucoes.model.Venda;

@Named
@ViewScoped
public class DetalheVendaController implements Serializable {
	private static final long serialVersionUID = 7339239483626065852L;
	private Venda venda;
	
	public DetalheVendaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("detalhes");
		venda = (Venda) flash.get("detalhes");
	}
	
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
}
