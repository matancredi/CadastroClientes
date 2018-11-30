package br.com.fatec.eng3.web.controle.comandos;

import java.sql.SQLException;

import br.com.fatec.eng3.model.EntidadeModelo;

public class ListarComando extends AbstractComando {

	@Override
	public Resultado executar(EntidadeModelo entidade) {
		
		try {
			return fachada.listar(entidade);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}

}
