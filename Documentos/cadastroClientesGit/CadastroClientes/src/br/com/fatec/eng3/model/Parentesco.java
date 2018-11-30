package br.com.fatec.eng3.model;

public class Parentesco {
	
	private Integer id;
	private String tipo;
	private Boolean atv;
	
	public Parentesco() {}

	public Parentesco(Integer id, String tipo, Boolean atv) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.atv = atv;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getAtv() {
		return atv;
	}

	public void setAtv(Boolean atv) {
		this.atv = atv;
	}
	

}
