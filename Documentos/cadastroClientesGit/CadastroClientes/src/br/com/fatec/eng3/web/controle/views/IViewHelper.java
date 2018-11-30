package br.com.fatec.eng3.web.controle.views;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.web.controle.comandos.Resultado;

public interface IViewHelper {
	
	public EntidadeModelo getEntidade(HttpServletRequest request);
	public void setView(Resultado resultado,HttpServletRequest request, HttpServletResponse response) throws IOException;

}
