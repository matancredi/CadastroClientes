package br.com.fatec.eng3.web.controle.comandos;

import br.com.fatec.eng3.web.controle.Fachada;
import br.com.fatec.eng3.web.controle.IFachada;

public abstract class AbstractComando implements IComando {
	
	protected IFachada fachada = new Fachada();

}
