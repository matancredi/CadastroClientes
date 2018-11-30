package br.com.fatec.eng3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.ClienteEnderecoEntrega;
import br.com.fatec.eng3.model.Endereco;
import br.com.fatec.eng3.model.TipoResidencia;
import br.com.fatec.eng3.util.Conexao;

public class ClienteEnderecoEntregaDAO implements IDAO {
	
	
	public Boolean salvar(Connection connection,EntidadeModelo cli_end) throws SQLException, ClassNotFoundException {
		
		ClienteEnderecoEntrega cliente_endereco = (ClienteEnderecoEntrega) cli_end;
		
		PreparedStatement smtp = connection.prepareStatement("INSERT INTO cliente_enderecoentrega VALUES(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		smtp.setInt(1, cliente_endereco.getCliente().getId());
		smtp.setInt(2, cliente_endereco.getEndereco().getId());
		
		int executeUpdate = smtp.executeUpdate();
		
		if(executeUpdate < 1) {
			
			return false;
		}
		
		return true;
		
	}
	
	

	@Override
	public Boolean alterar(Connection connection,EntidadeModelo entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntidadeModelo buscar(Connection connection,Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List buscarTodos(Connection connection,EntidadeModelo entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Boolean excluir(Connection connection, EntidadeModelo entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
