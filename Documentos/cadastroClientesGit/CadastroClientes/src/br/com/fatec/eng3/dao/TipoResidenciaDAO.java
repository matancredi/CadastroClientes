package br.com.fatec.eng3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.Cidade;
import br.com.fatec.eng3.model.Cliente;
import br.com.fatec.eng3.model.TipoLogradouro;
import br.com.fatec.eng3.model.TipoResidencia;
import br.com.fatec.eng3.util.Conexao;

public class TipoResidenciaDAO implements IDAO {
	
	public TipoResidencia buscar(Connection con,Integer id) throws SQLException, ClassNotFoundException {
		
		TipoResidencia tipoResidencia = null;
		
		PreparedStatement smtp = con.prepareStatement("SELECT * FROM tipo_residencia where id = ?");
		smtp.setInt(1, id);
		
		ResultSet resultSet = smtp.executeQuery();
		
		if(resultSet.next()) {
			tipoResidencia = new TipoResidencia();
			tipoResidencia.setId(resultSet.getInt("id"));
			tipoResidencia.setTipo(resultSet.getString("tipo"));
			tipoResidencia.setAtv(BooleanUtils.toBooleanObject(resultSet.getInt("atv")));
			
		}
		
		resultSet.close();
		smtp.close();
		
		return tipoResidencia;
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
	public List<EntidadeModelo> buscarTodos(Connection con ,EntidadeModelo entidade) throws SQLException, ClassNotFoundException {
		
		List<EntidadeModelo> lista = new ArrayList<>();
		TipoResidencia tipoResidencia = null;
		
		String sql = "SELECT * FROM tipo_residencia";
		int posicaoBusca = 1;
		
		if(entidade != null) {
			
			sql += " WHERE ";
			
			
			tipoResidencia = (TipoResidencia) entidade;
			
			if(tipoResidencia.getTipo() != null && !tipoResidencia.getTipo().isEmpty() ) {
				
				sql += " tipo = ? ";
			}
			
			
		}
		
		sql += " ORDER BY tipo ";
		
		PreparedStatement smtp = con.prepareStatement(sql);
		if(entidade != null) {
			
			if(tipoResidencia.getTipo() != null && !tipoResidencia.getTipo().isEmpty() ) {
				
				smtp.setString(posicaoBusca, tipoResidencia.getTipo());
				posicaoBusca++;
			}
			
		}
		
		ResultSet resultSet = smtp.executeQuery();
		
		while(resultSet.next()) {
			TipoResidencia tipo = (TipoResidencia) this.buscar(con, resultSet.getInt("id"));
			
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
