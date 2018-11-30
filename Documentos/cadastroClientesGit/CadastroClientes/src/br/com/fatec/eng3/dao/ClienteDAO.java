package br.com.fatec.eng3.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.eng3.enums.Genero;
import br.com.fatec.eng3.enums.TipoEndereco;
import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.CartaoCredito;
import br.com.fatec.eng3.model.Cliente;
import br.com.fatec.eng3.model.ClienteEnderecoEntrega;
import br.com.fatec.eng3.model.Endereco;
import br.com.fatec.eng3.util.Conexao;

public class ClienteDAO implements IDAO {
	
	private Conexao con;
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Boolean salvar(Connection con ,EntidadeModelo cli) throws SQLException, ClassNotFoundException {
		
		con.setAutoCommit(false);
		
		Cliente cliente = (Cliente) cli;
		
		if(cliente.getId() == null || cliente.getId() < 1) {
		
			String sql = "INSERT INTO clientes (nome, dataNascimento, cpf, telefone, ranking, genero, enderecoCobranca, enderecoEntrega)"
					+ " VALUES(?, ?, ?, ?, ?, ?,?, ?)"; 
					
			PreparedStatement smtp = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			smtp.setString(1, cliente.getNome());
			
			Date dataNasc = new Date(cliente.getDataNascimento().getTime());
			smtp.setDate(2, dataNasc);
			
			// Trata CPF
			String regex = "\\D";
			
			smtp.setString(3, cliente.getCpf().replaceAll(regex, ""));
			smtp.setString(4, cliente.getDDD()+"-"+cliente.getTelefone().replaceAll(regex, ""));
			smtp.setInt(5, 0);
			smtp.setString(6, cliente.getGenero().name());
			
			//Endereço cobrança
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			Endereco endCobranca = cliente.getEnderecoCobranca();
			
			if(!enderecoDAO.salvar(con,endCobranca)) {
				
				setMsg(enderecoDAO.getMsg());
				con.rollback();
				return false;
			}
			
			//Endereço entrega
			
			Endereco endEntrega = cliente.getEnderecoEntrega();
			if(!enderecoDAO.salvar(con,endEntrega)) {
				
				setMsg(enderecoDAO.getMsg());
				con.rollback();
				return false;
			}
			
			
			smtp.setInt(7, endCobranca.getId());
			
			smtp.setInt(8, endEntrega.getId());
			
			int executeUpdate = smtp.executeUpdate();
			
			if(executeUpdate < 1) {
				
				setMsg("Erro ao inserir cliente");
				con.rollback();
				return false;
			}
			
			ResultSet resultSet = smtp.getGeneratedKeys();
			
			if(!resultSet.next()) {
				
				con.rollback();
				setMsg("Erro ao inserir cliente");
				
			}
			
			cliente.setId(resultSet.getInt(1));
			
			
			// Cartões
			List<CartaoCredito> cartoes = cliente.getCartoes();
			CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();
			
			for(CartaoCredito cartao : cartoes) {
				
				cartao.setCliente(cliente);
				
				if(!cartaoCreditoDAO.salvar(con, cartao)) {
					
					setMsg(cartaoCreditoDAO.getMsg());
					con.rollback();
					return false;
				}
			}
			
			con.commit();
			smtp.close();
		
		}
		else {
			
			String sqlUpdate = "UPDATE "+
			"clientes set nome = ?, "+
			"dataNascimento = ?, "+
			"telefone = ? , "+
			"genero = ? ";
			// verifica se cpf foi alterado
			
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente clienteCadastrado = clienteDAO.buscar(con, cliente.getId());
			
			if(!clienteCadastrado.getCpf().equals(cliente.getCpf().replaceAll("\\D", ""))) {
				
				sqlUpdate += ", cpf = ? "; 
			}
			
			sqlUpdate += " WHERE id = ?";
			
			System.out.println(sqlUpdate);
			
			PreparedStatement prepareStatementCli = con.prepareStatement(sqlUpdate);
			
			prepareStatementCli.setString(1, cliente.getNome());
			
			Date dataNasc = new Date(cliente.getDataNascimento().getTime());
			prepareStatementCli.setDate(2, dataNasc);
			
			prepareStatementCli.setString(3, cliente.getDDD()+"-"+cliente.getTelefone().replaceAll("\\D", ""));
			prepareStatementCli.setString(4, cliente.getGenero().name());
			
			if(!clienteCadastrado.getCpf().equals(cliente.getCpf().replaceAll("\\D", ""))) {
				
				prepareStatementCli.setString(5, cliente.getCpf().replaceAll("\\D", ""));
				prepareStatementCli.setInt(6, cliente.getId());
			}
			else {
				prepareStatementCli.setInt(5, cliente.getId());
			}
			
			int executeUpdateCli = prepareStatementCli.executeUpdate();
			
			if(executeUpdateCli < 1) {
				
				setMsg("Erro ao alterar cliente");
				return false;
			}
			
			
			//Endereço cobrança
			EnderecoDAO enderecoDAO = new EnderecoDAO();
			Endereco endCobranca = cliente.getEnderecoCobranca();
		
			if(!enderecoDAO.salvar(con,endCobranca)) {
				
				setMsg(enderecoDAO.getMsg());
				con.rollback();
				return false;
			}
			
			//Endereço entrega
			
			Endereco endEntrega = cliente.getEnderecoEntrega();
			
			if(!enderecoDAO.salvar(con,endEntrega)) {
				
				setMsg(enderecoDAO.getMsg());
				con.rollback();
				return false;
			}
			
			// Cartões
			
			List<CartaoCredito> cartoes = cliente.getCartoes();
			CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();
			
			for(CartaoCredito cartao : cartoes) {
				
				cartao.setCliente(cliente);
				
				if(!cartaoCreditoDAO.salvar(con, cartao)) {
					
					setMsg(cartaoCreditoDAO.getMsg());
					con.rollback();
					return false;
				}
			}
			
		}
		
		return true;
			
	}
	
	public Cliente buscar(Connection con,Integer id) throws SQLException, ClassNotFoundException {
		
		Cliente cli = null;
		ResultSet resultSet = null;
		
		String sql = "SELECT * FROM clientes WHERE id = ?";
		
		PreparedStatement smtp = con.prepareStatement(sql);
		smtp.setInt(1, id);
		
		resultSet = smtp.executeQuery();
		
		if(resultSet.next()) {
			
			cli = new Cliente();
			
			cli.setId(resultSet.getInt("id"));
			cli.setNome(resultSet.getString("nome"));
			cli.setDataNascimento(resultSet.getDate("dataNascimento"));
			cli.setCpf(resultSet.getString("cpf"));
			cli.setTelefone(resultSet.getString("telefone"));
			cli.setRanking(resultSet.getInt("ranking"));
			
			String a = resultSet.getString("genero");
			
			if(resultSet.getString("genero").equals("MASCULINO")) {
				cli.setGenero(Genero.MASCULINO);
			}
			
			if(resultSet.getString("genero").equals("FEMININO")) {
				cli.setGenero(Genero.FEMININO);
			}
			
			
			Endereco endCobranca =  (Endereco) (new EnderecoDAO()).buscar(con,resultSet.getInt("enderecoCobranca"));
			cli.setEnderecoCobranca(endCobranca);
			
			Endereco endEntrega =  (Endereco) (new EnderecoDAO()).buscar(con,resultSet.getInt("enderecoEntrega"));
			cli.setEnderecoEntrega(endEntrega);
			
			
			CartaoCreditoDAO cartaoCreditoDAO = new CartaoCreditoDAO();
			List<CartaoCredito> cartoes =  cartaoCreditoDAO.buscarPorCliente(con, cli);
			cli.setCartoes(cartoes);
			
		}
		
		resultSet.close();
		smtp.close();
		
		return cli;
		
	}
	
	public List<Cliente> buscarTodos(Connection con,EntidadeModelo entidade) throws SQLException, ClassNotFoundException{
		
		List<Cliente> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM clientes";
		
		
		PreparedStatement smtp = con.prepareStatement(sql);
		ResultSet resultSet = smtp.executeQuery();
		
		while(resultSet.next()) {
			Cliente cliente = this.buscar(con, resultSet.getInt("id"));
			
			if(cliente != null)
				lista.add(cliente);
		}
		
		resultSet.close();
		smtp.close();
		
		return lista;
	}

	@Override
	public Boolean alterar(Connection connection,EntidadeModelo entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean excluir(Connection con, EntidadeModelo entidade) {
		
		
	    try {
	    	con.setAutoCommit(false);
			Cliente cliente = (Cliente) entidade;
			
			
			
			// deleta cartoes
			String queryDelCartoes = "DELETE FROM cartao_credito WHERE cliente_id = ?";
			PreparedStatement prepareStatementCartoes = con.prepareStatement(queryDelCartoes);
			prepareStatementCartoes.setInt(1, cliente.getId());
			
			int executeUpdateCartoes = prepareStatementCartoes.executeUpdate();
			
			if(executeUpdateCartoes < 1) {
				
				setMsg("Erro ao deletar cartões(s)");
				con.rollback();
				return false;
			}
			
			// Deleta cliente
			String queryDelCliente = "DELETE FROM clientes WHERE id = ?";
			PreparedStatement prepareStatementCliente = con.prepareStatement(queryDelCliente);
			prepareStatementCliente.setInt(1, cliente.getId());
			
			int executeUpdateCli = prepareStatementCliente.executeUpdate();
			
			if(executeUpdateCli < 1) {
				
				setMsg("Erro ao deletar cliente");
				con.rollback();
				return false;
			}
			
			// deleta endereços
			String queryDelEnderecos = "DELETE FROM enderecos WHERE id in(?,?)";
			PreparedStatement prepareStatementEnderecos = con.prepareStatement(queryDelEnderecos);
			prepareStatementEnderecos.setInt(1, cliente.getEnderecoCobranca().getId());
			prepareStatementEnderecos.setInt(2, cliente.getEnderecoEntrega().getId());
			
			int executeUpdateEnd = prepareStatementEnderecos.executeUpdate();
			
			if(executeUpdateEnd < 1) {
				
				setMsg("Erro ao deletar endereço(s)");
				con.rollback();
				return false;
			}
			
			setMsg("Cliente excluído");
			con.commit();
			
			return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	

}
