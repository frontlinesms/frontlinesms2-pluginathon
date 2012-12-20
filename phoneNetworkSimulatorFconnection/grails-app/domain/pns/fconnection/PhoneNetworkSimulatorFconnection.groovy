package pns.fconnection

import frontlinesms2.camel.clickatell.*

import org.apache.camel.Exchange
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.RouteDefinition
import frontlinesms2.camel.exception.*

class PhoneNetworkSimulatorFconnection {
	static final configFields = ['pnsBaseUrl', 'phoneNumber']
	static final defaultValues = []
	static passwords = []
	static String getShortName() { 'pns' }
	
	String pnsBaseUrl
	String phoneNumber
    static constraints = {
    	pnsBaseUrl(nullable:false, blank:false)
    	phoneNumber(nullable:false, blank:false)
    }

    List<RouteDefinition> getRouteDefinitions() {
		return new RouteBuilder() {
			@Override void configure() {}
			List getRouteDefinitions() {
				return [from("seda:out-${PhoneNetworkSimulatorFconnection.this.id}")
						.onException(AuthenticationException, InvalidApiIdException, InsufficientCreditException)
									.handled(true)
									.beanRef('fconnectionService', 'handleDisconnection')
									.end()
						.setHeader(Fconnection.HEADER_FCONNECTION_ID, simple(PhoneNetworkSimulatorFconnection.this.id.toString()))
						.process(new PNSPreProcessor())
						.setHeader(Exchange.HTTP_QUERY,
								simple('recipients=${header.pns.dst}&text=${body}'))
						.to(this.pnsBaseUrl)
						.process(new PNSPostProcessor())
						.routeId("out-internet-${PhoneNetworkSimulatorFconnection.this.id}")]
			}
		}.routeDefinitions
	}
}
