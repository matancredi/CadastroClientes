package br.com.fatec.eng3.web.controle.comandos;

import br.com.fatec.eng3.model.EntidadeModelo;

public class AlterarComando extends AbstractComando {

	@Override
	public Resultado executar(EntidadeModelo entidade) {
		
		return fachada.alterar(entidade);
	}

}
