package br.unitins.wbconstrucoes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.dao.DAO;
import br.unitins.wbconstrucoes.dao.ProdutoDao;
import br.unitins.wbconstrucoes.model.Produto;
@Named
@ViewScoped
public class ProdutoController extends Controller<Produto> implements Serializable{
	
	private static final long serialVersionUID = 2779615796542735726L;
	/*Criar a lista dos produtos instanciar o objeto do tipo entity contido em produto*/
	private List<Produto>listaProdutos;
	
	/*Passando um dao como parâmetro para o contrutor do dao que será utilizado em outra classe*/
	public ProdutoController(DAO<Produto> dao) {
		super(new ProdutoDao());
	}
	
	@Override
	public Produto getEntity() {
		if(entity == null) {
			entity = new Produto();
		}
		return entity;
	}
	
	public List<Produto> getListaProdutos() {
		if(listaProdutos == null) {
			listaProdutos= new ArrayList<Produto>();
		}
		return listaProdutos;
	}
	
	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	@Override
	public void limpar() {
		super.limpar();
	}
}
