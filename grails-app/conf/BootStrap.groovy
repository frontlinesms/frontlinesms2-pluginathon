import ketchup.*

class BootStrap {

    def init = { servletContext ->
    	["Heinz", "Peptang", "Kengold"].each { brandName ->
    		new Ketchup(brand:brandName, viscosity:1, ingredients:"tomatoes, secrets").save(failOnError:true, flush:true)
    	}
    }
    def destroy = {
    }
}
