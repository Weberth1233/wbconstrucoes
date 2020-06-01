package br.unitins.wbconstrucoes.model;

public enum Sexo {
	SELECIONE_SEXO(0,"Selecione sexo"),
	MASCULINO(1,"Masculino"),
	FEMININO(2, "Feminino");
	
	private String descricao;
	private int id;

	private Sexo(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static Sexo valueOf(int id) {
		for (Sexo sexo : Sexo.values()) {
			if(id == sexo.id) {
				return sexo;
			}
		}
		return null;
	}
}
