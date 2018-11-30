package br.com.fatec.eng3.model;

import java.util.Date;

public class CartaoCredito  extends EntidadeModelo {
	
	private Integer id;
	private Cliente cliente;
	private String numCartao;
	private String nomeImpresso;
	private Integer codSeguranca;
	private Date dataVencimento;
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
	public String getNumCartao() {
		return numCartao;
	}
	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}
	public String getNomeImpresso() {
		return nomeImpresso;
	}
	public void setNomeImpresso(String nomeImpresso) {
		this.nomeImpresso = nomeImpresso;
	}
	public Integer getCodSeguranca() {
		return codSeguranca;
	}
	public void setCodSeguranca(Integer codSeguranca) {
		this.codSeguranca = codSeguranca;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	

	
}
