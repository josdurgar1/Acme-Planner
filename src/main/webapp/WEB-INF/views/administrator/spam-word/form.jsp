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
	<acme:form-textbox code ="administrator.spam-word.form.label.word" path="word"/>
	
	<acme:form-submit test="${command == 'create'}" code="administrator.spam-word.form.button.create" action="/administrator/spam-word/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.spam-word.form.button.update" action="/administrator/spam-word/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.spam-word.form.button.update" action="/administrator/spam-word/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.spam-word.form.button.delete" action="/administrator/spam-word/delete"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.spam-word.form.button.delete" action="/administrator/spam-word/delete"/>
	<acme:form-return code="administrator.spam-word.form.button.return"/>
	
</acme:form>

