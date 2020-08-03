package br.unitins.wbconstrucoes.model;

public class ItemVenda extends Entity<ItemVenda> {
	private static final long serialVersionUID = -1289777749233578680L;
	
	private Produto produto;
	private Double valor;
	private Venda venda;
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
}
