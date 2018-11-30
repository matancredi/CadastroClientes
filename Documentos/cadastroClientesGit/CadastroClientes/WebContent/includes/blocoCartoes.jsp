<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<div data-identificador="blocoBaseCartao" class="alert alert-secondary">
	<input type="hidden" value="${param.id}"
			name="cartoes[${param.indicesCartoes}]['id']">
	<div class="form-group">
		<input type="text" value="${param.numCartao}" class="form-control"
			name="cartoes[${param.indicesCartoes}]['numCartao']" placeholder="Número">
	</div>
	<div class="form-group">
		<input type="text" value="${param.nomeImpresso}" class="form-control"
			name="cartoes[${param.indicesCartoes}]['nomeImpresso']" placeholder="Nome Impresso">
	</div>
	<div class="form-group">
		<input type="text" value="${param.codSeguranca}" class="form-control"
			tag="inteiro5" name="cartoes[${param.indicesCartoes}]['codSeguranca']"
			placeholder="Código de Segurança">
	</div>
	<div class="form-group">
		
		<input type="text" value="${param.dataVencimento}" class="form-control" tag="date" name="cartoes[${param.indicesCartoes}]['dataVencimento']" placeholder="Data de Vencimento">
	</div>
</div>
<hr>

