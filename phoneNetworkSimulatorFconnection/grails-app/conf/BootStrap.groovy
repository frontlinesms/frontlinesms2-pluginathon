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
	}

	def destroy = {
	}
}
