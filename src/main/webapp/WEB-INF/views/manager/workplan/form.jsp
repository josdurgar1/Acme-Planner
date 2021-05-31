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
		<h2><acme:form-selectM code="manager.workplan.form.label.task.unassigned"
			path="tasks">
			<jstl:forEach items="${allTask}" var="task">
				<acme:form-optionScript
					code="${task.title} - Workload: ${task.workload} - Init: ${task.initialMoment} - End: ${task.endMoment} - ${task.visibility}"
					value="${task.id}" />
			</jstl:forEach>
		</acme:form-selectM></h2>
	</jstl:if>

	<jstl:if test="${command == 'show' || command =='publish'}">
		<br>

<h2><acme:message code="manager.workplan.form.label.task.assigned"/></h2>
	<table id="taskTable" class="table table-striped">
	<caption></caption>
		<thead>
			<tr>
				<th id="columnTable" style="width: 14%;"><acme:message code="manager.workplan.form.label.title"/></th>
				<th id="columnTable" style="width: 14%;"><acme:message code="manager.workplan.form.label.workload"/></th>
				<th id="columnTable" style="width: 19%;"><acme:message code="manager.workplan.form.label.init"/></th>
				<th id="columnTable" style="width: 19%;"><acme:message code="manager.workplan.form.label.end"/></th>
				<th id="columnTable" style="width: 14%;"><acme:message code="manager.workplan.form.label.isPublic"/></th>
				<jstl:if test="${command == 'show' && isPublished=='false'}">
				<th id="columnTable" style="width: 10%;"><acme:message code="manager.workplan.form.label.link.unnassign"/></th>
				</jstl:if>
			</tr>
		</thead>
		<tbody>
			<jstl:forEach var="task1" items="${tasks}">
				<tr>
					<td><acme:print value="${task1.title}"/></td>
					<td><acme:print value="${task1.workload }"/></td>
					
					<td>
					<acme:print value="${task1.initialMoment}"/>
					
					</td>
					<td>
					<acme:print value="${task1.endMoment}"/>
					</td>
					<td>
					<jstl:if test="${task1.visibility=='PUBLIC'}" >
					<acme:message code="manager.workplan.form.label.public"/>
					</jstl:if>
					<jstl:if test="${task1.visibility=='PRIVATE'}" >
					<acme:message code="manager.workplan.form.label.nopublic"/>
					</jstl:if>
					</td>
					<jstl:if test="${command == 'show' && isPublished=='false'}">
					<td>
					<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.label.link.unnassign"  action="/management/workplan/unnassign?tId=${task1.id}&wId=${id}"/>
					</td>
					</jstl:if>
				</tr>
			</jstl:forEach>
		</tbody>
	</table>


	</jstl:if>


	<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.button.update" action="/management/workplan/update"/>
	<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.button.delete" action="/management/workplan/delete"/>
	<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.button.publish" action="/management/workplan/publish"/>
	<acme:form-submit test="${command == 'create'}" code="manager.workplan.form.button.create" action="/management/workplan/create"/>
	<acme:form-submit test="${command == 'publish'}" code="manager.workplan.form.button.publish" action="/management/workplan/publish"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.workplan.form.button.delete" action="/management/workplan/delete"/>		
	<acme:form-return code="manager.workplan.form.button.return"/>	
</acme:form>