package br.com.fatec.eng3.negocio;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.Cliente;

public class ValidarCPF extends AbstractValidadores {

	@Override
	public Boolean processar(EntidadeModelo entidade) {
		
		Cliente cli = (Cliente) entidade;
		StringBuilder msgErros = new StringBuilder();
		msgErros.setLength(0);
		String regex = "\\D";
		
		if(entidade == null) {
			msgErros.append("|Campos de cliente não informados");
			this.setMsg(msgErros);
			return false;
		}
		
		if(cli.getCpf() == null) {
			
			msgErros.append("|Campo cpf não informado");
			this.setMsg(msgErros);
			return false;
		}
		
		String cpfTratado = cli.getCpf().replaceAll(regex, "");
		
		if(cpfTratado.length() != 11) {
			
			msgErros.append("|CPF inválido, o mesmo deve possuir 11 números");
			
		}
		
		if(!msgErros.toString().isEmpty()) {
			
			this.setMsg(msgErros);
			return false;
		}
		
		return true;
	}

}
