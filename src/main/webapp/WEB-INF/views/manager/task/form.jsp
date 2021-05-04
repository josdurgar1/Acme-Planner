<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	
	<acme:form-textbox code ="manager.task.form.label.title" path="title"/>
	<acme:form-moment code ="manager.task.form.label.initialMoment" path="initialMoment"/>
	<acme:form-moment code ="manager.task.form.label.endMoment" path="endMoment"/>
	<acme:form-double code ="manager.task.form.label.workload" path="workload"/>
	<acme:form-textarea code ="manager.task.form.label.description" path="description"/>
	<acme:form-url code ="manager.task.form.label.link" path="link"/>
	<jstl:if test="${command == 'show'}" >
	<acme:form-double code ="manager.task.form.label.executionPeriod" path="executionPeriod" readonly='true'/>
	</jstl:if>
	<acme:form-select path="visibility" code ="manager.task.form.label.visibility" >
		<acme:form-option code="PUBLIC" value="PUBLIC" selected="${visibility=='PUBLIC'}"/>
		<acme:form-option code="PRIVATE" value="PRIVATE" selected="${visibility=='PRIVATE'}"/>
	</acme:form-select>
	
	<acme:form-submit test="${command == 'show' && checkP == 'true' && checkF == 'false'}" code="manager.task.form.button.update" action="/management/task/update"/>
	<acme:form-submit test="${command == 'show' && checkP == 'true' && checkF == 'false'}" code="manager.task.form.button.delete" action="/management/task/delete"/>
	
	
	<acme:form-submit test="${command == 'create'}" code="manager.task.form.button.create" action="/management/task/create"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.task.form.button.delete" action="/management/task/delete"/>
	<acme:form-submit test="${command == 'update'}" code="manager.task.form.button.update" action="/management/task/update"/>
	<acme:form-return code="manager.task.form.button.return"/>
	
</acme:form>
