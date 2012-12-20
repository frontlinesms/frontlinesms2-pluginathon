
<%@ page import="ketchup.Ketchup" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ketchup.label', default: 'Ketchup')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-ketchup" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-ketchup" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="brand" title="${message(code: 'ketchup.brand.label', default: 'Brand')}" />
					
						<g:sortableColumn property="ingredients" title="${message(code: 'ketchup.ingredients.label', default: 'Ingredients')}" />
					
						<g:sortableColumn property="viscosity" title="${message(code: 'ketchup.viscosity.label', default: 'Viscosity')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${ketchupInstanceList}" status="i" var="ketchupInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ketchupInstance.id}">${fieldValue(bean: ketchupInstance, field: "brand")}</g:link></td>
					
						<td>${fieldValue(bean: ketchupInstance, field: "ingredients")}</td>
					
						<td>${fieldValue(bean: ketchupInstance, field: "viscosity")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ketchupInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
