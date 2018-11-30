package br.com.fatec.eng3.negocio;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.Cliente;
import br.com.fatec.eng3.model.Endereco;

public class ValidarDadosCliente extends AbstractValidadores {

	@Override
	public Boolean processar(EntidadeModelo entidade) {

		Cliente cli = (Cliente) entidade;
		StringBuilder msgErros = new StringBuilder();
		msgErros.setLength(0);
		
		if(entidade == null) {
			
			msgErros.append("|Campos de cliente não informados");
			this.setMsg(msgErros);
			return false;
		}

		if (cli.getNome() == null || cli.getNome().trim().equals("")) {

			msgErros.append("|Campo nome é obrigatório");

		}

		if (cli.getDataNascimento() == null || cli.getDataNascimento().toString().isEmpty()) {

			msgErros.append("|Campo data de nascimento é obrigatória");

		}

		if (cli.getCpf() == null ||  cli.getCpf().trim().equals("")) {

			msgErros.append("|Campo cpf é obrigatório");

		}

		if (cli.getTelefone() == null || cli.getTelefone().trim().equals("")) {

			msgErros.append("|Campo telefone é obrigatório");

		}

		if (cli.getGenero() == null) {

			msgErros.append("|Informe o gênero");

		}
		
		ValidarEndereco validarEndereco = new ValidarEndereco();
		
		if(!validarEndereco.processar(cli.getEnderecoCobranca())) {
			
			msgErros.append(validarEndereco.getMsg());
		}
		
		if(!validarEndereco.processar(cli.getEnderecoEntrega())) {
			
			msgErros.append(validarEndereco.getMsg());
		}
		

		if (!msgErros.toString().isEmpty()) {

			this.setMsg(msgErros);
			return false;
		}

		return true;
	}

}
