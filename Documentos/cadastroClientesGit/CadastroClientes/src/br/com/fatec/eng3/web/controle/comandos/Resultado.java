package br.com.fatec.eng3.web.controle.comandos;

import java.util.List;

import br.com.fatec.eng3.model.EntidadeModelo;

public class Resultado {
	
	private String msg;
	private Boolean status;
	private EntidadeModelo entidade;
	private List<EntidadeModelo> listaEntidades;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public EntidadeModelo getEntidade() {
		return entidade;
	}
	public void setEntidade(EntidadeModelo entidade) {
		this.entidade = entidade;
	}
	public List<EntidadeModelo> getListaEntidades() {
		return listaEntidades;
	}
	public void setListaEntidades(List<EntidadeModelo> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}
	

}
