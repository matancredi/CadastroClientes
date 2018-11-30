package br.com.fatec.eng3.web.controle.comandos;

import java.sql.SQLException;

import br.com.fatec.eng3.model.EntidadeModelo;

public class ExcluirComando extends AbstractComando {

	@Override
	public Resultado executar(EntidadeModelo entidade) {
		
		return fachada.excluir(entidade);
		
	}

}
