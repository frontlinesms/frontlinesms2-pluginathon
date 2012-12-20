import frontlinesms2.*

class BootStrap {

    def init = { servletContext ->        
        def oldImplementations = frontlinesms2.Fconnection.implementations
        frontlinesms2.Fconnection.metaClass.static.getImplementations = { ->
            return oldImplementations << NexmoFconnection
        }
        new NexmoFconnection(name:'FrontlineNEXMOOOOO', api_key:'00d5dea9', api_secret:'0bf8f7e8', fromNumber:"123456789").save(failOnError:true)
    }

    def destroy = {
    }
}
