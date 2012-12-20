<%@ page import="ketchup.Ketchup" %>



<div class="fieldcontain ${hasErrors(bean: ketchupInstance, field: 'brand', 'error')} ">
	<label for="brand">
		<g:message code="ketchup.brand.label" default="Brand" />
		
	</label>
	<g:textField name="brand" value="${ketchupInstance?.brand}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ketchupInstance, field: 'ingredients', 'error')} ">
	<label for="ingredients">
		<g:message code="ketchup.ingredients.label" default="Ingredients" />
		
	</label>
	<g:textField name="ingredients" value="${ketchupInstance?.ingredients}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ketchupInstance, field: 'viscosity', 'error')} required">
	<label for="viscosity">
		<g:message code="ketchup.viscosity.label" default="Viscosity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="viscosity" required="" value="${fieldValue(bean: ketchupInstance, field: 'viscosity')}"/>
</div>

