<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<acme:list>
		<acme:list-column code="anonymous.workplan.list.label.title" path="title" width="20%"/>
		<acme:list-column code="anonymous.workplan.list.label.isPublic" path="isPublic" width="20%"/>
		<acme:list-column code="anonymous.workplan.list.label.isPublished" path="isPublished" width="20%"/>
		<acme:list-column code="anonymous.workplan.list.label.init" path="init" width="20%"/>
		<acme:list-column code="anonymous.workplan.list.label.end" path="end" width="60%"/>	
		<acme:list-column code="anonymous.workplan.list.label.workload" path="workload" width="20%"/>	
</acme:list>
	
	
	
	 <%-- test="${command == 'show' && finalMode == 'false' }" --%>
	
	