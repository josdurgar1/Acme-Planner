<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
	
<acme:form>
		<acme:form-textbox code="authenticated.task.form.label.title" path="title"/>
		<acme:form-moment code="authenticated.task.form.label.initialMoment" path="initialMoment" />
		<acme:form-moment code="authenticated.task.form.label.endMoment" path="endMoment" />
		<acme:form-moment code="authenticated.task.form.label.workload" path="workload"/>	
		<acme:form-moment code="authenticated.task.form.label.executionPeriod" path="executionPeriod"/>	
		<acme:form-textarea code="authenticated.task.form.label.description" path="description"/>	
		<acme:form-url code="authenticated.task.form.label.link" path="link"/>
		
		<acme:form-return code="authenticated.task.form.button.return"/>	
</acme:form>