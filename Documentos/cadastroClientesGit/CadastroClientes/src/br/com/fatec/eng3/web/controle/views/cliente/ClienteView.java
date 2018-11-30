package br.com.fatec.eng3.web.controle.views.cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fatec.eng3.dao.CartaoCreditoDAO;
import br.com.fatec.eng3.dao.CidadeDAO;
import br.com.fatec.eng3.dao.ClienteDAO;
import br.com.fatec.eng3.dao.EstadoDAO;
import br.com.fatec.eng3.dao.TipoLogradouroDAO;
import br.com.fatec.eng3.dao.TipoResidenciaDAO;
import br.com.fatec.eng3.enums.Genero;
import br.com.fatec.eng3.enums.TipoEndereco;
import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.CartaoCredito;
import br.com.fatec.eng3.model.Cidade;
import br.com.fatec.eng3.model.Cliente;
import br.com.fatec.eng3.model.Endereco;
import br.com.fatec.eng3.model.Estado;
import br.com.fatec.eng3.model.TipoLogradouro;
import br.com.fatec.eng3.model.TipoResidencia;
import br.com.fatec.eng3.util.Conexao;
import br.com.fatec.eng3.web.controle.comandos.Resultado;
import br.com.fatec.eng3.web.controle.views.IViewHelper;

public class ClienteView implements IViewHelper {

	@Override
	public EntidadeModelo getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		
		Cliente cliente = new Cliente();
		
		if(operacao == null) {
			
			return cliente;
		}
		
		if(operacao.equals("excluir")) {
			
			try {
				Connection connection = (new Conexao()).getConnection();
				
				if(!request.getParameterMap().containsKey("id") && request.getParameter("id").isEmpty()) {
					return cliente;
				}
				
				Integer id = Integer.parseInt(request.getParameter("id"));
				ClienteDAO clienteDAO = new ClienteDAO();
				
				cliente = clienteDAO.buscar(connection, id);
				
				return cliente;
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(operacao.equals("alterar")) {
			
			try {
				Connection connection = (new Conexao()).getConnection();
				
				if(!request.getParameterMap().containsKey("id") && request.getParameter("id").isEmpty()) {
					return cliente;
				}
				
				Integer id = Integer.parseInt(request.getParameter("id"));
				ClienteDAO clienteDAO = new ClienteDAO();
				
				cliente = clienteDAO.buscar(connection, id);
				
				// Acerta data pois o dia sempre vem com 1 a menos
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(cliente.getDataNascimento());
				calendar.add(Calendar.DATE, 1);
				cliente.setDataNascimento(calendar.getTime());
				
				// ddd
				
				cliente.setDDD(cliente.getTelefone().split("-")[0]);
				cliente.setTelefone(cliente.getTelefone().split("-")[1]);
				
				return cliente;
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(operacao.equals("salvar")) {
			
			try {
				
				Connection connection = (new Conexao()).getConnection();
				
				if(request.getParameterMap().containsKey("id") && !request.getParameter("id").isEmpty())
					cliente.setId(Integer.parseInt(request.getParameter("id")));
			
				if(request.getParameterMap().containsKey("nome") && !request.getParameter("nome").isEmpty())
					cliente.setNome(request.getParameter("nome"));
				
				if(request.getParameterMap().containsKey("dataNascimento") && !request.getParameter("dataNascimento").isEmpty()) {
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					Date dataNasc = formatter.parse(request.getParameter("dataNascimento"));
					cliente.setDataNascimento(dataNasc);
					
				}
				
				if(request.getParameterMap().containsKey("cpf") && !request.getParameter("cpf").isEmpty()) {
					
					cliente.setCpf(request.getParameter("cpf"));
					
				}
				
				if(request.getParameterMap().containsKey("ddd") && !request.getParameter("ddd").isEmpty())
					cliente.setDDD(request.getParameter("ddd"));
				
				if(request.getParameterMap().containsKey("telefone") && !request.getParameter("telefone").isEmpty())
					cliente.setTelefone(request.getParameter("telefone"));
				
				if(request.getParameterMap().containsKey("genero") && !request.getParameter("genero").isEmpty()) {
					
					if(request.getParameter("genero").equals(Genero.MASCULINO.toString())) {
						
						cliente.setGenero(Genero.MASCULINO);
					}
					
					if(request.getParameter("genero").equals(Genero.FEMININO.toString())) {
						
						cliente.setGenero(Genero.FEMININO);
					}
				}
			
				
				// End cobrança
				
				Endereco enderecoCobranca = new Endereco();
				
				if(request.getParameterMap().containsKey("endereco_cobranca_id") && !request.getParameter("endereco_cobranca_id").isEmpty()) {
					enderecoCobranca.setId(Integer.parseInt(request.getParameter("endereco_cobranca_id")));
				}
				
				if(request.getParameterMap().containsKey("endereco_cobranca_logradouro") && !request.getParameter("endereco_cobranca_logradouro").isEmpty()) {
					enderecoCobranca.setLogradouro(request.getParameter("endereco_cobranca_logradouro"));
					enderecoCobranca.setTipoEndereco(TipoEndereco.COBRANÇA);
				}
				
				if(request.getParameterMap().containsKey("endereco_cobranca_numero") && !request.getParameter("endereco_cobranca_numero").isEmpty())
					enderecoCobranca.setNumero(Integer.parseInt(request.getParameter("endereco_cobranca_numero")));
				
				if(request.getParameterMap().containsKey("endereco_cobranca_bairro") && !request.getParameter("endereco_cobranca_bairro").isEmpty())
					enderecoCobranca.setBairro(request.getParameter("endereco_cobranca_bairro"));
				
				if(request.getParameterMap().containsKey("endereco_cobranca_cep") && !request.getParameter("endereco_cobranca_cep").isEmpty())
					enderecoCobranca.setCep(request.getParameter("endereco_cobranca_cep"));
				
				if(request.getParameterMap().containsKey("endereco_cobranca_tipo_logradouro") && !request.getParameter("endereco_cobranca_tipo_logradouro").isEmpty()) {
					TipoLogradouro tpLogradouro = (new TipoLogradouroDAO()).buscar(connection,Integer.parseInt(request.getParameter("endereco_cobranca_tipo_logradouro")));
					enderecoCobranca.setTipoLogradouro(tpLogradouro);
				}
				
				if(request.getParameterMap().containsKey("endereco_cobranca_tipo_residencia") && !request.getParameter("endereco_cobranca_tipo_residencia").isEmpty()) {
					TipoResidencia tpResidencia = (new TipoResidenciaDAO()).buscar(connection,Integer.parseInt(request.getParameter("endereco_cobranca_tipo_residencia")));
					enderecoCobranca.setTipoResidencia(tpResidencia);
				}
				
				if(request.getParameterMap().containsKey("endereco_cobranca_cidade") && !request.getParameter("endereco_cobranca_cidade").isEmpty()) {
					
					CidadeDAO cidadeDAO = new CidadeDAO();
					Cidade cidade = cidadeDAO.buscar(connection,Integer.parseInt(request.getParameter("endereco_cobranca_cidade")));
					enderecoCobranca.setCidade(cidade);
					cliente.setEnderecoCobranca(enderecoCobranca);
				}
				
				cliente.setEnderecoCobranca(enderecoCobranca);
				
				
			    // End entrega
				
				Endereco enderecoEntrega = new Endereco();
				
				if(request.getParameterMap().containsKey("endereco_entrega_id") && !request.getParameter("endereco_entrega_id").isEmpty()) {
					enderecoEntrega.setId(Integer.parseInt(request.getParameter("endereco_entrega_id")));
				}
				
				if(request.getParameterMap().containsKey("endereco_entrega_logradouro") && !request.getParameter("endereco_entrega_logradouro").isEmpty()) {
					enderecoEntrega.setLogradouro(request.getParameter("endereco_entrega_logradouro"));
					enderecoEntrega.setTipoEndereco(TipoEndereco.ENTREGA);
				}
				
				if(request.getParameterMap().containsKey("endereco_entrega_numero") && !request.getParameter("endereco_entrega_numero").isEmpty())
					enderecoEntrega.setNumero(Integer.parseInt(request.getParameter("endereco_entrega_numero")));
				
				if(request.getParameterMap().containsKey("endereco_entrega_bairro") && !request.getParameter("endereco_entrega_bairro").isEmpty())
					enderecoEntrega.setBairro(request.getParameter("endereco_entrega_bairro"));
				
				if(request.getParameterMap().containsKey("endereco_entrega_cep") && !request.getParameter("endereco_entrega_cep").isEmpty())
					enderecoEntrega.setCep(request.getParameter("endereco_entrega_cep"));
				
				if(request.getParameterMap().containsKey("endereco_entrega_tipo_logradouro") && !request.getParameter("endereco_entrega_tipo_logradouro").isEmpty()) {
					TipoLogradouro tpLogradouro = (new TipoLogradouroDAO()).buscar(connection,Integer.parseInt(request.getParameter("endereco_entrega_tipo_logradouro")));
					enderecoEntrega.setTipoLogradouro(tpLogradouro);
				}
				
				if(request.getParameterMap().containsKey("endereco_entrega_tipo_residencia") && !request.getParameter("endereco_entrega_tipo_residencia").isEmpty()) {
					TipoResidencia tpResidencia = (new TipoResidenciaDAO()).buscar(connection,Integer.parseInt(request.getParameter("endereco_entrega_tipo_residencia")));
					enderecoEntrega.setTipoResidencia(tpResidencia);
				}
				
				if(request.getParameterMap().containsKey("endereco_entrega_cidade") && !request.getParameter("endereco_entrega_cidade").isEmpty()) {
					
					CidadeDAO cidadeDAO = new CidadeDAO();
					Cidade cidade = cidadeDAO.buscar(connection,Integer.parseInt(request.getParameter("endereco_entrega_cidade")));
					enderecoEntrega.setCidade(cidade);
					
				}
				cliente.setEnderecoEntrega(enderecoEntrega);
				
				
				// Cartoes
				String[] indicesCartoes = request.getParameter("indicesCartoes")
						.replaceAll("\\s+$", "")
						.split(" ");
				
				List<CartaoCredito> cartoes = new ArrayList<>();
				
				for(int i = 0; i < indicesCartoes.length ; i++) {
					
					CartaoCredito cartaoCredito = new CartaoCredito();
					String idCartao = request.getParameter(String.format("cartoes[%s]['id']", indicesCartoes[i]));
					String numCartao = request.getParameter(String.format("cartoes[%s]['numCartao']", indicesCartoes[i]));
					String nomeImpresso = request.getParameter(String.format("cartoes[%s]['nomeImpresso']", indicesCartoes[i]));
					String codSeguranca = request.getParameter(String.format("cartoes[%s]['codSeguranca']", indicesCartoes[i]));
					String dataVencimento = request.getParameter(String.format("cartoes[%s]['dataVencimento']", indicesCartoes[i]));
					
					if(!idCartao.isEmpty()) {
						cartaoCredito.setId(Integer.parseInt(idCartao));
					}
					
					if(!numCartao.isEmpty()) {
						cartaoCredito.setNumCartao(numCartao);
					}
					if(!nomeImpresso.isEmpty()) {
						cartaoCredito.setNomeImpresso(nomeImpresso);
					}
					
					if(!codSeguranca.isEmpty()) {
						Integer codSeg = Integer.parseInt(codSeguranca);
						cartaoCredito.setCodSeguranca(codSeg);
					}
					if(dataVencimento != null) {
						
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						Date dataVenc = formatter.parse(dataVencimento);
						cartaoCredito.setDataVencimento(dataVenc);
					}

					if(cartaoCredito != null) {
						cartoes.add(cartaoCredito);
					}
					
				}
				
				cliente.setCartoes(cartoes);
				connection.close();
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return cliente;
		
	}

	@Override
	public void setView(Resultado resultado,HttpServletRequest request,  HttpServletResponse response) throws IOException {
		
		try {
			
			String operacao = request.getParameter("operacao");
		
			if(operacao != null && operacao.equals("listar")) {
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("clientes.jsp");
				HttpSession session = request.getSession();
				
				request.setAttribute("Lista", resultado.getListaEntidades());
				requestDispatcher.forward(request, response);
			}
			
			if(operacao != null && operacao.equals("excluir")) {
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("clientes.jsp");
				HttpSession session = request.getSession();
				
				if(resultado != null) {
					session.setAttribute("erro", resultado.getStatus());
					session.setAttribute("msg", resultado.getMsg());
				}
				
				response.sendRedirect("cliente?operacao=listar");
			}
			
			if(operacao != null && operacao.equals("salvar")) {
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("clientes.jsp");
				HttpSession session = request.getSession();
				
				if(resultado != null) {
					session.setAttribute("erro", resultado.getStatus());
					session.setAttribute("msg", resultado.getMsg());
				}
				
				response.sendRedirect("cliente?operacao=listar");
			}
			
			if(operacao != null && ( operacao.equals("inserir") || operacao.equals("alterar") )) {
				
				try {
					
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("formulario-cliente.jsp");
					HttpSession session = request.getSession();
					
					Connection connection = (new Conexao()).getConnection();
					
					EstadoDAO estadoDAO = new EstadoDAO();
					TipoResidenciaDAO tipoResidenciaDAO = new TipoResidenciaDAO();
					TipoLogradouroDAO tipoLogradouro = new TipoLogradouroDAO();
					
					try {
						request.setAttribute("ListaEstados", estadoDAO.buscarTodos(connection,null));
						request.setAttribute("ListaTipoResidencia", tipoResidenciaDAO.buscarTodos(connection,null));
						request.setAttribute("ListaTipoLogradouro", tipoLogradouro.buscarTodos(connection,null));
						
					
						if(operacao.equals("alterar")) {
							request.setAttribute("Cliente", resultado.getEntidade());
						}
						
						requestDispatcher.forward(request, response);
					
					} catch (ClassNotFoundException | SQLException e) {
						
						e.printStackTrace();
					}
					
					connection.close();
					
				} catch (ServletException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
