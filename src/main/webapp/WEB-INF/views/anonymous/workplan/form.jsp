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
		
		<acme:form-return code="anonymous.task.form.button.return"/>	
</acme:form>