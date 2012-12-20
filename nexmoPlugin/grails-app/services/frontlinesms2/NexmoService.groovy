package frontlinesms2

import grails.converters.JSON

import frontlinesms2.api.*

class NexmoService {
	def apiProcess(connection, controller) {
		controller.render(generateApiResponse(connection, controller) as JSON)
	}

	def generateApiResponse(connection, controller) {
		//if(connection.secret && controller.params.secret != connection.secret) return failure()

		try {
			def payload = handleIncoming(connection, controller.params)
			return [payload:payload]
		} catch(FrontlineApiException ex) {
// FIXME should send a non-200 status code here
			return failure(ex)
		}
	}

	private def handleIncoming(connection, params) {
		//if(!connection.receiveEnabled) throw new FrontlineApiException("Receive not enabled for this connection")

		//if(!params.from || params.message==null) throw new FrontlineApiException('Missing one or both of `from` and `message` parameters');

		/* parse received JSON with the following params:
		     */
		sendMessageAndHeaders('seda:incoming-fmessages-to-store',
				new Fmessage(inbound:true, src:params.msisdn, text:params.text),
				['fconnection-id':connection.id])

		def response = success()
		//if(connection.sendEnabled) response += generateOutgoingResponse(connection, false)
		return response
	}

	private def failure(FrontlineApiException ex=null) {
		if(ex) {
			return [payload:[success:'false', error:ex.message]]
		} else {
			return [payload:[success:'false']]
		}
	}

	private def success(additionalContent=null) {
		def responseMap = [success:'true']
		if(additionalContent) responseMap += additionalContent
		return responseMap
	}
}