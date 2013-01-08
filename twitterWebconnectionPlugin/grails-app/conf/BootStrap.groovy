import frontlinesms2.*

class BootStrap {

	def init = { servletContext ->
		def twitterWebconnection = new TwitterWebconnection(name:'twitter tester', url:'http://api.twitter.org', apiEnabled:true, httpMethod:Webconnection.HttpMethod.POST ,consumerKey:'B1yaRzVCXDL8zRWu6GXg', consumerSecret:'faquiweicPvD3BymNzGLLAOWx7yXX85BCfIwPYgqjE', accessToken:'99170991-uT9lYg3Cp3ekco1gex7Mh8ifHA5lo7UWclCZS6v3F', accessTokenSecret:'ImQ0yEU1SXvT6CyAPuj9yI6TqwTBGLufZLKDMFzbGQ')
		twitterWebconnection.addToKeywords(new Keyword(value:'SEMA'))
		twitterWebconnection.save(failOnError:true)

		println "## WEB_CONNECTION_TYPE_MAP #before# # ${WebconnectionController.WEB_CONNECTION_TYPE_MAP}"
		Webconnection.implementations << TwitterWebconnection
		WebconnectionController.WEB_CONNECTION_TYPE_MAP.twitter = TwitterWebconnection
		println "## WEB_CONNECTION_TYPE_MAP #after# # ${WebconnectionController.WEB_CONNECTION_TYPE_MAP}"

		println "Twitter4jService #boot# ${org.twitter4j.grails.plugin.Twitter4jService}"
		println "FTwitterService #boot# ${FTwitterService}"
	}

	def destroy = {
	}
}
