package br.com.fatec.eng3.web.controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.com.fatec.eng3.dao.EstadoDAO;
import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.CartaoCredito;
import br.com.fatec.eng3.model.Cliente;
import br.com.fatec.eng3.model.Estado;
import br.com.fatec.eng3.web.controle.comandos.AlterarComando;
import br.com.fatec.eng3.web.controle.comandos.ConsultarComando;
import br.com.fatec.eng3.web.controle.comandos.ExcluirComando;
import br.com.fatec.eng3.web.controle.comandos.IComando;
import br.com.fatec.eng3.web.controle.comandos.ListarComando;
import br.com.fatec.eng3.web.controle.comandos.Resultado;
import br.com.fatec.eng3.web.controle.comandos.SalvarComando;
import br.com.fatec.eng3.web.controle.views.IViewHelper;
import br.com.fatec.eng3.web.controle.views.cliente.ClienteView;
import br.com.fatec.eng3.web.controle.views.cliente.FormularioClienteView;
import br.com.fatec.eng3.web.controle.views.helpers.HelpersView;

@WebServlet(urlPatterns = {
		"/cliente"
		,"/helpers"})
public class PrincipalController extends HttpServlet {
	
	private Map<String, IComando> comandos;
	private Map<String, IViewHelper> views;
	
	public PrincipalController() {
		
		comandos = new HashMap<String, IComando>();
		comandos.put("inserir", new InserirComando());
		comandos.put("salvar", new SalvarComando());
		comandos.put("alterar", new AlterarComando());
		comandos.put("excluir", new ExcluirComando());
		comandos.put("listar", new ListarComando());
		comandos.put("consultar", new ConsultarComando());
		
		views = new HashMap<String,IViewHelper>();
		views.put("/CadastroClientes/cliente", new ClienteView());
//		views.put("/CadastroClientes/cliente-formulario", new FormularioClienteView());
		views.put("/CadastroClientes/helpers", new HelpersView());
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req,resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

		try {
			
			String uri = req.getRequestURI();
			req.setCharacterEncoding("UTF8");
			
			String operacao = req.getParameter("operacao");
			Resultado resultado = null;
			
			IViewHelper view = views.get(uri);
			
			if(view == null) {
				
				throw new ServletException("Não existe view associada a rota");
			}
			
			IComando comando = comandos.get(operacao);
			EntidadeModelo entidade =  view.getEntidade(req);
			
			if(operacao != null && !operacao.isEmpty()) {
				resultado = comando.executar(entidade);
			}
			
			view.setView(resultado, req, resp);

		}
		catch (Exception e) {
			
			e.printStackTrace();
		}

	}
	
	

}
