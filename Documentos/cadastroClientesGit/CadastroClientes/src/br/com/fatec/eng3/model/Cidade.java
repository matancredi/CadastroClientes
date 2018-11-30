package br.com.fatec.eng3.model;

public class Cidade extends EntidadeModelo{
	
	private Integer id;
	private String cidade;
	private Estado estado;
	
	public Integer getId() {
		return id;
	}
	public void setCodigoCidade(Integer id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setNome(String cidade) {
		this.cidade = cidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	

}
