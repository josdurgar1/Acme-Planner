<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<acme:form>
		<acme:form-textbox code="anonymous.workplan.form.label.title" path="title"/>
		<acme:form-textbox code="anonymous.workplan.form.label.isPublic" path="isPublic"/>
		<acme:form-moment code="anonymous.workplan.form.label.isPublished" path="isPublished" />
		<acme:form-moment code="anonymous.workplan.form.label.executionPeriod" path="executionPeriod" />
		<acme:form-moment code="anonymous.workplan.form.label.workload" path="workload"/>	
		<acme:form-moment code="anonymous.workplan.form.label.init" path="init"/>	
		<acme:form-textarea code="anonymous.workplan.form.label.end" path="end"/>
		
		<h2><acme:message code="manager.workplan.form.label.task.assigned"/></h2>
	<table id="taskTable" class="table table-striped">
	<caption></caption>
		<thead>
			<tr>
				<th id="columnTable" style="width: 15%;"><acme:message code="manager.workplan.form.label.title"/></th>
				<th id="columnTable" style="width: 15%;"><acme:message code="manager.workplan.form.label.workload"/></th>
				<th id="columnTable" style="width: 20%;"><acme:message code="manager.workplan.form.label.init"/></th>
				<th id="columnTable" style="width: 20%;"><acme:message code="manager.workplan.form.label.end"/></th>
				<th id="columnTable" style="width: 15%;"><acme:message code="manager.workplan.form.label.isPublic"/></th>
				<th id="columnTable" style="width: 14%;"><acme:message code="manager.workplan.form.label.link.detalles"/></th>
			</tr>
		</thead>
		<tbody>
			<jstl:forEach var="task" items="${tasks}">
				<tr>
					<td><acme:print value="${task.title}"/></td>
					<td><acme:print value="${task.workload }"/></td>
					
					<td>
					<acme:print value="${task.initialMoment}"/>
					
					</td>
					<td>
					<acme:print value="${task.endMoment}"/>
					</td>
					<td>
					<jstl:if test="${task.visibility=='PUBLIC'}" >
					<acme:message code="manager.workplan.form.label.public"/>
					</jstl:if>
					<jstl:if test="${task.visibility=='PRIVATE'}" >
					<acme:message code="manager.workplan.form.label.nopublic"/>
					</jstl:if>
					</td>
					<td>
				
					<a 	class="btn btn-primary active" role="button" href="anonymous/task/show?id=${task.id}" class="nav-link"><acme:message code="manager.workplan.form.label.link.detalles"/></a>
					</td>
				</tr>
			</jstl:forEach>
		</tbody>
	</table>
		<acme:form-return code="anonymous.task.form.button.return"/>	
</acme:form>
