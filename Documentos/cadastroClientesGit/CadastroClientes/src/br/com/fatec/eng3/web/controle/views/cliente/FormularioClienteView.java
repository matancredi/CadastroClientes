package br.com.fatec.eng3.web.controle.views.cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.eng3.dao.EstadoDAO;
import br.com.fatec.eng3.dao.TipoLogradouroDAO;
import br.com.fatec.eng3.dao.TipoResidenciaDAO;
import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.Cliente;
import br.com.fatec.eng3.model.TipoLogradouro;
import br.com.fatec.eng3.model.TipoResidencia;
import br.com.fatec.eng3.util.Conexao;
import br.com.fatec.eng3.web.controle.comandos.Resultado;
import br.com.fatec.eng3.web.controle.views.IViewHelper;

public class FormularioClienteView implements IViewHelper {

	@Override
	public EntidadeModelo getEntidade(HttpServletRequest request) {
		
		return new Cliente();
	}

	@Override
	public void setView(Resultado resultado,HttpServletRequest request,  HttpServletResponse response) throws IOException {
		
		try {
		
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("formulario-cliente.jsp");
			Connection connection = (new Conexao()).getConnection();
			
			String operacao = request.getParameter("operacao");
			
			EstadoDAO estadoDAO = new EstadoDAO();
			TipoResidenciaDAO tipoResidenciaDAO = new TipoResidenciaDAO();
			TipoLogradouroDAO tipoLogradouro = new TipoLogradouroDAO();
			
			try {
				request.setAttribute("ListaEstados", estadoDAO.buscarTodos(connection,null));
				request.setAttribute("ListaTipoResidencia", tipoResidenciaDAO.buscarTodos(connection,null));
				request.setAttribute("ListaTipoLogradouro", tipoLogradouro.buscarTodos(connection,null));
				requestDispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			
			
			
		} catch (ServletException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
