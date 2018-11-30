package br.com.fatec.eng3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.CartaoCredito;
import br.com.fatec.eng3.model.Cidade;
import br.com.fatec.eng3.model.Cliente;
import br.com.fatec.eng3.model.Endereco;
import br.com.fatec.eng3.model.TipoLogradouro;
import br.com.fatec.eng3.model.TipoResidencia;

public class CartaoCreditoDAO implements IDAO {
	
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public Boolean salvar(Connection con, EntidadeModelo entidade) throws SQLException, ClassNotFoundException {

		con.setAutoCommit(false);
		
		CartaoCredito cartao = (CartaoCredito) entidade;
		
		if(cartao.getId() == null || cartao.getId() < 1) {
			
			String sql = "INSERT INTO cartao_credito (cliente_id, numCartao, nomeImpresso, codSeguranca, dataVencimento)"
					+ " VALUES(?, ?, ?, ?, ?)";
	
			PreparedStatement smtp = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	
			smtp.setInt(1, cartao.getCliente().getId());
			smtp.setString(2, cartao.getNumCartao());
			smtp.setString(3, cartao.getNomeImpresso());
			smtp.setInt(4, cartao.getCodSeguranca());
			
			java.sql.Date dataVencimento = new java.sql.Date(cartao.getDataVencimento().getTime());
			smtp.setDate(5, dataVencimento);
		
			int executeUpdate = smtp.executeUpdate();
	
			if (executeUpdate < 1) {
				setMsg("Erro ao inserir cartão");
				con.rollback();
				return false;
			}
	
			ResultSet resultSet = smtp.getGeneratedKeys();
			
			if(!resultSet.next()) {
				con.rollback();
				setMsg("Erro ao inserir cartão");
				return false;
				
			}
			
			cartao.setId(resultSet.getInt(1));
			con.commit();
		
		}
		else {
			
			String sqlUpdate = "UPDATE cartao_credito set cliente_id = ? , nomeImpresso = ? , codSeguranca = ? , dataVencimento = ? ";
					
					// verifica se cartão foi alterado
					
			CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();
			CartaoCredito cartaoAtual = cartaoCreditoDAO.buscar(con, cartao.getId());
			
			if(!cartaoAtual.getNumCartao().equals(cartao.getNumCartao())) {
				sqlUpdate += " , numCartao = ? ";
			}
			sqlUpdate += " WHERE id = ? ";
			
			PreparedStatement smtp = con.prepareStatement(sqlUpdate);
			
			smtp.setInt(1, cartao.getCliente().getId());
			smtp.setString(2, cartao.getNomeImpresso());
			smtp.setInt(3, cartao.getCodSeguranca());
			
			java.sql.Date dataVenc = new java.sql.Date(cartao.getDataVencimento().getTime());
			smtp.setDate(4, dataVenc);
			
			if(!cartaoAtual.getNumCartao().equals(cartao.getNumCartao())) {
				smtp.setString(5, cartao.getNumCartao());
				smtp.setInt(6, cartao.getId());
			}
			else {
				smtp.setInt(5, cartao.getId());
			}
			
			System.out.println(smtp.toString());
			
			int executeUpdate = smtp.executeUpdate();
			
			if (executeUpdate < 1) {
				setMsg("Erro ao alterar cartão");
				con.rollback();
				return false;
			}
	
		}

		return true;
		
	}

	@Override
	public Boolean alterar(Connection connection, EntidadeModelo entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean excluir(Connection connection, EntidadeModelo entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartaoCredito buscar(Connection con, Integer id) throws SQLException, ClassNotFoundException {
		
		CartaoCredito cartao = null;

		PreparedStatement smtp = con.prepareStatement("SELECT * FROM cartao_credito where id = ?");
		smtp.setInt(1, id);

		ResultSet resultSet = smtp.executeQuery();

		if (resultSet.next()) {
			
			cartao = new CartaoCredito();
			
			cartao.setId(resultSet.getInt("id"));
			
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = clienteDAO.buscar(con, resultSet.getInt("cliente_id"));
			
			cartao.setCliente(cliente);
			cartao.setNumCartao(resultSet.getString("numCartao"));
			cartao.setNomeImpresso(resultSet.getString("nomeImpresso"));
			cartao.setCodSeguranca(resultSet.getInt("codSeguranca"));
			
			Date dataVenc = resultSet.getDate("dataVencimento");
			cartao.setDataVencimento(dataVenc);
			
		}

		resultSet.close();
		smtp.close();
		return cartao;
	}

	@Override
	public List<CartaoCredito> buscarTodos(Connection con, EntidadeModelo entidade)
			throws SQLException, ClassNotFoundException {
		
		
		List<CartaoCredito> lista = new ArrayList<>();
		CartaoCredito cartao = null;
		
		String sql = "SELECT * FROM cartao_credito";
		int posicaoBusca = 1;
		
		if(entidade != null) {
			
			sql += " WHERE ";
			cartao = (CartaoCredito) entidade;
			if(cartao.getCliente() != null  && cartao.getCliente().getId() != null) {
				sql += " cliente_id = ? ";
			}
			
		}
		
		PreparedStatement smtp = con.prepareStatement(sql);
		if(entidade != null) {
			
			if(cartao.getCliente() != null  && cartao.getCliente().getId() != null) {
				
				smtp.setInt(posicaoBusca, cartao.getCliente().getId());
				posicaoBusca++;
			}
			
		}
		
		ResultSet resultSet = smtp.executeQuery();
		while(resultSet.next()) {
			CartaoCredito card = this.buscar(con, resultSet.getInt("id"));
			
			if(card != null)
				lista.add(card);
		}
		
		resultSet.close();
		smtp.close();
		
		return lista;
	}
	
	
	public List<CartaoCredito> buscarPorCliente(Connection con, Cliente cliente)
			throws SQLException, ClassNotFoundException {
		
		
		List<CartaoCredito> lista = new ArrayList<>();
		CartaoCredito cartao = null;
		
		String sql = "SELECT * FROM cartao_credito WHERE cliente_id = ?";
		
		PreparedStatement smtp = con.prepareStatement(sql);
		smtp.setInt(1, cliente.getId());
		
		ResultSet resultSet = smtp.executeQuery();
		while(resultSet.next()) {
			
			CartaoCredito cartaoCredito = new CartaoCredito();
			
			cartaoCredito.setCliente(cliente);
			cartaoCredito.setId(resultSet.getInt("id"));
			cartaoCredito.setNumCartao(resultSet.getString("numCartao"));
			cartaoCredito.setNomeImpresso(resultSet.getString("nomeImpresso"));
			cartaoCredito.setCodSeguranca(resultSet.getInt("codSeguranca"));
			
			java.sql.Date dataVencimento = new java.sql.Date(resultSet.getDate("dataVencimento").getTime());
			
			cartaoCredito.setDataVencimento(dataVencimento);
			lista.add(cartaoCredito);
			
		}
		
		resultSet.close();
		smtp.close();
		
		return lista;
	}

}
