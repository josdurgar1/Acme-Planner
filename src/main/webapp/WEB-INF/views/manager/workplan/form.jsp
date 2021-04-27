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


	<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.button.update" action="/manager/workplan/update"/>
	<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.button.delete" action="/manager/workplan/delete"/>
	<acme:form-submit test="${command == 'show' && isPublished == 'false'}" code="manager.workplan.form.button.publish" action="/manager/workplan/publish"/>
	<acme:form-submit test="${command == 'create'}" code="manager.workplan.form.button.create" action="/manager/workplan/create"/>
	<acme:form-submit test="${command == 'update'}" code="manager.workplan.form.button.update" action="/manager/workplan/update"/>
	<acme:form-submit test="${command == 'publish'}" code="manager.workplan.form.button.publish" action="/manager/workplan/publish"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.workplan.form.button.delete" action="/manager/workplan/delete"/>		
	<acme:form-return code="manager.workplan.form.button.return"/>	
</acme:form>

