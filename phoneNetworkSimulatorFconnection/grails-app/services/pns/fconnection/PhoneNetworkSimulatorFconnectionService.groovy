package pns.fconnection

import frontlinesms2.camel.*
import frontlinesms2.*
import org.apache.camel.Exchange
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.RouteDefinition
import frontlinesms2.camel.exception.*
import frontlinesms2.api.*

class PhoneNetworkSimulatorFconnectionService {

    def apiProcess(connection, controller) {
		controller.render(generateApiResponse(connection, controller))
	}

	def generateApiResponse(connection, controller) {
		def params = controller.params
		println "PARAMS::: $params"
		if (!params.text || !params.from)
			return [success:'false']
		sendMessageAndHeaders('seda:incoming-fmessages-to-store',
			new Fmessage(inbound:true, src:params.from, text:params.text),
			['fconnection-id':connection.id])
		return [success:'true']
	}
}
