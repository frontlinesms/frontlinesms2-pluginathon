import ketchup.*
import frontlinesms2.*

class BootStrap {

	def init = { servletContext ->
		def twitterWebconnection = new TwitterWebconnection(name:'twitter tester', url:'http://api.twitter.org', httpMethod:Webconnection.HttpMethod.POST ,consumerKey:'B1yaRzVCXDL8zRWu6GXg', consumerSecret:'faquiweicPvD3BymNzGLLAOWx7yXX85BCfIwPYgqjE', accessToken:'99170991-uT9lYg3Cp3ekco1gex7Mh8ifHA5lo7UWclCZS6v3F', accessTokenSecret:'ImQ0yEU1SXvT6CyAPuj9yI6TqwTBGLufZLKDMFzbGQ')
		twitterWebconnection.save(failOnError:true)
	}
	def destroy = {
	}
}
