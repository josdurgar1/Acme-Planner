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
	<jstl:if test="${command == 'show'}" >
	<acme:form-double readonly="true" code="manager.workplan.form.label.workload" path="workload"/>	
	</jstl:if>
	<acme:form-moment code="manager.workplan.form.label.init" path="init"/>
	<acme:form-moment code="manager.workplan.form.label.end" path="end"/>
	<jstl:if test="${command == 'show'}" >
	<acme:form-double readonly="true" code="manager.workplan.form.label.executionPeriod" path="executionPeriod"/>	
	</jstl:if>
	<acme:form-select code="manager.workplan.form.label.isPublic" path="isPublic">
		<acme:form-option code="manager.workplan.form.label.true" value="true" selected="${isPublic == 'true'}"/>
		<acme:form-option code="manager.workplan.form.label.false" value="false" selected="${isPublic == 'false'}"/>
	</acme:form-select>
	<acme:form-select code="manager.workplan.form.label.isPublished" path="isPublished">
		<acme:form-option code="manager.workplan.form.label.false" value="false" selected="${isPublished == 'false'}"/>
		<acme:form-option code="manager.workplan.form.label.true" value="true" selected="${isPublished == 'true'}"/>
	</acme:form-select>

<h2><acme:message code="manager.workplan.form.label.task.assigned"/></h2>
	<table id="taskTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 35%;"><acme:message code="manager.workplan.form.label.title"/></th>
				<th style="width: 35%;"><acme:message code="manager.workplan.form.label.workload"/></th>
				<jstl:if test="${isPublished=='false'}" >
				<th style="width: 30%;"><acme:message code="manager.workplan.form.label.link.unnassign"/></th>
		 		</jstl:if>
			</tr>
		</thead>
		<tbody>
			<jstl:forEach var="task" items="${tasks}">
				<tr>
					<td>${task.title}</td>
					<td><acme:print value="${task.workload }"/></td>
					<jstl:if test="${isPublished=='false'}" >
					<td>
                	
            <acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.label.link.unnassign" action="/manager/workplan/unnassign?tId=${aTasks.id}&wId=${id}"/>
                </td>
                </jstl:if>
				</tr>
			</jstl:forEach>
		</tbody>
	</table>

 <h2><acme:message code="manager.workplan.form.label.task.unassigned"/></h2>
	<table id="taskTable" class="table table-striped">
		<thead>
			<tr>
				<th style="width: 35%;"><acme:message code="manager.workplan.form.label.title"/></th>
				<th style="width: 35%;"><acme:message code="manager.workplan.form.label.workload"/></th>
				<jstl:if test="${isPublished=='false'}" >
				<th style="width: 30%;"><acme:message code="manager.workplan.form.label.link.assign"/></th>
		 		</jstl:if>
			</tr>
		</thead>
		<tbody>
			<jstl:forEach var="aTasks" items="${unnasignedTask}">
				<tr>
					<td>${aTasks.title}</td>
					<td><acme:print value="${aTasks.workload }"/></td>
					<jstl:if test="${isPublished=='false'}" >
					<td>
                	<!-- <a href="manager/workplan/assign?tId=${aTasks.id}&wId=${id}"><acme:message code="manager.workplan.form.label.link.assign"/></a> -->
           			 <acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.label.link.assign" action="/manager/workplan/assign?tId=${aTasks.id}&wId=${id}"/>
                </td>
                </jstl:if>
				</tr>
			</jstl:forEach>
		</tbody>
	</table>
	
	

	<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.button.update" action="/manager/workplan/update"/>
	<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.button.delete" action="/manager/workplan/delete"/>
	<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.button.publish" action="/manager/workplan/publish"/>
	<acme:form-submit test="${command == 'create'}" code="manager.workplan.form.button.create" action="/manager/workplan/create"/>
	<acme:form-submit test="${command == 'update'}" code="manager.workplan.form.button.update" action="/manager/workplan/update"/>
	<acme:form-submit test="${command == 'publish'}" code="manager.workplan.form.button.publish" action="/manager/workplan/publish"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.workplan.form.button.delete" action="/manager/workplan/delete"/>		
	<acme:form-return code="manager.workplan.form.button.return"/>	
</acme:form>

