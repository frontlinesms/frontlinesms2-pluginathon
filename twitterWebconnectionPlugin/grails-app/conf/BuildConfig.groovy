grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

environments {
	test {
		grails.server.port.http=8081
	}
}

grails.project.dependency.resolution = {
	defaultDependenciesProvided false

	// inherit Grails' default dependencies
	inherits("global") {
		// uncomment to disable ehcache
		// excludes 'ehcache'
	}
	log "trace" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	checksums true // Whether to verify checksums on resolve

	repositories {
		inherits true // Whether to inherit repository definitions from plugins

		grailsHome()
		grailsPlugins()

		mavenLocal()
		//mavenRepo "http://192.168.0.200:8081/artifactory/simple/super-repo/"
		//grailsRepo "http://192.168.0.200:8081/artifactory/simple/super-repo/"
		mavenRepo 'http://dev.frontlinesms.com/m2repo/'
		mavenCentral()

		grailsCentral()

		// uncomment these to enable remote dependency resolution from public Maven repositories
		//mavenCentral()
		//mavenRepo "http://snapshots.repository.codehaus.org"
		//mavenRepo "http://repository.codehaus.org"
		//mavenRepo "http://download.java.net/maven/2/"
		//mavenRepo "http://repository.jboss.com/maven2/"
	}

	dependencies {}
	
	plugins {
		compile ":frontlinesms-core:2.0-SNAPSHOT"
		compile ':twitter4j:0.3.2'
	}
}

coverage {
}

codenarc {
	reportName = 'target/analysis-reports/codenarc.xml'
	reportType = 'xml'
	systemExitOnBuildException = false
	// NB these numbers should be LOWERED over time as code quality should be INCREASING
	maxPriority1Violations = 0
	maxPriority2Violations = 250
	maxPriority3Violations = 500

	properties = {
		GrailsPublicControllerMethod.enabled = false
	}
}
