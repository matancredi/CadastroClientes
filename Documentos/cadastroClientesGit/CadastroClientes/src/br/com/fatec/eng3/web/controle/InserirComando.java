package br.com.fatec.eng3.web.controle;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.web.controle.comandos.AbstractComando;
import br.com.fatec.eng3.web.controle.comandos.IComando;
import br.com.fatec.eng3.web.controle.comandos.Resultado;

public class InserirComando extends AbstractComando {

	@Override
	public Resultado executar(EntidadeModelo entidade) {
		return fachada.inserir(entidade);
	}

}
