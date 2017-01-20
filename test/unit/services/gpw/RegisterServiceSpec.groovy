package services.gpw

import grails.test.mixin.TestFor
import model.gpw.Operation
import model.OperationType
import org.codehaus.groovy.grails.commons.DefaultGrailsApplication
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(RegisterService)
class RegisterServiceSpec extends Specification {

    def operationService
    def calculationService
    def dataService
    def grailsApplication

    def setup() {
        grailsApplication = new DefaultGrailsApplication()
        grailsApplication.config.files.location = "test\\resources"
        operationService = new OperationService()
        calculationService = new CalculationService()
        dataService = new DataService()
        dataService.grailsApplication = grailsApplication
        service.operationService = operationService
        service.calculationService = calculationService
        service.dataService = dataService
    }

    def cleanup() {
    }

    void "test simple buy"() {
        given:
        def operation = new Operation(registerId: "t1", price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(10), type: OperationType.BUY)

        when:
        service.add(operation)

        then:
        assertEquals(service.registers.get("t1").operations.size(), 1)
        assertEquals(service.registers.get("t1").calculations.size(), 1)
    }

    void "test simple sell"() {
        given:
        def operation = new Operation(registerId: "t1", price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(10), type: OperationType.SELL)
        service.add(new Operation(registerId: "t1", price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(10), type: OperationType.BUY))

        when:
        service.add(operation)

        then:
        assertEquals(service.registers.get("t1").operations.size(), 2)
        assertEquals(service.registers.get("t1").calculations.size(), 1)
    }

    void "test simple loadData"() {
        when:
        service.loadData()

        then:
        assertEquals(service.registers.get("T1").operations.size(), 4)
        assertEquals(service.registers.get("T2").operations.size(), 4)
    }

}
