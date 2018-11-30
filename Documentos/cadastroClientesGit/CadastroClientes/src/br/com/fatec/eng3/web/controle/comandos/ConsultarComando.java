package br.com.fatec.eng3.web.controle.comandos;

import java.sql.SQLException;

import br.com.fatec.eng3.model.EntidadeModelo;

public class ConsultarComando extends AbstractComando {

	@Override
	public Resultado executar(EntidadeModelo entidade) {
		
		try {
			return fachada.consultar(entidade);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
