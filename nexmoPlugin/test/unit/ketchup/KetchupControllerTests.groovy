package ketchup



import org.junit.*
import grails.test.mixin.*

@TestFor(KetchupController)
@Mock(Ketchup)
class KetchupControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/ketchup/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.ketchupInstanceList.size() == 0
        assert model.ketchupInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.ketchupInstance != null
    }

    void testSave() {
        controller.save()

        assert model.ketchupInstance != null
        assert view == '/ketchup/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/ketchup/show/1'
        assert controller.flash.message != null
        assert Ketchup.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/ketchup/list'


        populateValidParams(params)
        def ketchup = new Ketchup(params)

        assert ketchup.save() != null

        params.id = ketchup.id

        def model = controller.show()

        assert model.ketchupInstance == ketchup
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/ketchup/list'


        populateValidParams(params)
        def ketchup = new Ketchup(params)

        assert ketchup.save() != null

        params.id = ketchup.id

        def model = controller.edit()

        assert model.ketchupInstance == ketchup
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/ketchup/list'

        response.reset()


        populateValidParams(params)
        def ketchup = new Ketchup(params)

        assert ketchup.save() != null

        // test invalid parameters in update
        params.id = ketchup.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/ketchup/edit"
        assert model.ketchupInstance != null

        ketchup.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/ketchup/show/$ketchup.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        ketchup.clearErrors()

        populateValidParams(params)
        params.id = ketchup.id
        params.version = -1
        controller.update()

        assert view == "/ketchup/edit"
        assert model.ketchupInstance != null
        assert model.ketchupInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/ketchup/list'

        response.reset()

        populateValidParams(params)
        def ketchup = new Ketchup(params)

        assert ketchup.save() != null
        assert Ketchup.count() == 1

        params.id = ketchup.id

        controller.delete()

        assert Ketchup.count() == 0
        assert Ketchup.get(ketchup.id) == null
        assert response.redirectedUrl == '/ketchup/list'
    }
}
