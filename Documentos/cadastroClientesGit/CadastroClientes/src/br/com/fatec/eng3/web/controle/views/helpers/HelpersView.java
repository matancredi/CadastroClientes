package br.com.fatec.eng3.web.controle.views.helpers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.eng3.dao.CidadeDAO;
import br.com.fatec.eng3.dao.EstadoDAO;
import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.Cidade;
import br.com.fatec.eng3.model.Estado;
import br.com.fatec.eng3.util.Conexao;
import br.com.fatec.eng3.web.controle.comandos.Resultado;
import br.com.fatec.eng3.web.controle.views.IViewHelper;

public class HelpersView implements IViewHelper {

	@Override
	public EntidadeModelo getEntidade(HttpServletRequest request) {
		
		return null;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		String acao = request.getParameter("acao");
		
		
		if(acao.equals("estado-cidade")) {
			try {
				
				Connection connection = (new Conexao()).getConnection();
				
				PrintWriter writer = response.getWriter();
				
				if(request.getParameter("id").isEmpty()) {
					
					writer.printf("");
				}
				
				Integer idEstado = Integer.parseInt(request.getParameter("id"));
				EstadoDAO estadoDao = new EstadoDAO();
				Estado estado = estadoDao.buscar(connection,idEstado);
				Cidade cidade = new Cidade();
				
				cidade.setEstado(estado);
				
				CidadeDAO cidadeDAO = new CidadeDAO();
				
				List<EntidadeModelo> listaCidades = cidadeDAO.buscarTodos(connection,cidade);
				
				Iterator<EntidadeModelo> iterator = listaCidades.iterator();
				
				writer.printf("<option value='' >%s</option>", "Selecione uma cidade");
				
				while(iterator.hasNext()) {
					
					Cidade cid = (Cidade) iterator.next();
					writer.printf("<option value='%d' >%s</option>", cid.getId(),cid.getCidade());
					
				}
				
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
}
