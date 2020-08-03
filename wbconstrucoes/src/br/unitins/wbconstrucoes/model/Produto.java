package br.unitins.wbconstrucoes.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Produto extends Entity<Produto>{
	
	private static final long serialVersionUID = -658538154954676024L;
	@NotBlank(message ="Descrição deve ser informado!")
	private String descricao;
	@Max(value = (long) 999.99, message = "Valor deve ser menor que 999!")
	@Min(value = 1)
	private Double valor;
	private Integer qtdEstoque;
	@NotBlank(message ="Categoria deve ser informado!")
	private String categoria;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
