package br.com.fatec.eng3.web.controle;

import java.sql.SQLException;
import java.util.List;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.web.controle.comandos.Resultado;

public interface IFachada {
	
	public Resultado salvar(EntidadeModelo entidade) throws ClassNotFoundException, SQLException;
	public Resultado inserir(EntidadeModelo entidade);
	public Resultado alterar(EntidadeModelo entidade);
	public Resultado excluir(EntidadeModelo entidade);
	public Resultado consultar(EntidadeModelo entidade) throws SQLException, ClassNotFoundException;
	public Resultado listar(EntidadeModelo entidade) throws SQLException, ClassNotFoundException;
}
