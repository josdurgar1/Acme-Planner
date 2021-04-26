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
	<acme:message code="administrator.chart.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
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


