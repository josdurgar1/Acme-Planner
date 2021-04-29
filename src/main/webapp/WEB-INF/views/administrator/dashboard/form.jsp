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
<h2>
	<acme:message code="administrator.dashboard.form.title.task"/>
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
	
	<!-- CHARTS -->
	
	<caption>
		<acme:message code="administrator.chart.form.title.general-indicators"/>
	</caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.chart.form.label.totalNumberWorkplans"/>
		</th>
		<td>
			<acme:print value="${totalNumberWorkplans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.chart.form.label.totalNumberWorkplansPublished"/>
		</th>
		<td>
			<acme:print value="${totalNumberWorkplansPublished}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.chart.form.label.totalNumberWorkplansNonPublished"/>
		</th>
		<td>
			<acme:print value="${totalNumberWorkplansNonPublished}"/>
		</td>
	</tr>
	
</table>

<h2>
	<acme:message code="administrator.dashboard.form.title.workplan"/>
</h2>
<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalpublicworkplan"/>
		</th>
		<td>
			<acme:print value="${totalPublicWorkplan}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalprivateworkplan"/>
		</th>
		<td>
			<acme:print value="${totalPrivateWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalworkplan"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfPublicPrivateWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-execute-period-workplan"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfWorkplanExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalFinishedworkplan"/>
		</th>
		<td>
			<acme:print value="${totalFinishedWorkplan}"/>
		</td>
	</tr>
	<tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalNonFinishedworkplan"/>
		</th>
		<td>
			<acme:print value="${totalNonFinishedWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.totalworkplanFinishedAndNotFinished"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfFinishedNonFinishedWorkplan}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.stdDevworkplanExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${stdDevWorkplanExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minworkplanExecutionPeriod"/>
		</th>
		<td>
			<acme:print value="${minWorkplanExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maxworkplanExecutionPeriod"/>
		</th>
		<td>
			<acme:print value="${maxWorkplanExecutionPeriod}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maxworkplanWorkload"/>
		</th>
		<td>
			<acme:print value="${maxWorkplanWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minworkplanWorkload"/>
		</th>
		<td>
			<acme:print value="${minWorkplanWorkload}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.averageNumberOfworkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${averageNumberOfWorkplanWorkloads}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.stdDevworkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${stdDevWorkplanWorkloads}"/>
		</td>
	</tr>
	
</table>

