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

<acme:form readonly="true">
	<acme:form-textbox code="manager.workplan.form.label.title" path="title"/>
	<acme:form-double code="manager.workplan.form.label.workload" path="workload"/>	
	<acme:form-moment code="manager.workplan.form.label.init" path="init"/>
	<acme:form-moment code="manager.workplan.form.label.end" path="end"/>
	<acme:form-textbox code="manager.workplan.form.label.isPublic" path="isPublic"/>
	<acme:form-textbox code="manager.workplan.form.label.isPublished" path="isPublished"/>
	
	<acme:form-submit test="${command == 'show'}" code="manager.workplan.form.button.apply" method="get" action="/manager/application/create?workplanId=${id}"/>
	<acme:form-return code="manager.workplan.form.button.return"/>	
</acme:form>

