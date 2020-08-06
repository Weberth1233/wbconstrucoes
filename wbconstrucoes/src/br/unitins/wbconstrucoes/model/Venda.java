package br.unitins.wbconstrucoes.model;

import java.time.LocalDate;
import java.util.List;

public class Venda extends Entity<Venda>{

	private static final long serialVersionUID = 6821712939595036698L;
	private LocalDate data;
	private Usuario usuario;
	private List<ItemVenda> ListaItemVenda;
	private Double totalVenda = 0.0;
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<ItemVenda> getListaItemVenda() {
		return ListaItemVenda;
	}
	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		ListaItemVenda = listaItemVenda;
	}
	public Double getTotalVenda() {
		return totalVenda;
	}
	public void setTotalVenda(Double totalVenda) {
		this.totalVenda = totalVenda;
	}
	
}
