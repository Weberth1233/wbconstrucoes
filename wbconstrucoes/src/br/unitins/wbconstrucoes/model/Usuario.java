package br.unitins.wbconstrucoes.model;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

public class Usuario extends Entity<Usuario>{
	@NotBlank(message = "Campo nome deve ser informado..")
	private String nome;
	
	@Past(message = "Data n�o pode estar no futuro")
	private LocalDate dataNascimento;
	
	@NotBlank(message="Login n�o pode ser vazio..")
	private String login;
	
	private String senha;
	
	private Sexo sexo;
	
	@NotBlank(message = "CPF n�o pode ser vazio..")
	@CPF(message = "CPF inv�lido ")
	private String cpf;
	
	@Email(message = "Email inv�lido")
	private String email;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
