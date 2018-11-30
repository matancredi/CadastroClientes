package br.com.fatec.eng3.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.util.Conexao;

public interface IDAO {
	
	public Boolean salvar(Connection connection, EntidadeModelo entidade) throws  SQLException, ClassNotFoundException;
	public Boolean alterar(Connection connection,EntidadeModelo entidade);
	public Boolean excluir(Connection connection,EntidadeModelo entidade);
	public EntidadeModelo buscar(Connection connection,Integer id) throws  SQLException, ClassNotFoundException;
	public List<? extends EntidadeModelo> buscarTodos(Connection connection,EntidadeModelo entidade) throws SQLException, ClassNotFoundException;

}
