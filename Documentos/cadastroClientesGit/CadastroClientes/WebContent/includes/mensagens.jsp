<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<c:if test = "${not empty param.erro}">
	<div class="col-4 offset-4 mt-2 text-center  alert ${ param.erro ? 'alert-primary' : 'alert-danger' }" role="alert">
	   <c:forTokens items = "${ param.msg }" delims = "|" var = "texto">
	   	<c:out value = "${texto}"/><br>
	   </c:forTokens>
	</div>
</c:if>