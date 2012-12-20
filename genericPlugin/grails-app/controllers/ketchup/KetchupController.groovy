package ketchup

import org.springframework.dao.DataIntegrityViolationException

class KetchupController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [ketchupInstanceList: Ketchup.list(params), ketchupInstanceTotal: Ketchup.count()]
    }

    def create() {
        [ketchupInstance: new Ketchup(params)]
    }

    def save() {
        def ketchupInstance = new Ketchup(params)
        if (!ketchupInstance.save(flush: true)) {
            render(view: "create", model: [ketchupInstance: ketchupInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'ketchup.label', default: 'Ketchup'), ketchupInstance.id])
        redirect(action: "show", id: ketchupInstance.id)
    }

    def show() {
        def ketchupInstance = Ketchup.get(params.id)
        if (!ketchupInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'ketchup.label', default: 'Ketchup'), params.id])
            redirect(action: "list")
            return
        }

        [ketchupInstance: ketchupInstance]
    }

    def edit() {
        def ketchupInstance = Ketchup.get(params.id)
        if (!ketchupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ketchup.label', default: 'Ketchup'), params.id])
            redirect(action: "list")
            return
        }

        [ketchupInstance: ketchupInstance]
    }

    def update() {
        def ketchupInstance = Ketchup.get(params.id)
        if (!ketchupInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ketchup.label', default: 'Ketchup'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (ketchupInstance.version > version) {
                ketchupInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'ketchup.label', default: 'Ketchup')] as Object[],
                          "Another user has updated this Ketchup while you were editing")
                render(view: "edit", model: [ketchupInstance: ketchupInstance])
                return
            }
        }

        ketchupInstance.properties = params

        if (!ketchupInstance.save(flush: true)) {
            render(view: "edit", model: [ketchupInstance: ketchupInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'ketchup.label', default: 'Ketchup'), ketchupInstance.id])
        redirect(action: "show", id: ketchupInstance.id)
    }

    def delete() {
        def ketchupInstance = Ketchup.get(params.id)
        if (!ketchupInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'ketchup.label', default: 'Ketchup'), params.id])
            redirect(action: "list")
            return
        }

        try {
            ketchupInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'ketchup.label', default: 'Ketchup'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ketchup.label', default: 'Ketchup'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
