package pns.fconnection

import frontlinesms2.*
import org.apache.camel.*

class PNSPreProcessor implements Processor {
	public void process(Exchange x) throws Exception {
		def log = { println "PNSPreProcessor.process() : $it" }
		log 'ENTRY'
		
		log "Calculating connection ID..."
		def connectionId = x.fconnectionId
		log "connectionId=$connectionId"
		def connection = PhoneNetworkSimulatorFconnection.get(connectionId)

		// URL-encode body
		def d = x.in.body
		log "sending message ${d.text}"
		x.in.headers[Exchange.HTTP_PATH] = connection.pnsBaseUrl + "/modem/${connection.phoneNumber}/send/"
		x.in.headers[Exchange.HTTP_METHOD] = "POST"
		x.in.headers['frontlinesms.dispatch.id'] = d.id
		x.in.headers[Exchange.CONTENT_TYPE] = 'application/x-www-form-urlencoded'
		x.in.body = "recipient=${urlEncode(d.dst)}&text=${urlEncode(d.text)}"
				
		log "connection=$connection"
		log "EXIT::: EXCHANGE IS ${x.in.headers}, especially the http path header, which is ${x.in.headers[Exchange.HTTP_PATH]}"
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

