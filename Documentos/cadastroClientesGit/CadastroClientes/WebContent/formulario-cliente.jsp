<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %>

<html lang="pt-BR">
<jsp:include page="includes/head.jsp"></jsp:include>
<body>
<jsp:include page="includes/menu.jsp" />

<jsp:include page="includes/mensagens.jsp">
	<jsp:param value="${sessionScope.erro}" name="erro"/>
	<jsp:param value="${sessionScope.msg}" name="msg"/>
</jsp:include>

<div class="col-12">

	<c:if test = "${result != null}">
		<div class="alert ${ result ? 'alert-primary' : 'alert-danger' }" role="alert">
		  <c:out value="${ msg }" />
		</div>
	</c:if>

	<div class="card col-6 offset-3 mt-2">
		<form action="cliente?operacao=salvar" method="post" class="card-body">
			<input name="id" id="id" type="hidden" value="${Cliente.id}">
			<div class="form-group">
		    	<input type="text" value="${Cliente.nome}" class="form-control" name="nome" id="nome" placeholder="Nome do cliente">
			</div>
			<div class="form-group">
				<c:if test="${ not empty Cliente.dataNascimento }">
					<fmt:formatDate value="${Cliente.dataNascimento}" pattern="dd/MM/yyyy" var="dataNasc" />
				</c:if>
			
		    	<input type="text" value="${not empty dataNasc ? dataNasc: '' }" class="form-control" tag="date" name="dataNascimento" id="dataNascimento" placeholder="Data de nascimento">
			</div>
			<div class="form-group">
		    	<input type="text" value="${Cliente.cpf}"  class="form-control" tag="cpf" name="cpf" id="cpf" placeholder="CPF">
			</div>
			
			<div class="float-left col-3" >
				<div class="form-group">
			    	<input type="text" value="${Cliente.getDDD()}"   class="form-control" tag="inteiro2" name="ddd" id="ddd" placeholder="DDD">
				</div>
			</div>
			<div class="float-left col-6" >
				<div class="form-group">
			    	<input type="text" value="${Cliente.telefone}"   class="form-control" tag="fone" name="telefone" id="telefone" placeholder="Telefone">
				</div>
			</div>
			<div class="form-group">
		    	<select class="form-control" name="genero" id="genero">
		    		<option value="">Gênero</option>
		    		<option ${Cliente.genero.name().equals('MASCULINO') ? "selected" : ""} value="MASCULINO">Masculino</option>
		    		<option ${Cliente.genero.name().equals('FEMININO') ? "selected" : ""} value="FEMININO">Feminino</option>
		    	</select>
			</div>
			
			<div class="card mb-2">
			    <div class="card-body">
			    	<h5 class="card-title">Endereço de Cobrança</h5>
			    	<input type="hidden" name="endereco_cobranca_id"  value="${Cliente.enderecoCobranca.id}" />
			    	<div class="form-group">
				    	<input type="text" value="${Cliente.enderecoCobranca.logradouro}"  class="form-control" name="endereco_cobranca_logradouro" id="endereco_cobranca_logradouro" placeholder="Logradouro" maxlength="150">
					</div>
					<div class="form-group">
				    	<select class="form-control"  name="endereco_cobranca_tipo_logradouro"  id="endereco_cobranca_tipo_logradouro">
				    		<option value="" >Selecione o tipo do logradouro</option>
				    		<c:if test="${ListaTipoLogradouro.size() > 0}">
				    			<c:forEach var="logradouro" items="${ListaTipoLogradouro}">
				    				<option ${Cliente.enderecoCobranca.tipoLogradouro.id == logradouro.id ? "selected" : ""} value="${logradouro.id}">${logradouro.tipo}</option>
				    			</c:forEach>
				    		</c:if>
				    		
				    	</select>
					</div>
					<div class="form-group">
				    	<select class="form-control"  name="endereco_cobranca_tipo_residencia"  id="endereco_cobranca_tipo_residencia">
				    		<option value="" >Selecione o tipo da residência</option>
				    		<c:if test="${ListaTipoResidencia.size() > 0}">
				    			<c:forEach var="residencia" items="${ListaTipoResidencia}">
				    				<option ${Cliente.enderecoCobranca.tipoResidencia.id == residencia.id ? "selected" : ""} value="${residencia.id}">${residencia.tipo}</option>
				    			</c:forEach>
				    		</c:if>
				    		
				    	</select>
					</div>
					
					<div class="float-left col-3">
						<div class="form-group">
					    	<input type="text" value="${Cliente.enderecoCobranca.numero}" class="form-control" tag="inteiro5" name="endereco_cobranca_numero" maxlength="5" id="endereco_cobranca_numero" placeholder="Número">
						</div>
					</div>
					<div class="float-right col-9">
					    <div class="form-group">
					    	<input type="text" value="${Cliente.enderecoCobranca.bairro}" class="form-control" name="endereco_cobranca_bairro" maxlength="50" id="endereco_cobranca_bairro" placeholder="Bairro">
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="col-6">
						<div class="form-group">
					    	<input type="text" value="${Cliente.enderecoCobranca.cep}" class="form-control" tag="cep" name="endereco_cobranca_cep" maxlength="9" id="endereco_cobranca_cep" placeholder="Cep">
						</div>
					</div>
					<div>
						<div class="flot-left col-6">
							<div class="form-group">
						    	<select class="form-control"  name="endereco_cobranca_estado"  id="endereco_cobranca_estado">
						    		<option value="">Selecione o estado</option>
						    		<c:if test="${ListaEstados.size() > 0}">
						    			<c:forEach var="estado" items="${ListaEstados}">
						    				<option ${Cliente.enderecoCobranca.cidade.estado.id == estado.id ? "selected" : ""} value="${estado.id}">${estado.nome}</option>
						    			</c:forEach>
						    		</c:if>
						    		
						    	</select>
							</div>
						</div>
						<div class="flot-left col-8">
							<div class="form-group">
						    	<select class="form-control"  name="endereco_cobranca_cidade"  id="endereco_cobranca_cidade" >
						    	<option>Selecione a cidade</option>
						    	</select>
							</div>
						</div>
				    </div>
				
					
			    </div>
				
			</div>
			
			<div class="card mb-2">
			    <div class="card-body">
			    	<h5 class="card-title">Endereço de Entrega</h5>
			    	<input type="hidden" name="endereco_entrega_id"  value="${Cliente.enderecoEntrega.id}" />
			    	<div class="form-group">
				    	<input type="text" value="${Cliente.enderecoEntrega.logradouro}" class="form-control" name="endereco_entrega_logradouro" id="endereco_entrega_logradouro" placeholder="Logradouro" maxlength="150">
					</div>
					<div class="form-group">
				    	<select class="form-control"  name="endereco_entrega_tipo_logradouro"  id="endereco_entrega_tipo_logradouro">
				    		<option value="" >Selecione o tipo do logradouro</option>
				    		<c:if test="${ListaTipoLogradouro.size() > 0}">
				    			<c:forEach var="logradouro" items="${ListaTipoLogradouro}">
				    				<option ${Cliente.enderecoEntrega.tipoLogradouro.id == logradouro.id ? "selected" : ""} value="${logradouro.id}">${logradouro.tipo}</option>
				    			</c:forEach>
				    		</c:if>
				    		
				    	</select>
					</div>
					<div class="form-group">
				    	<select class="form-control"  name="endereco_entrega_tipo_residencia"  id="endereco_entrega_tipo_residencia">
				    		<option value="" >Selecione o tipo da residência</option>
				    		<c:if test="${ListaTipoResidencia.size() > 0}">
				    			<c:forEach var="residencia" items="${ListaTipoResidencia}">
				    				<option ${Cliente.enderecoEntrega.tipoResidencia.id == residencia.id ? "selected" : ""} value="${residencia.id}">${residencia.tipo}</option>
				    			</c:forEach>
				    		</c:if>
				    		
				    	</select>
					</div>
					
					<div class="float-left col-3">
						<div class="form-group">
					    	<input type="text" value="${Cliente.enderecoEntrega.numero}"  class="form-control" tag="inteiro5" name="endereco_entrega_numero" maxlength="5" id="endereco_entrega_numero" placeholder="Número">
						</div>
					</div>
					<div class="float-right col-9">
					    <div class="form-group">
					    	<input type="text" value="${Cliente.enderecoEntrega.bairro}"  class="form-control" name="endereco_entrega_bairro" maxlength="50" id="endereco_entrega_bairro" placeholder="Bairro">
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="col-6">
						<div class="form-group">
					    	<input type="text" value="${Cliente.enderecoEntrega.cep}"  class="form-control" tag="cep" name="endereco_entrega_cep" maxlength="9" id="endereco_entrega_cep" placeholder="Cep">
						</div>
					</div>
					<div>
						<div class="flot-left col-6">
							<div class="form-group">
						    	<select class="form-control"  name="endereco_entrega_estado"  id="endereco_entrega_estado">
						    		<option value="">Selecione o estado</option>
						    		<c:if test="${ListaEstados.size() > 0}">
						    			<c:forEach var="estado" items="${ListaEstados}">
						    				<option ${Cliente.enderecoEntrega.cidade.estado.id == estado.id ? "selected" : ""} value="${estado.id}">${estado.nome}</option>
						    			</c:forEach>
						    		</c:if>
						    		
						    	</select>
							</div>
						</div>
						<div class="flot-left col-8">
							<div class="form-group">
						    	<select class="form-control"  name="endereco_entrega_cidade"  id="endereco_entrega_cidade" >
						    	<option>Selecione a cidade</option>
						    	</select>
							</div>
						</div>
				    </div>
				
			    </div>
				
			</div>
			
			<div class="card mb-2">
			    <div class="card-body">
			    	<h5 class="card-title">Cartões</h5>
			    	
			    	<div id="blocoCartoes">
			    		    <c:set var="indicesCartoes" value="1" />
			    		    
			    		    <c:if test="${ empty Cliente.cartoes }">
			    		    	<jsp:include page="includes/blocoCartoes.jsp">
			    		    		<jsp:param value="${indicesCartoes}" name="indicesCartoes"/>
			    		    	</jsp:include>
			    		    </c:if>
			    		    
			    		    <c:if test="${ not empty Cliente.cartoes }">
					    		<c:forEach var="cartao" items="${Cliente.cartoes}">
					    		
					    			<c:if test="${ not empty cartao.dataVencimento }">
										<fmt:formatDate value="${cartao.dataVencimento}" pattern="dd/MM/yyyy" var="dataVencimento" />
									</c:if>
									
					    			<jsp:include page="includes/blocoCartoes.jsp">
					    				<jsp:param value="${cartao.id}" name="id"/>
										<jsp:param value="${cartao.numCartao}" name="numCartao"/>
										<jsp:param value="${cartao.nomeImpresso}" name="nomeImpresso"/>
										<jsp:param value="${cartao.codSeguranca}" name="codSeguranca"/>
										<jsp:param value="${ not empty dataVencimento? dataVencimento:'' }" name="dataVencimento"/>
										<jsp:param value="${indicesCartoes}" name="indicesCartoes"/>
									</jsp:include>
					    			
					    			<c:if test="${ Cliente.cartoes.size() >  indicesCartoes}">
										<c:set var="indicesCartoes" value="${indicesCartoes + 1} " />
									</c:if>
									
					    		</c:forEach>
				    		</c:if>
				    		
				    		<input type="hidden" value="${ indicesCartoes }" name="indicesCartoes" />
			    		
			    		<div class="text-right"><span style="cursor:pointer; color: Dodgerblue" data-operacao="adicionar" ><i class="fas fa-plus fa-2x"></i></span></div>
			    	
					</div>
			    	
			    </div>
			</div>
			
			<div class="col-12 text-right">
			 <button type="submit" class="btn btn-primary btn-sm">Salvar</button>
			</div>
		</form>
	</div>
</div>
<script>
	$(function(){
		
		// recarrega lista de cidades
		
		
		$("select[name*='cobranca_estado']").on("change",function(){
			
			if($(this).val()){
				
				$.ajax({
					  method: "GET",
					  url: "helpers",
					  data: { 'id': $(this).val(),'acao': 'estado-cidade' }
				}).done(function( options ) {
					    if(options){
					    	$("#endereco_cobranca_cidade").empty();
					    	$("#endereco_cobranca_cidade").append(options);
					    	
							if($("#id").val()){
					    		
					    		$("#endereco_cobranca_cidade").val(${Cliente.enderecoCobranca.cidade.id});
					    	}
					    }
				});
				
					
			}
		});
		
		$("select[name*='entrega_estado']").on("change",function(){
			
			if($(this).val()){
				
				$.ajax({
					  method: "GET",
					  url: "helpers",
					  data: { 'id': $(this).val(),'acao': 'estado-cidade' }
				}).done(function( options ) {
					
					    if(options){
					    	$("#endereco_entrega_cidade").empty();
					    	$("#endereco_entrega_cidade").append(options);
					    	
					    	if($("#id").val()){
					    		
					    		$("#endereco_entrega_cidade").val(${Cliente.enderecoEntrega.cidade.id});
					    	}
					    	
					    }
				});
				
			}
			
		});
		
		if($("#id").val()){
			
			$("select[name*='cobranca_estado']").trigger("change");
			$("select[name*='entrega_estado']").trigger("change");
			
		}
		

	/* 	$("#blocoCartoes").data("operacao","adicionar").on("click",function(){
			
			var blocoBaseCartao = $("#blocoCartoes").data("identificador","blocoBaseCartao").clone();
			$('#blocoCartoes').append(blocoBaseCartao);
			
			$("#blocoCartoes").data("operacao", "remover").show()
			
			
		}) */
		
	})

</script>
</body>
</html>