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

	public String novoProduto() {
		return "produtocad.xhtml?faces-redirect=true";
	}
	public void pesquisar() {
		ProdutoDao dao = new ProdutoDao();
		listaProduto = dao.getFiltroCategoria(filtro);
	}
	
	public String editar(Produto produto) {
		ProdutoDao dao= new ProdutoDao();
		produto = dao.findById(produto.getId());
		
		Flash flash = FacesContext.getCurrentInstance().
						getExternalContext().getFlash();
		
		flash.put("flashproduto", produto);
		return "produtocad.xhtml?faces-redirect=true";
	}
	
	public List<Produto> getListaProduto() {
		if (listaProduto == null) {
			listaProduto = new ArrayList<Produto>();
		}
		return listaProduto;
	}
	
	public String getFiltro() {
		/*Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("chave");
		filtro = (String) flash.get("chave");*/
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
}
