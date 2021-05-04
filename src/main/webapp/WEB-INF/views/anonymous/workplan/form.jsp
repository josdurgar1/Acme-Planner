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
		<acme:form-textbox code="anonymous.workplan.form.label.manager" path="manager.name"/>	
		
		<h2><acme:message code="manager.workplan.form.label.task.assigned"/></h2>
	<table id="taskTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 15%;"><acme:message code="manager.workplan.form.label.title"/></th>
				<th style="width: 15%;"><acme:message code="manager.workplan.form.label.workload"/></th>
				<th style="width: 20%;"><acme:message code="manager.workplan.form.label.init"/></th>
				<th style="width: 20%;"><acme:message code="manager.workplan.form.label.end"/></th>
				<th style="width: 15%;"><acme:message code="manager.workplan.form.label.isPublic"/></th>
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
				</tr>
			</jstl:forEach>
		</tbody>
	</table>
		<acme:form-return code="anonymous.task.form.button.return"/>	
</acme:form>