package services.gpw

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DataService)
class DataServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test loadData"() {
        when:
        def result = service.loadData()
        then:
        assertEquals(result.size(), 8)
    }
}
