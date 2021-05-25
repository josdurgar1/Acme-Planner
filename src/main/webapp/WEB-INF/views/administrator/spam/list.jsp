<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list>
			<acme:list-column code="administrator.spam.form.label.threshold" path="umbral" width="60%"/>
</acme:list>
	
	
	
	 <%-- test="${command == 'show' && finalMode == 'false' }" --%>
	
	