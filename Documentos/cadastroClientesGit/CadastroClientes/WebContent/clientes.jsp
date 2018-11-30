<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html lang="pt-BR">
<jsp:include page="includes/head.jsp"></jsp:include>

<body>
<jsp:include page="includes/menu.jsp" />
<jsp:include page="includes/mensagens.jsp">
	<jsp:param value="${sessionScope.erro}" name="erro"/>
	<jsp:param value="${sessionScope.msg}" name="msg"/>
</jsp:include>
<c:remove var="erro" scope="session"/>
<c:remove var="msg" scope="session"/>

<div class="col-12 ">
	<div class="col-12 text-right">
		<a href="cliente?operacao=inserir" ><i class="fas fa-plus-square fa-2x"></i></a>
	</div>
	<div class="row">
		<table class="col-12 table table-bordered">
		  <thead>
		    <tr>
		      <th scope="col">Nome</th>
		      <th scope="col">CPF</th>
		      <th scope="col">Cidade</th>
		      <th scope="col">Telefone</th>
		      <th scope="col">&nbsp;</th>
		      <th scope="col">&nbsp;</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:if test = "${Lista.size() > 0}">
		  <c:forEach var = "cliente" items="${Lista}">
		      <tr>
		      	<td>${cliente.nome}</td>
		      	<td>${cliente.cpf}</td>
		      	<td>${cliente.enderecoCobranca.cidade.cidade}</td>
		      	<td>${cliente.telefone}</td>
		      	<td><a href="cliente?operacao=alterar&id=${cliente.id}" ><i class="fas fa-edit"></i></a></td>
		      	<td><a href="cliente?operacao=excluir&id=${cliente.id}" ><i class="fas fa-trash-alt"></i></a></td>
		      </tr>   
		  </c:forEach>
		  </c:if>
		  <c:if test = "${Lista.size() < 1}">
		  	<tr>
		      	<td colspan="6">Não há dados</td>
		    </tr>
		  </c:if>
		  </tbody>
		</table>
	</div>
</div>
</body>
</html>