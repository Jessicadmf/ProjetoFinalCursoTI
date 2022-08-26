package br.com.devti.gestaopedidodefarmacia.core.entity;


public class AtendenteEntity{
	
	private Long idAtendente;
	private String nomeAtendente;
	private String cargo;
	private String emailAtendente;
	private String cpf;

	
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmailAtendente() {
		return emailAtendente;
	}
	public void setEmailAtendente(String emailAtendente) {
		this.emailAtendente = emailAtendente;
	}
	
	public Long getIdAtendente() {
		return idAtendente;
	}
	public void setIdAtendente(Long idAtendente) {
		this.idAtendente = idAtendente;
	}
	public String getNomeAtendente() {
		return nomeAtendente;
	}
	public void setNomeAtendente(String nomeAtendente) {
		this.nomeAtendente = nomeAtendente;
	}
	
	
	
}
