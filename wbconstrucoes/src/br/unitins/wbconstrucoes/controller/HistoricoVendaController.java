package br.unitins.wbconstrucoes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.application.Session;
import br.unitins.wbconstrucoes.dao.VendaDao;
import br.unitins.wbconstrucoes.model.Usuario;
import br.unitins.wbconstrucoes.model.Venda;



@Named
@ViewScoped
public class HistoricoVendaController implements Serializable {
	
	private static final long serialVersionUID = 3389511082730634697L;
	private List<Venda>listaVenda;
	
	public List<Venda> getListaVenda() {
		if(listaVenda == null) {
			VendaDao dao = new VendaDao();
			Usuario usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
			listaVenda = dao.findByUsuario(usuario.getId());
			if(listaVenda == null) 
				listaVenda= new ArrayList<Venda>();
		}
		return listaVenda;
	}
	
	public String detalhes(Venda venda) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("detalhes", venda);
		
		return "detalhesvenda.xhtml?faces-redirect=true";
		
	}
	
}
