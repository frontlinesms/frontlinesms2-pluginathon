import frontlinesms2.*

class BootStrap {

    def init = { servletContext ->
        Fconnection.implementations << NexmoFconnection
    }
    def destroy = {
    }
}
