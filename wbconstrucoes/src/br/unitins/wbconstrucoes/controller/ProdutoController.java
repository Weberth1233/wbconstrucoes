package br.unitins.wbconstrucoes.controller;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.unitins.wbconstrucoes.dao.ProdutoDao;
import br.unitins.wbconstrucoes.model.Produto;

@Named
@ViewScoped
public class ProdutoController extends Controller<Produto> {

	private static final long serialVersionUID = -6651018793228213955L;
	private List<Produto> listaProdutos;
	
	public ProdutoController() {
		super(new ProdutoDao());
		Flash flash = FacesContext.getCurrentInstance().
				getExternalContext().getFlash();
		flash.keep("flashproduto");
		entity = (Produto) flash.get("flashproduto");
	}
	
	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	@Override
	public Produto getEntity() {
		if(entity == null) 
			entity = new Produto();
		return entity;
	}
}
