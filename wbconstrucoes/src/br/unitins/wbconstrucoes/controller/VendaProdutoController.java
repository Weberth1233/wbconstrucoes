package br.unitins.wbconstrucoes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.application.Session;
import br.unitins.wbconstrucoes.application.Util;
import br.unitins.wbconstrucoes.dao.ProdutoDao;
import br.unitins.wbconstrucoes.model.ItemVenda;
import br.unitins.wbconstrucoes.model.Produto;
import br.unitins.wbconstrucoes.model.Venda;
@Named
@ViewScoped

public class VendaProdutoController implements Serializable {
	private static final long serialVersionUID = 5551767443156564360L;
	private String categoria;
	private List<Produto>listaProduto = null;
	private Integer qtdProAdc = 0;
	
	public void pesquisar() {
		listaProduto = null;
	}
	public void adicionar(int idProduto) {
		/* 1- Passar o idProduto no parametro do metodo para o findById do Dao do produto*/
		ProdutoDao dao = new ProdutoDao();
		Produto produto = dao.findById(idProduto);
		/*2 - Verificar na sessão se existe um carrinho caso o carrinho seja null eu pego 
		 * a sessão carrinho e passo a key e a lista de item para que assim ela seja instanciada e não ocorra um erro
		 * na lista */
			if(Session.getInstance().getAttribute("carrinho") == null) {
				Session.getInstance().setAttribute("carrinho", new ArrayList<ItemVenda>());
			}
			/*Obtendo o carrinho da sessão e atribuindo a uma lista de itens so espaco valor da minha sessão*/
			List<ItemVenda> carrinho = (ArrayList<ItemVenda>) Session.getInstance().getAttribute("carrinho");
			
			/*Intanciando uma lista e passando seus relaciomentos e adicionado itens no carrinho*/
			ItemVenda item = new ItemVenda();
			item.setProduto(produto);
			item.setValor(produto.getValor());
			
			Venda venda= new Venda();
			venda.setTotalVenda(item.getValor());
			carrinho.add(item);
			/*Atualizando carrinho na sessão*/
			Session.getInstance().setAttribute("carrinho", carrinho);
			
			this.setQtdProAdc(carrinho.size());
			Util.addInfoMessage("O carrinho possui: " +carrinho.size()+" produtos adicionados");
	}
	public String getCategoria() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("chave");
		categoria = (String) flash.get("chave");
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public List<Produto> getListaProduto() {
		if(listaProduto == null) {
			ProdutoDao dao = new ProdutoDao();
			listaProduto = dao.getFiltroCategoria(getCategoria());
			
			if(listaProduto == null)
				listaProduto = new ArrayList<Produto>();
		}
		return listaProduto;
	}
	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
	
	public Integer getQtdProAdc() {
		return qtdProAdc;
	}
	public void setQtdProAdc(Integer qtdProAdc) {
		this.qtdProAdc = qtdProAdc;
	}
}
