package br.com.fatec.eng3.model;

public class Dependente {
	
	private Integer id;
	private Cliente cliente;
	private Parentesco parentesco;
	
	public Dependente() {
		super();
	}

	public Dependente(Integer id, Cliente cliente, Parentesco parentesco) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.parentesco = parentesco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}
	
	
}
