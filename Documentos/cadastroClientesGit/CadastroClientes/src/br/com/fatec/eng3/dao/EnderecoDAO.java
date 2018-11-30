package br.com.fatec.eng3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.eng3.enums.TipoEndereco;
import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.Cidade;
import br.com.fatec.eng3.model.Cliente;
import br.com.fatec.eng3.model.Endereco;
import br.com.fatec.eng3.model.Estado;
import br.com.fatec.eng3.model.TipoLogradouro;
import br.com.fatec.eng3.model.TipoResidencia;
import br.com.fatec.eng3.util.Conexao;

public class EnderecoDAO implements IDAO {

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Endereco buscar(Connection con,Integer id) throws SQLException, ClassNotFoundException {

		
		Endereco endereco = null;

		PreparedStatement smtp = con.prepareStatement("SELECT * FROM enderecos where id = ?");
		smtp.setInt(1, id);

		ResultSet resultSet = smtp.executeQuery();

		if (resultSet.next()) {

			endereco = new Endereco();

			endereco.setId(resultSet.getInt("id"));
			endereco.setBairro(resultSet.getString("bairro"));
			endereco.setCep(resultSet.getString("cep"));
			endereco.setLogradouro(resultSet.getString("logradouro"));
			endereco.setNumero(resultSet.getInt("numero"));

			Cidade cidade = (new CidadeDAO()).buscar(con, resultSet.getInt("cidade"));
			endereco.setCidade(cidade);

			endereco.setObservacoes(resultSet.getString("observacoes"));

			TipoLogradouro tipoLogradouro = (new TipoLogradouroDAO()).buscar(con, resultSet.getInt("tipoLogradouro"));
			endereco.setTipoLogradouro(tipoLogradouro);

			TipoResidencia tipoResidencia = (new TipoResidenciaDAO()).buscar(con, resultSet.getInt("tipoResidencia"));
			endereco.setTipoResidencia(tipoResidencia);

		}

		resultSet.close();
		smtp.close();
		return endereco;
	}

	@Override
	public Boolean salvar(Connection con,EntidadeModelo entidade) throws SQLException, ClassNotFoundException {

		con.setAutoCommit(false);
		
		Endereco endereco = (Endereco) entidade;
		
		if(endereco.getId() == null || endereco.getId() < 1) {
		
			String sql = "INSERT INTO enderecos (logradouro, numero, bairro, cep, cidade, observacoes, tipoResidencia, tipoLogradouro, tipoEndereco)"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ? )";
	
			PreparedStatement smtp = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	
			smtp.setString(1, endereco.getLogradouro());
			smtp.setInt(2, endereco.getNumero());
			smtp.setString(3, endereco.getBairro());
			smtp.setString(4, endereco.getCep());
			smtp.setInt(5, endereco.getCidade().getId());
			smtp.setString(6, endereco.getObservacoes());
			smtp.setInt(7, endereco.getTipoResidencia().getId());
			smtp.setInt(8, endereco.getTipoLogradouro().getId());
			smtp.setString(9, endereco.getTipoEndereco().name());
			
			System.out.println(smtp.toString());
	
			int executeUpdate = smtp.executeUpdate();
	
			if (executeUpdate < 1) {
				setMsg("Erro ao inserir endereço");
				con.rollback();
				return false;
			}
	
			ResultSet resultSet = smtp.getGeneratedKeys();
			
			if(!resultSet.next()) {
				con.rollback();
				setMsg("Erro ao inserir endereço");
				return false;
				
			}
			
			endereco.setId(resultSet.getInt(1));
			con.commit();
		
		}
		else {
			
			String sql = "UPDATE enderecos set logradouro = ? , numero  = ?, bairro = ?, cep = ?,"
					+" cidade  = ?, observacoes  = ?, tipoResidencia  = ?, tipoLogradouro  = ?, tipoEndereco = ? "
					+ " where id = ? ";
					
	
			PreparedStatement smtp = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	
			smtp.setString(1, endereco.getLogradouro());
			smtp.setInt(2, endereco.getNumero());
			smtp.setString(3, endereco.getBairro());
			smtp.setString(4, endereco.getCep());
			smtp.setInt(5, endereco.getCidade().getId());
			smtp.setString(6, endereco.getObservacoes());
			smtp.setInt(7, endereco.getTipoResidencia().getId());
			smtp.setInt(8, endereco.getTipoLogradouro().getId());
			smtp.setString(9, endereco.getTipoEndereco().name());
			smtp.setInt(10, endereco.getId());
			
			System.out.println(smtp.toString());
	
			int executeUpdate = smtp.executeUpdate();
	
			if (executeUpdate < 1) {
				setMsg("Erro ao alterar endereço");
				con.rollback();
				return false;
			}
	
			con.commit();
			
		}

		return true;
	}

	@Override
	public Boolean alterar(Connection con,EntidadeModelo entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadeModelo> buscarTodos(Connection con,EntidadeModelo entidade) throws SQLException, ClassNotFoundException {
		
		
		List<EntidadeModelo> lista = new ArrayList<>();
		Endereco endereco = null;
		
		String sql = "SELECT * FROM enderecos";
		int posicaoBusca = 1;
		
		if(entidade != null) {
			
			sql += " WHERE ";
			
			
			endereco = (Endereco) entidade;
			
			if(endereco.getId() != null  && endereco.getId() != null) {
				
				sql += " id = ? ";
			}
			
			if(endereco.getTipoEndereco() != null ) {
				
				sql += " tipoEndereco = ? ";
			}
			
		}
		
		PreparedStatement smtp = con.prepareStatement(sql);
		if(entidade != null) {
			
			if(endereco.getId() != null  && endereco.getId() != null) {
				
				smtp.setInt(posicaoBusca, endereco.getId());
				posicaoBusca++;
			}
			
			if(endereco.getTipoEndereco() != null ) {
				
				smtp.setString(posicaoBusca, endereco.getTipoEndereco().name());
			}
			
		}
		
		ResultSet resultSet = smtp.executeQuery();
		
		
		while(resultSet.next()) {
			Endereco end = this.buscar(con, resultSet.getInt("id"));
			
			if(end != null)
				lista.add(end);
		}
		
		resultSet.close();
		smtp.close();
		
		return lista;
		
	}
	
	
	public EntidadeModelo buscarPorClienteTipoEndereco(Integer id, TipoEndereco tipoEndereco) throws SQLException, ClassNotFoundException {
		
		Connection con = Conexao.getConnection();
		Endereco endereco = new Endereco();
		
		String sql = "SELECT * FROM enderecos WHERE id = ? and tipoEndereco = ? ";
		
		PreparedStatement smtp = con.prepareStatement(sql);
		
		smtp.setInt(1, id);
		smtp.setString(2, tipoEndereco.name());
		
		ResultSet resultSet = smtp.executeQuery();
		
		if(resultSet.next()) {
			
			endereco = new Endereco();

			endereco.setId(resultSet.getInt("id"));
			endereco.setBairro(resultSet.getString("bairro"));
			endereco.setCep(resultSet.getString("cep"));
			endereco.setLogradouro(resultSet.getString("logradouro"));
			endereco.setNumero(resultSet.getInt("numero"));

			Cidade cidade = (new CidadeDAO()).buscar(con, resultSet.getInt("cidade"));
			endereco.setCidade(cidade);

			endereco.setObservacoes(resultSet.getString("observacoes"));

			TipoLogradouro tipoLogradouro = (new TipoLogradouroDAO()).buscar(con, resultSet.getInt("tipoLogradouro"));
			endereco.setTipoLogradouro(tipoLogradouro);

			TipoResidencia tipoResidencia = (new TipoResidenciaDAO()).buscar(con, resultSet.getInt("tipoResidencia"));
			endereco.setTipoResidencia(tipoResidencia);
			
		}
		
		resultSet.close();
		smtp.close();
		con.close();
		
		return endereco;
		
	}

	@Override
	public Boolean excluir(Connection connection, EntidadeModelo entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
