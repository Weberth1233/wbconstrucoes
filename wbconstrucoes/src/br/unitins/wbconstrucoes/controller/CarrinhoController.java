package br.unitins.wbconstrucoes.controller;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.wbconstrucoes.application.Session;
import br.unitins.wbconstrucoes.application.Util;
import br.unitins.wbconstrucoes.dao.VendaDao;
import br.unitins.wbconstrucoes.model.ItemVenda;
import br.unitins.wbconstrucoes.model.Usuario;
import br.unitins.wbconstrucoes.model.Venda;

@Named
@ViewScoped
public class CarrinhoController implements Serializable {
	private static final long serialVersionUID = -1950908042294874008L;
	
	private Venda venda;

	public Venda getVenda() {
		/*Instanciar uma venda
		 * Pegar a sessão com o carrinho 
		 * verificar se o carrinho e null e criar uma instacia para ele.
		 * Passar para a sessão os itens vindo do carrinho para a lista de vendas*/
		
		if(venda == null) 
			venda = new Venda();
			
			List<ItemVenda>carrinho = 
					(ArrayList<ItemVenda>)Session.getInstance().getAttribute("carrinho");
			
			if(carrinho == null) {
				carrinho = new ArrayList<ItemVenda>();
			}
			venda.setListaItemVenda(carrinho);
			
		return venda;
	}
	
	public void remover(int idProduto) {
		Session.getInstance().setAttribute(idProduto, null);
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public void finalizar() {
		/*Verificar se o usuario está logado
		 * Intanciar uma venda e passar os valores
		 * pegar o carrinho da sessão e passar ele para a lista de vendas 
		 * salvar no banco as informação e limpar o carrinho*/
		Usuario usuario = null;
		usuario = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
		if(usuario == null) {
			Util.addErrorMessage("Usuario não está logado, não é possivel finalizar venda!");
			return;
		}
		Venda venda= new Venda();
		venda.setData(LocalDate.now());
		venda.setUsuario(usuario);
		List<ItemVenda>carrinho = (ArrayList<ItemVenda>)Session.getInstance().getAttribute("carrinho");
		venda.setListaItemVenda(carrinho);
		
		VendaDao dao = new VendaDao();
		if(dao.create(venda)) {
			Util.addInfoMessage("Compra finalizada com sucesso!");
			Session.getInstance().setAttribute("carrinho", null);
		}else {
			Util.addErrorMessage("Ouve algum erro durante a compra!");
		}
	}
}
