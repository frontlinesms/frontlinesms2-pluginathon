package frontlinesms2

import org.twitter4j.grails.plugin.*
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

class FTwitterService extends Twitter4jService {
    FTwitterService(){
        super()
    }
    
	def connect(twitterWebconnection) {
        def twitterConfiguration = getTwitterConfiguration(twitterWebconnection)

        ConfigurationBuilder cb = new ConfigurationBuilder();
        twitterConfiguration.each { key, value ->
            cb."$key" = value
        }
        def twitterFactory = new TwitterFactory(cb.build())
        return twitterFactory.getInstance()
    }

    private getTwitterConfiguration(twitterWebconnection) {
        def configuration = [:]
        configuration.consumerKey = twitterWebconnection.consumerKey
        configuration.consumerSecret = twitterWebconnection.consumerSecret
        configuration.accessToken = twitterWebconnection.accessToken
        configuration.accessTokenSecret = twitterWebconnection.accessTokenSecret
        return configuration
    }
}