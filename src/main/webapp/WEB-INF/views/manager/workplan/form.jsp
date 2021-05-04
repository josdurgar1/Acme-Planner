<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form>
	
	<acme:form-textbox code="manager.workplan.form.label.title" path="title"/>
	<jstl:if test="${command == 'show' || command == 'assign' || command == 'unnassign'}" >
	<acme:form-double readonly="true" code="manager.workplan.form.label.workload" path="workload"/>	
	</jstl:if>
	<acme:form-moment code="manager.workplan.form.label.init" path="init"/>
	<jstl:if test="${suggestionInit!=null }">
	<div id="suggestion" class="text-info">
	<acme:message code="manager.workplan.form.label.suggestionInit"/><acme:print value="${suggestionInit}"/>
	</div>
	</jstl:if>
	<acme:form-moment code="manager.workplan.form.label.end" path="end"/>
	<jstl:if test="${suggestionEnd!=null }">
	<div id="suggestion" class="text-info">
	<acme:message code="manager.workplan.form.label.suggestionEnd"/><acme:print value="${suggestionEnd}"/>
	</div>
	</jstl:if>
	<jstl:if test="${command == 'show'}" >
	<acme:form-double readonly="true" code="manager.workplan.form.label.executionPeriod" path="executionPeriod"/>	
	</jstl:if>
	<acme:form-select code="manager.workplan.form.label.isPublic" path="isPublic">
		<acme:form-option code="manager.workplan.form.label.true" value="true" selected="${isPublic == 'true'}"/>
		<acme:form-option code="manager.workplan.form.label.false" value="false" selected="${isPublic == 'false'}"/>
	</acme:form-select>
	<acme:form-selectwo code="manager.workplan.form.label.isPublished" path="isPublished">
		<acme:form-option code="manager.workplan.form.label.false" value="false" selected="${isPublished == 'false'}"/>
		<acme:form-option code="manager.workplan.form.label.true" value="true" selected="${isPublished == 'true'}"/>
	</acme:form-selectwo>
	
	<jstl:if test="${command == 'show' && isPublished=='false'}">
		<acme:form-selectM code="manager.workplan.form.label.task"
			path="tasks">
			<acme:form-option code="manager.workplan.form.label.noTask" value=""/>
			<jstl:forEach items="${allTask}" var="task">
				<acme:form-option
					code="${task.title} - Workload: ${task.workload} - Init: ${task.initialMoment} - End: ${task.endMoment} - ${task.visibility}"
					value="${task.id}" />
			</jstl:forEach>
		</acme:form-selectM>
	</jstl:if>

	<jstl:if test="${command == 'show' || command =='publish'}">
		<br>

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


	</jstl:if>


	<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.button.update" action="/manager/workplan/update"/>
	<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.button.delete" action="/manager/workplan/delete"/>
	<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.button.publish" action="/manager/workplan/publish"/>
	<acme:form-submit test="${command == 'create'}" code="manager.workplan.form.button.create" action="/manager/workplan/create"/>
	<acme:form-submit test="${command == 'update'}" code="manager.workplan.form.button.update" action="/manager/workplan/update"/>
	<acme:form-submit test="${command == 'publish'}" code="manager.workplan.form.button.publish" action="/manager/workplan/publish"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.workplan.form.button.delete" action="/manager/workplan/delete"/>		
	<acme:form-return code="manager.workplan.form.button.return"/>	
</acme:form>

