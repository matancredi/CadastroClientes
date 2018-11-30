<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div class="form-group">
	<input type="text" class="form-control"
		name="endereco_${param.tipoEndereco}_logradouro" id="endereco_${param.tipoEndereco}_logradouro"
		placeholder="Logradouro" maxlength="150">
</div>
<div class="form-group">
	<select class="form-control" name="endereco_${param.tipoEndereco}_tipo_logradouro"
		id="endereco_${param.tipoEndereco}_tipo_logradouro">
		<option value="">Selecione o tipo do logradouro</option>
		<c:if test="${param.ListaTipoLogradouro.size() > 0}">
			<c:forEach var="logradouro" items="${param.ListaTipoLogradouro}">
				<option value="${logradouro.id}">${logradouro.tipo}</option>
			</c:forEach>
		</c:if>

	</select>
</div>
<div class="form-group">
	<select class="form-control" name="endereco_${param.tipoEndereco}_tipo_residencia"
		id="endereco_${param.tipoEndereco}_tipo_residencia">
		<option value="">Selecione o tipo da residência</option>
		<c:if test="${param.ListaTipoResidencia.size() > 0}">
			<c:forEach var="residencia" items="${param.ListaTipoResidencia}">
				<option value="${residencia.id}">${residencia.tipo}</option>
			</c:forEach>
		</c:if>

	</select>
</div>

<div class="float-left col-3">
	<div class="form-group">
		<input type="text" class="form-control" tag="inteiro5"
			name="endereco_cobranca_numero" maxlength="5"
			id="endereco_cobranca_numero" placeholder="Número">
	</div>
</div>
<div class="float-right col-9">
	<div class="form-group">
		<input type="text" class="form-control"
			name="endereco_${param.tipoEndereco}_bairro" maxlength="50"
			id="endereco_${param.tipoEndereco}_bairro" placeholder="Bairro">
	</div>
</div>
<div class="clearfix"></div>
<div class="col-6">
	<div class="form-group">
		<input type="text" class="form-control" tag="cep"
			name="endereco_${param.tipoEndereco}_cep" maxlength="9" id="endereco_${param.tipoEndereco}_cep"
			placeholder="Cep">
	</div>
</div>
<div>
	<div class="flot-left col-6">
		<div class="form-group">
			<select class="form-control" name="endereco_${param.tipoEndereco}_estado"
				id="endereco_${param.tipoEndereco}_estado">
				<option value="">Selecione o estado</option>
				<c:if test="${param.ListaEstados.size() > 0}">
					<c:forEach var="estado" items="${param.ListaEstados}">
						<option value="${estado.id}">${estado.nome}</option>
					</c:forEach>
				</c:if>

			</select>
		</div>
	</div>
	<div class="flot-left col-8">
		<div class="form-group">
			<select class="form-control" name="endereco_${param.tipoEndereco}_cidade"
				id="endereco_${param.tipoEndereco}_cidade">
				<option>Selecione a cidade</option>
			</select>
		</div>
	</div>
</div>
