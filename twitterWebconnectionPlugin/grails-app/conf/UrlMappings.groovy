class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"()
		"/"(controller:'message')
		"500"(view:'/error')
	}
}
