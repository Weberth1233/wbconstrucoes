package br.unitins.wbconstrucoes.controller;
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

	public ProdutoController() {
		super(new ProdutoDao());
		Flash flash = FacesContext.getCurrentInstance().
				getExternalContext().getFlash();
		flash.keep("flashProduto");
		entity = (Produto) flash.get("flashProduto");
	}
	
	@Override
	public Produto getEntity() {
		if(entity == null) 
			entity = new Produto();
		return entity;
	}
}
