package br.com.fatec.eng3.model;

public class Pais extends EntidadeModelo{
	
	private Integer codigoPais;
	private String pais;
	
	public Pais() {}

	public Pais(Integer codigoPais, String pais) {
		super();
		this.codigoPais = codigoPais;
		this.pais = pais;
	}

	public Integer getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Integer codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	

}
