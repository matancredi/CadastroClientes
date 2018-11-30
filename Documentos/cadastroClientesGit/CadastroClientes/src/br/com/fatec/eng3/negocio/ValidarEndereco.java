package br.com.fatec.eng3.negocio;

import br.com.fatec.eng3.model.EntidadeModelo;
import br.com.fatec.eng3.model.Cliente;
import br.com.fatec.eng3.model.Endereco;

public class ValidarEndereco extends AbstractValidadores {

	@Override
	public Boolean processar(EntidadeModelo entidade) {
		
		Endereco endereco = (Endereco) entidade;
		StringBuilder msgErros = new StringBuilder();
		msgErros.setLength(0);
		
		if(entidade == null) {
			msgErros.append("|Campos de endereço não informados");
			this.setMsg(msgErros);
			return false;
		}

		if (endereco.getLogradouro() == null || endereco.getLogradouro().trim().equals("")) {

			msgErros.append("|Campo logradouro é obrigatório");

		}
		
		if (endereco.getNumero() == null || endereco.getNumero() == 0) {

			msgErros.append("|Campo número é obrigatório");

		}
		
		if (endereco.getBairro() == null || endereco.getBairro().trim().equals("")) {

			msgErros.append("|Campo bairro é obrigatório");

		}
		
		if (endereco.getCep() == null || endereco.getCep().trim().equals("")) {

			msgErros.append("|Campo cep é obrigatório");

		}
		
		if (endereco.getCidade() == null || endereco.getCidade().getCidade().trim().equals("")) {

			msgErros.append("|Campo cidade é obrigatório");

		}
		
		if (endereco.getTipoResidencia() == null || endereco.getTipoResidencia().getTipo().trim().equals("")) {

			msgErros.append("|Campo tipo residência  é obrigatório");

		}
		
		if (endereco.getTipoLogradouro() == null || endereco.getTipoLogradouro().getTipo().trim().equals("")) {

			msgErros.append("|Campo tipo logradouro é obrigatório");

		}
		
		
		if (!msgErros.toString().isEmpty()) {

			this.setMsg(msgErros);
			return false;
		}

		return true;
		
	}

}
