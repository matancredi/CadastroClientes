package br.com.fatec.eng3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.TipoLogradouro;
import br.com.fatec.eng3.model.TipoResidencia;
import br.com.fatec.eng3.util.Conexao;

public class TipoLogradouroDAO implements IDAO{
	
public TipoLogradouro buscar(Connection con,Integer id) throws SQLException, ClassNotFoundException {
		
		TipoLogradouro tipoLogradouro = null;
		
		PreparedStatement smtp = con.prepareStatement("SELECT * FROM tipo_logradouro where id = ?");
		smtp.setInt(1, id);
		
		ResultSet resultSet = smtp.executeQuery();
		
		if(resultSet.next()) {
			tipoLogradouro = new TipoLogradouro();
			tipoLogradouro.setId(resultSet.getInt("id"));
			tipoLogradouro.setTipo(resultSet.getString("tipo"));
			tipoLogradouro.setAtv(BooleanUtils.toBooleanObject(resultSet.getInt("atv")));
			
		}
		
		resultSet.close();
		smtp.close();
		return tipoLogradouro;
	}

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
public List<EntidadeModelo> buscarTodos(Connection connection,EntidadeModelo entidade) throws SQLException, ClassNotFoundException {
	
	Connection con = Conexao.getConnection();
	List<EntidadeModelo> lista = new ArrayList<>();
	TipoLogradouro tipoLogradouro = null;
	
	String sql = "SELECT * FROM tipo_logradouro";
	int posicaoBusca = 1;
	
	if(entidade != null) {
		
		sql += " WHERE ";
		
		
		tipoLogradouro = (TipoLogradouro) entidade;
		
		if(tipoLogradouro.getTipo() != null && !tipoLogradouro.getTipo().isEmpty() ) {
			
			sql += " tipo = ? ";
		}
		
	}
	
	sql += " ORDER BY tipo ";
	
	PreparedStatement smtp = con.prepareStatement(sql);
	if(entidade != null) {
		
		if(tipoLogradouro.getTipo() != null && !tipoLogradouro.getTipo().isEmpty() ) {
			
			smtp.setString(posicaoBusca, tipoLogradouro.getTipo());
			posicaoBusca++;
		}
		
	}
	
	ResultSet resultSet = smtp.executeQuery();
	
	
	while(resultSet.next()) {
		TipoLogradouro tipo = (TipoLogradouro) this.buscar(con, resultSet.getInt("id"));
		
		if(tipo != null)
			lista.add(tipo);
	}
	
	resultSet.close();
	smtp.close();
	
	return lista;
}

@Override
public Boolean excluir(Connection connection, EntidadeModelo entidade) {
	// TODO Auto-generated method stub
	return null;
}

}
