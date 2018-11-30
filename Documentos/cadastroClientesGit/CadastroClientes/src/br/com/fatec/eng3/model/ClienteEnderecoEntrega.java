package br.com.fatec.eng3.model;

public class ClienteEnderecoEntrega extends EntidadeModelo {
	
	private Cliente cliente;
	private Endereco endereco;
	
	public ClienteEnderecoEntrega() {}

	public ClienteEnderecoEntrega(Cliente cliente, Endereco endereco) {
		super();
		this.cliente = cliente;
		this.endereco = endereco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	

}
