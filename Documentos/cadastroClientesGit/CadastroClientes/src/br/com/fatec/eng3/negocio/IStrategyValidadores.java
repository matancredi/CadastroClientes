package br.com.fatec.eng3.negocio;

import br.com.fatec.eng3.model.EntidadeModelo;

public interface IStrategyValidadores {
	 
	public Boolean processar(EntidadeModelo entidade);
	 
}
