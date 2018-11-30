package br.com.fatec.eng3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.Cidade;
import br.com.fatec.eng3.model.Cliente;
import br.com.fatec.eng3.model.Estado;
import br.com.fatec.eng3.util.Conexao;

public class CidadeDAO implements IDAO  {
	
	public Cidade buscar(Connection con,Integer id) throws SQLException, ClassNotFoundException {
		
		
		Cidade cidade = null;
		
		PreparedStatement smtp = con.prepareStatement("SELECT * FROM cidades where id = ?");
		smtp.setInt(1, id);
		
		ResultSet resultSet = smtp.executeQuery();
		
		if(resultSet.next()) {
			
			cidade = new Cidade();
			
			cidade.setCodigoCidade(resultSet.getInt("id"));
			
			EstadoDAO estadoDAO = new EstadoDAO();
			Estado estado = estadoDAO.buscar(con, resultSet.getInt("estado"));
			
			cidade.setEstado(estado);
			cidade.setNome(resultSet.getString("cidade"));
			
		}
		
		resultSet.close();
		smtp.close();
		return cidade;
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
	public List<EntidadeModelo> buscarTodos(Connection con,EntidadeModelo entidade) throws SQLException, ClassNotFoundException {
		
		List<EntidadeModelo> lista = new ArrayList<>();
		Cidade cidade = null;
		
		String sql = "SELECT * FROM cidades";
		int posicaoBusca = 1;
		
		if(entidade != null) {
			
			sql += " WHERE ";
			
			
			cidade = (Cidade) entidade;
			
			if(cidade.getCidade() != null && !cidade.getCidade().isEmpty() ) {
				
				sql += " cidade = ? ";
			}
			
			if(cidade.getEstado() != null ) {
				
				sql += " estado = ? ";
			}
		}
		
		PreparedStatement smtp = con.prepareStatement(sql);
		if(entidade != null) {
			
			if(cidade.getCidade() != null && !cidade.getCidade().isEmpty() ) {
				
				smtp.setString(posicaoBusca, cidade.getCidade());
				posicaoBusca++;
			}
			
			if(cidade.getEstado() != null ) {
				
				smtp.setInt(posicaoBusca, cidade.getEstado().getId());
			}
			
		}
		
		ResultSet resultSet = smtp.executeQuery();
		
		
		while(resultSet.next()) {
			Cidade cid = this.buscar(con ,resultSet.getInt("id"));
			
			if(cid != null)
				lista.add(cid);
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
