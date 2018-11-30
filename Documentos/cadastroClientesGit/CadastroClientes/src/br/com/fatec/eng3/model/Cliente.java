package br.com.fatec.eng3.model;

import java.util.Date;
import java.util.List;

import br.com.fatec.eng3.enums.Genero;

public class Cliente extends EntidadeModelo {
	
	private Integer id;
	private String nome;
	private Date dataNascimento;
	private String cpf;
	private String ddd;
	private String telefone;
	private Integer ranking;
	private Endereco EnderecoCobranca;
	private Endereco EnderecoEntrega;
	private Genero genero;
	private List<CartaoCredito> cartoes;
	
	public Cliente() {}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDDD() {
		return ddd;
	}

	public void setDDD(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public Endereco getEnderecoCobranca() {
		return EnderecoCobranca;
	}

	public void setEnderecoCobranca(Endereco enderecoCobranca) {
		EnderecoCobranca = enderecoCobranca;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}


	public Endereco getEnderecoEntrega() {
		return EnderecoEntrega;
	}


	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		EnderecoEntrega = enderecoEntrega;
	}


	public List<CartaoCredito> getCartoes() {
		return cartoes;
	}


	public void setCartoes(List<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}
	
	

}
