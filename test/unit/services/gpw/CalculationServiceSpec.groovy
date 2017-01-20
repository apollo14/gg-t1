package services.gpw

import grails.test.mixin.TestFor
import model.gpw.Calculation
import model.gpw.Operation
import services.gpw.CalculationService
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CalculationService)
class CalculationServiceSpec extends Specification {

    def calculations
    def setup() {
        calculations = new ArrayList<Calculation>()
    }

    def cleanup() {
    }

    void "test simple buy"() {
        given:
        def operation = new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(10))

        when:
        service.buy(calculations, operation)

        then:
        assertEquals(calculations.size(), 1)
        assertNotNull(calculations.get(0).buy)
        assertTrue(calculations.get(0).volume.equals(BigDecimal.valueOf(10)))
    }

    void "test simple sell"(){
        given:
        service.buy(calculations, new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(10)))
        def operation = new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(5))

        when:
        service.sell(calculations, operation)

        then:
        assertEquals(calculations.size(), 2)
        assert(calculations.get(0).volume == BigDecimal.valueOf(5))
        assertNotNull(calculations.get(0).sell)
        assertNull(calculations.get(1).sell)

    }

    void "test simple sell 2"(){
        given:
        service.buy(calculations, new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(10)))
        service.sell(calculations, new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(3)))
        def operation = new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(5))

        when:
        service.sell(calculations, operation)

        then:
        assertEquals(calculations.size(), 3)
        assert(calculations.get(0).volume = BigDecimal.valueOf(3))
        assert(calculations.get(1).volume = BigDecimal.valueOf(5))
        assert(calculations.get(2).volume = BigDecimal.valueOf(2))
    }

    void "test simple sell 3"(){
        given:
        service.buy(calculations, new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(5)))
        service.buy(calculations, new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(5)))
        service.sell(calculations, new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(6)))
        def operation = new Operation(price: BigDecimal.valueOf(10), volume: BigDecimal.valueOf(1))

        when:
        service.sell(calculations, operation)

        then:
        assertEquals(calculations.size(), 4)
        assert(calculations.get(0).volume = BigDecimal.valueOf(5))
        assert(calculations.get(1).volume = BigDecimal.valueOf(1))
        assert(calculations.get(2).volume = BigDecimal.valueOf(1))
        assert(calculations.get(3).volume = BigDecimal.valueOf(3))
    }

}
