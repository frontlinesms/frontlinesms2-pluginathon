package pns.fconnection

import frontlinesms2.*
import org.apache.camel.*

class PNSPreProcessor implements Processor {
	public void process(Exchange x) throws Exception {
		def log = { println "PNSPreProcessor.process() : $it" }
		log 'ENTRY'
		
		// URL-encode body
		def d = x.in.body
		x.out.headers['frontlinesms.dispatch.id'] = d.id
		x.out.body = urlEncode(d.text)
		
		def destination = d.dst
		if(destination && destination.charAt(0)=='+') destination = destination.substring(1)
		set x, 'dst', destination
		
		// Add auth details to header
		log "Calculating connection ID..."
		def connectionId = x.fconnectionId
		log "connectionId=$connectionId"
		def connection = PhoneNetworkSimulatorFconnection.get(connectionId)
		log "connection=$connection"
		['pnsBaseUrl', 'phoneNumber'].each { set x, it, connection."$it" }
		log 'EXIT'
	}
	
	private def set(Exchange x, String header, String value) {
		println "PreProcessor.set() : header=$header; value=$value"
		x.out.headers["pns.$header"] = urlEncode(value)
	}
	
	private String urlEncode(String s) throws UnsupportedEncodingException {
		println "PreProcessor.urlEncode : s=$s -> ${URLEncoder.encode(s, "UTF-8")}"
		return URLEncoder.encode(s, "UTF-8");
	}
}

