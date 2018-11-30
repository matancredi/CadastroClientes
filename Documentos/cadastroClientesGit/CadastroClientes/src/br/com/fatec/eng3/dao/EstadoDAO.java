package br.com.fatec.eng3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.Cliente;
import br.com.fatec.eng3.model.Estado;
import br.com.fatec.eng3.model.Pais;
import br.com.fatec.eng3.util.Conexao;

public class EstadoDAO  implements IDAO{

	@Override
	public Boolean salvar(Connection connection,EntidadeModelo entidade) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean alterar(Connection connection,EntidadeModelo entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<EntidadeModelo> buscarTodos(Connection con,EntidadeModelo entidade) throws SQLException, ClassNotFoundException {
		
		List<EntidadeModelo> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM estados";
	
		PreparedStatement smtp = con.prepareStatement(sql);
		ResultSet resultSet = smtp.executeQuery();
		
		while(resultSet.next()) {
			Estado estado = this.buscar(con, resultSet.getInt("id"));
			
			if(estado != null)
				lista.add(estado);
		}
		
		resultSet.close();
		smtp.close();
		
		return lista;
	}

	@Override
	public Estado buscar(Connection con,Integer id) throws SQLException, ClassNotFoundException {
		
		Estado estado = null;
		
		PreparedStatement smtp =  con.prepareStatement("SELECT * FROM estados WHERE id = ?");
		smtp.setInt(1, id);
		
		ResultSet resutlSet = smtp.executeQuery();
		
		if(resutlSet.next()) {
			estado = new Estado();
			
			Pais pais = (new PaisDAO()).buscar(con, resutlSet.getInt("pais"));
			estado.setPais(pais);
			estado.setNome(resutlSet.getString("estado"));
			estado.setId(resutlSet.getInt("id"));
		}
		
		resutlSet.close();
		smtp.close();
		return estado;
	}

	@Override
	public Boolean excluir(Connection connection, EntidadeModelo entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
