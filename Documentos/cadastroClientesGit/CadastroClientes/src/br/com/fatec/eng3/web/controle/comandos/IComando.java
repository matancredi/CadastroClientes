package br.com.fatec.eng3.web.controle.comandos;

import br.com.fatec.eng3.model.EntidadeModelo;

public interface IComando {
	
	public Resultado executar(EntidadeModelo entidade);

}
