package br.com.fatec.eng3.web.controle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import br.com.fatec.eng3.dao.CidadeDAO;
import br.com.fatec.eng3.dao.ClienteDAO;
import br.com.fatec.eng3.dao.EstadoDAO;
import br.com.fatec.eng3.dao.IDAO;
import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.Cidade;
import br.com.fatec.eng3.model.Cliente;
import br.com.fatec.eng3.model.Estado;
import br.com.fatec.eng3.negocio.AbstractValidadores;
import br.com.fatec.eng3.negocio.IStrategyValidadores;
import br.com.fatec.eng3.negocio.ValidarCPF;
import br.com.fatec.eng3.negocio.ValidarDadosCliente;
import br.com.fatec.eng3.util.Conexao;
import br.com.fatec.eng3.web.controle.comandos.Resultado;

public class Fachada implements IFachada {
	
	private HashMap<String, IDAO> daos;
	private HashMap<String, List<AbstractValidadores>> rns;
	private StringBuilder sb = new StringBuilder();
	private Resultado resultado = new Resultado();
	
	
	public Fachada() {
		
		daos = new HashMap<String, IDAO>();
		rns = new HashMap<String, List<AbstractValidadores>>();
		
		// Definindo os DAOS
		daos.put(Cliente.class.getName(), new ClienteDAO());
		daos.put(Estado.class.getName(), new EstadoDAO());
		daos.put(Cidade.class.getName(), new CidadeDAO());
		
		// Validadores
		ValidarDadosCliente validarCliente = new ValidarDadosCliente();
		ValidarCPF validarCPF = new ValidarCPF();
		
		// Cria listas de validações
		List<AbstractValidadores> listaRnsCli = new ArrayList<>();
		listaRnsCli.add(validarCliente);
		listaRnsCli.add(validarCPF);
		
		rns.put(Cliente.class.getName(), listaRnsCli);
		
		
	}
	
	

	@Override
	public Resultado salvar(EntidadeModelo entidade) throws ClassNotFoundException, SQLException {
		
		Connection connection = (new Conexao()).getConnection();
		
		try {
			
			sb.setLength(0);
			
			String nomeClasse = entidade.getClass().getName();
			List<AbstractValidadores> rnsEntidade = rns.get(nomeClasse);
			
			for(AbstractValidadores rn: rnsEntidade) {
				
				if(!rn.processar(entidade)) {
					
					sb.append(rn.getMsg());
				}
			}
			
			if(sb.length() < 1) {
				
				IDAO dao = daos.get(nomeClasse);
				if(!dao.salvar(connection,entidade)) {
					
					sb.append("Erro ao salvar dados da entidade "+ nomeClasse);
					resultado.setMsg(sb.toString());
					resultado.setStatus(false);
					return resultado;
				}
				resultado.setStatus(true);
				resultado.setMsg("Cliente inserido/alterado com sucesso!");
			}
			else {
				
				resultado.setMsg(sb.toString());
				resultado.setStatus(false);
			}
			
		}
		catch(SQLException e) {
			resultado.setMsg(e.getMessage());
			resultado.setStatus(false);
		}
		connection.close();
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeModelo entidade) {
		
		Resultado resultado = new Resultado();
		resultado.setEntidade(entidade);
		
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeModelo entidade) {
		
		Connection connection;
		try {
			connection = (new Conexao()).getConnection();
			String nomeClasse = entidade.getClass().getName();
			
			IDAO dao = daos.get(nomeClasse);
			
			if(!dao.excluir(connection, entidade)) {
				sb.append("Erro ao excluir dados da entidade "+ nomeClasse);
				resultado.setMsg(sb.toString());
				resultado.setStatus(false);
			}
			else {
				resultado.setStatus(true);
				resultado.setMsg("Cliente excluído com sucesso!");
			}
			
			return resultado;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Resultado consultar(EntidadeModelo entidade) throws ClassNotFoundException, SQLException {
		
		if(entidade == null) {
			
			return null;
		}
		
		Connection connection = (new Conexao()).getConnection();
		
		
		
		return null;
	}

	@Override
	public Resultado listar(EntidadeModelo entidade) throws SQLException, ClassNotFoundException {
		
		Connection connection = (new Conexao()).getConnection();
		
		String nomeClasse = entidade.getClass().getName();
		IDAO dao = daos.get(nomeClasse);
		
		List<EntidadeModelo> buscarTodos = (List<EntidadeModelo>) dao.buscarTodos(connection,null);
		
		resultado.setListaEntidades(buscarTodos);
		
		connection.close();
		
		return resultado;
	}

	@Override
	public Resultado inserir(EntidadeModelo entidade) {
		
		Resultado resultado = new Resultado();
		resultado.setEntidade(entidade);
		
		return resultado;
	}

}
