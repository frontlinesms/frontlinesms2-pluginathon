package pns.fconnection

import frontlinesms2.camel.*
import frontlinesms2.*

import org.apache.camel.Exchange
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.RouteDefinition
import frontlinesms2.camel.exception.*

class PhoneNetworkSimulatorFconnection extends Fconnection {
	static final configFields = [name:null, pnsBaseUrl:null, phoneNumber:null]
	static final defaultValues = [pnsBaseUrl:'http://localhost:8080/PhoneNetworkSimulator']
	static passwords = []
	static String getShortName() { 'phonenetworksimulator' }
	
	String pnsBaseUrl
	String phoneNumber
	String name
    static constraints = {
    	pnsBaseUrl(nullable:false, blank:false)
    	phoneNumber(nullable:false, blank:false)
    }

    List<RouteDefinition> getRouteDefinitions() {
		return new RouteBuilder() {
			@Override void configure() {}
			List getRouteDefinitions() {
				return [from("seda:out-${PhoneNetworkSimulatorFconnection.this.id}")
						.onException(Exception)
							.handled(false)
							.end()
						.setHeader(Fconnection.HEADER_FCONNECTION_ID, simple(PhoneNetworkSimulatorFconnection.this.id.toString()))
						.process(new PNSPreProcessor())
						.to(PhoneNetworkSimulatorFconnection.this.pnsBaseUrl + "/modem/${PhoneNetworkSimulatorFconnection.this.phoneNumber}/send/")
						.process(new PNSPostProcessor())
						.routeId("out-internet-${PhoneNetworkSimulatorFconnection.this.id}")]
			}
		}.routeDefinitions
	}
}
