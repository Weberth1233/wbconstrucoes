package br.unitins.wbconstrucoes.model;

public class Telefone extends Entity<Telefone> {

	private static final long serialVersionUID = -5791362694758887083L;
	
	private Usuario usuario;
	private String tipoTelefone;
	private String numero;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getTipoTelefone() {
		return tipoTelefone;
	}
	public void setTipoTelefone(String tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
