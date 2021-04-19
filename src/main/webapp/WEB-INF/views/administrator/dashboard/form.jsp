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

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalpublictasks"/>
		</th>
		<td>
			<acme:print value="${totalPublicTasks}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalprivatetasks"/>
		</th>
		<td>
			<acme:print value="${totalPrivateTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totaltasks"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPublicPrivateTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-execute-period"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfTaskExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalFinishedTasks"/>
		</th>
		<td>
			<acme:print value="${totalFinishedTasks}"/>
		</td>
	</tr>
	<tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalNonFinishedTasks"/>
		</th>
		<td>
			<acme:print value="${totalNonFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalTaskFinishedAndNotFinished"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfFinishedNonFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.stdDevTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${stdDevTaskExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minExecutionPeriod"/>
		</th>
		<td>
			<acme:print value="${minExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maxExecutionPeriod"/>
		</th>
		<td>
			<acme:print value="${maxExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maxWorkload"/>
		</th>
		<td>
			<acme:print value="${maxWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minWorkload"/>
		</th>
		<td>
			<acme:print value="${minWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageNumberOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfTaskWorkloads}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.stdDevTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${stdDevTaskWorkloads}"/>
		</td>
	</tr>
</table>


