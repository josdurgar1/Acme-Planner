<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.spam.list.title.spamListing"/>
</h2>

<acme:list>
			<acme:list-column code="administrator.spam.list.label.spamWords" path="word" width="60%"/>
</acme:list>
	
	
	
	 <%-- test="${command == 'show' && finalMode == 'false' }" --%>
	
	