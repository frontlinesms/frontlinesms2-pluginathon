class TwitterWebconnectionGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "2.0 > *"
    def dependsOn = [:]
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]
    def title = "Twitter Webconnection"
    def author = "FrontlineSMS"
    def authorEmail = ""
    def description = "A Twitter Webconnection plugin fot FrontlineSMS. Forwards incoming messages to a twitter account feed"
    def documentation = "http://grails.org/plugin/test-plugin"
}
