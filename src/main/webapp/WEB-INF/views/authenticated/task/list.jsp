<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<acme:list>
		<acme:list-column code="manager.task.list.label.title" path="title" width="20%"/>
		<acme:list-column code="manager.task.list.label.initialMoment" path="initialMoment" width="20%"/>
		<acme:list-column code="manager.task.list.label.endMoment" path="endMoment" width="20%"/>
		<acme:list-column code="manager.task.list.label.description" path="description" width="60%"/>	
</acme:list>
	
	
	
	 <%-- test="${command == 'show' && finalMode == 'false' }" --%>
	
	