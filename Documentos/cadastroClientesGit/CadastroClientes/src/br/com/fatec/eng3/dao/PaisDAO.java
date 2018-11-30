package br.com.fatec.eng3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.Pais;
import br.com.fatec.eng3.util.Conexao;

public class PaisDAO implements IDAO {

	@Override
	public Boolean salvar(Connection con,EntidadeModelo entidade) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean alterar(Connection con,EntidadeModelo entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pais buscar(Connection con,Integer id) throws SQLException, ClassNotFoundException {
		
		Pais pais =	null;
		
		PreparedStatement smtp = con.prepareStatement("SELECT * FROM pais WHERE id = ?");
		smtp.setInt(1, id);
		
		ResultSet resultSet = smtp.executeQuery();
		
		if(resultSet.next()) {
			pais = new Pais();
			
			pais.setCodigoPais(resultSet.getInt("id"));
			pais.setPais(resultSet.getString("pais"));
			
		}
		
		resultSet.close();
		smtp.close();
		return pais;
		
	}

	@Override
	public List<EntidadeModelo> buscarTodos(Connection con, EntidadeModelo entidade) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean excluir(Connection connection, EntidadeModelo entidade) {
		// TODO Auto-generated method stub
		return null;
	}
}
