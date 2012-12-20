package frontlinesms2

import frontlinesms2.camel.nexmo.*

import org.apache.camel.Exchange
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.RouteDefinition
import frontlinesms2.camel.exception.*

class NexmoFconnection extends Fconnection {
	private static final String NEXMO_URL = 'http://rest.nexmo.com/sms/json?'
	static final configFields = [name:null, api_key:null, api_secret:null, fromNumber:null]
	static final defaultValues = []
	static String getShortName() { 'nexmo' }
	
	String api_key
	String api_secret
	String fromNumber
	
	static passwords = []
	
	static constraints = {
		api_key blank:false
		api_secret blank:false
		fromNumber blank:false
	}
	
	List<RouteDefinition> getRouteDefinitions() {
		return new RouteBuilder() {
			@Override void configure() {}
			List getRouteDefinitions() {
				return [from("seda:out-${NexmoFconnection.this.id}")
						.onException(AuthenticationException, InvalidApiIdException, InsufficientCreditException)
									.handled(true)
									.beanRef('fconnectionService', 'handleDisconnection')
									.end()
						.setHeader(Fconnection.HEADER_FCONNECTION_ID, simple(NexmoFconnection.this.id.toString()))
						.process(new NexmoPreProcessor())
						.setHeader(Exchange.HTTP_QUERY,
								simple('api_key=${header.nexmo.api_key}&' +
										'api_secret=${header.nexmo.api_secret}&' + 
										'from=${header.nexmo.fromNumber}&' + 
										'to=${header.nexmo.dst}&' +
										'text=${body}'))
						.to(NEXMO_URL)
						.process(new NexmoPostProcessor())
						.routeId("out-internet-${NexmoFconnection.this.id}")]
			}
		}.routeDefinitions
	}
}
