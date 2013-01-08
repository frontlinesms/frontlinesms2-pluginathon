import pns.*
import pns.fconnection.*
import frontlinesms2.*
import pns.fconnection.PhoneNetworkSimulatorFconnection

class BootStrap {
	def init = { servletContext ->
		Fconnection.implementations << PhoneNetworkSimulatorFconnection

		new PhoneNetworkSimulatorFconnection(name:"My PNS", pnsBaseUrl:'http://192.168.0.106:8888/PhoneNetworkSimulator', phoneNumber:'000').save(failOnError:true, flush:true)
	}

	def destroy = {
	}
}

