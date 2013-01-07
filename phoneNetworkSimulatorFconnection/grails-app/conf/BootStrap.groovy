import pns.*
import pns.fconnection.*
import frontlinesms2.*

class BootStrap {

	def init = { servletContext ->
		def oldImplementations = frontlinesms2.Fconnection.implementations
		frontlinesms2.Fconnection.metaClass.static.getImplementations = { ->
			if (!oldImplementations.contains(pns.fconnection.PhoneNetworkSimulatorFconnection))
				oldImplementations = (oldImplementations << pns.fconnection.PhoneNetworkSimulatorFconnection)
			println "AVAILABLE FCONNECTIONS: ${oldImplementations}}"
			return oldImplementations
		}

		new PhoneNetworkSimulatorFconnection(name:"My PNS", pnsBaseUrl:'http://192.168.0.106:8888/PhoneNetworkSimulator', phoneNumber:'000').save(failOnError:true, flush:true)
	}

	def destroy = {
	}
}
