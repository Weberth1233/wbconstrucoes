package br.unitins.wbconstrucoes.model;

import javax.validation.constraints.NotBlank;

public class Produto extends Entity<Produto>{
	@NotBlank(message = "Descrição não deve ser nula")
	private String descricao;
	private float valor;
	private int qtdEstoque;
	private String categoria;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
