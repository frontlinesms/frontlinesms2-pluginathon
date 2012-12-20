package pns.fconnection

import frontlinesms2.*
import org.apache.camel.*
import frontlinesms2.camel.exception.*

class PNSPostProcessor implements Processor {
	public void process(Exchange exchange) throws Exception {
		def log = { println "PNSProcessor.process() : $it" }
		log 'PNSPostProcessor works'
	}
}