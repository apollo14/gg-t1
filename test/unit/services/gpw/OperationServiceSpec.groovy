package services.gpw

import grails.test.mixin.TestFor
import model.gpw.Operation
import model.OperationType
import services.gpw.OperationService
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(OperationService)
class OperationServiceSpec extends Specification {

    def operations
    def setup() {
        operations = new ArrayList<Operation>()
    }

    def cleanup() {
    }

    void "test simple buy"() {
        given:
        def operation = new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(10), type: OperationType.BUY)

        when:
        service.buy(operations, operation)

        then:
        assertEquals(operations.size(), 1)
        assertEquals(operations.get(0).type, OperationType.BUY)
        assertNotNull(operations.get(0).dateCreated)
    }

    void "test simple sell"() {
        given:
        service.buy(operations, new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(10), type: OperationType.BUY))
        def operation = new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(10), type: OperationType.SELL)

        when:
        service.sell(operations, operation)

        then:
        assertEquals(operations.size(), 2)
        assertEquals(operations.get(1).type, OperationType.SELL)
        assertNotNull(operations.get(1).dateCreated)
    }


}
