package frontlinesms2.camel.nexmo

import frontlinesms2.*
import org.apache.camel.*
import frontlinesms2.camel.exception.*

class NexmoPostProcessor implements Processor {
	public void process(Exchange exchange) throws Exception {
		def log = { println "NexmoPostProcessor.process() : $it" }
		log 'ENTRY'
		log exchange
		log 'EXIT'
	}
}