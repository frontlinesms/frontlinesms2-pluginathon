import ketchup.*
import frontlinesms2.*

class BootStrap {

    def init = { servletContext ->
    	["Heinz", "Peptang", "Kengold"].each { brandName ->
    		new Ketchup(brand:brandName, viscosity:1, ingredients:"tomatoes, secrets").save(failOnError:true, flush:true)
    	}
		def poll1 = new Poll(name: 'Ketchup', question:"Which is the best tomato sauce?", sentMessageText:"Which is best?", autoreplyText:"Thank you for participating in the ketchup poll")
		poll1.addToResponses(key:'A', value:'heinz')
		poll1.addToResponses(key:'B', value:'peptang')
		poll1.addToKeywords(new Keyword(isTopLevel:true, value:"HEINZ", ownerDetail:'A')).addToKeywords(new Keyword(isTopLevel:true, value:"PEPTANG", ownerDetail:'B'))
		poll1.addToResponses(PollResponse.createUnknown())
		poll1.save(failOnError:true, flush:true)
    }
    def destroy = {
    }
}
