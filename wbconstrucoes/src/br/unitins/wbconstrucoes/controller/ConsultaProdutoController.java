package br.unitins.wbconstrucoes.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.dao.ProdutoDao;
import br.unitins.wbconstrucoes.model.Produto;

@Named
@ViewScoped
public class ConsultaProdutoController implements Serializable {
	
	private static final long serialVersionUID = -7823377079644945962L;
	private String filtro;
	private List<Produto> listaProduto;
	
	public void pesquisar() {
		ProdutoDao dao = new ProdutoDao();
		listaProduto = dao.getFiltroCategoria(getFiltro());
	}
	
	public String novoProduto() {
		return "produtos.xhtml?faces-redirect=true";
	}
	
	public String editar(Produto produto) {
		ProdutoDao dao = new ProdutoDao();
		produto = dao.findById(produto.getId());
		
		Flash flash = FacesContext.getCurrentInstance().
						getExternalContext().getFlash();
		
		flash.put("flashProduto", produto);
		return "produtos.xhtml?faces-redirect=true";
	}

	public List<Produto> getListaProduto() {
		if (listaProduto == null) {
			listaProduto = new ArrayList<Produto>();
		}
		return listaProduto;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
}
