<div class="info">
	<p><g:message code="webconnection.twitter.description"/></p>
	<p><g:message code="webconnection.twitter.oauth.description"/></p>
</div>
<div class="input">
	<label for="consumerKey"><g:message code="webconnection.twitter.consumerKey.label"/></label>
	<g:textField name="consumerKey" value="${activityInstanceToEdit?.consumerKey}"/>
	<g:hiddenField name="url" value="${activityInstanceToEdit?.url}" />
</div>
<div class="input">
	<label for="consumerSecret"><g:message code="webconnection.twitter.consumerSecret.label"/></label>
	<g:textField name="consumerSecret" value="${activityInstanceToEdit?.consumerSecret}"/>
</div>
<div class="input">
	<label for="accessToken"><g:message code="webconnection.twitter.accessToken.label"/></label>
	<g:textField name="accessToken" value="${activityInstanceToEdit?.accessToken}"/>
</div>
<div class="input">
	<label for="accessTokenSecret"><g:message code="webconnection.twitter.accessTokenSecret.label"/></label>
	<g:textField name="accessToken" value="${activityInstanceToEdit?.accessTokenSecret}"/>
</div>