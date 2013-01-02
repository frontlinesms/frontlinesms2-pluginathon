package frontlinesms2

import org.apache.camel.Exchange

class TwitterWebconnection extends Webconnection {
	static String getType() { 'twitter' }

	String consumerKey = ""
	String consumerSecret = ""
	String accessToken = ""
	String accessTokenSecret = ""

	static constraints = {
	}

	def initialize(params) {
		this.url = "http://www.twitter.com"
		this.httpMethod = Webconnection.HttpMethod.POST
		this.name = params.name

		consumerKey = param.consumerKey
		consumerSecret = param.consumerSecret
		accessToken = param.accessToken
		accessTokenSecret = param.accessTokenSecret
		
		this.apiEnabled = false
		this
	}

	def getServiceType() {
		"twitter"
	}

	def getKey() {
		requestParameters?.find {it.name == "key"}?.value
	}

	def processKeyword(Fmessage message, Keyword k) {
		this.addToMessages(message)
		this.save(failOnError:true)
		//TODO twitter4j config
	}

	def activate() {
		//do nothing
	}

	def deactivate() {
		//does nothing
	}
}

