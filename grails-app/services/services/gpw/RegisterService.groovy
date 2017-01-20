package services.gpw

import model.CalculationsFilter
import model.gpw.Operation
import model.OperationType
import model.gpw.Register

class RegisterService {
    static transactional = false

    def calculationService
    def operationService
    def dataService

    def Map<String, Register> registers = new HashMap<String, Register>()
	BigDecimal incomeTotal = BigDecimal.ZERO


    def add(Operation operation){
        if (operation.type == OperationType.BUY){
            buy(operation)
        } else {
            sell(operation)
        }
    }

    def private buy(Operation operation) {
        def register = registers.get(operation.registerId)
        if(!register){
            register = new Register(id: operation.registerId)
            registers.put(operation.registerId, register)
        }
        operationService.buy(register.operations, operation)
        calculationService.buy(register.calculations, operation)
    }

    def private sell(Operation operation){
        def register = registers.get(operation.registerId)
        if (register) {
            operationService.sell(register.operations, operation)
            calculationService.sell(register.calculations, operation)
        } else {
            throw new Exception("SELL: register not exists - ${operation.registerId}")
        }
    }

    def loadData(){
        registers = new HashMap<String, Register>()
        def operations = dataService.loadData()
        operations.each {
            add(it)
        }
        calculateIncome()
        calculateVolume()
    }

    def loadData(CalculationsFilter filter){
        registers = new HashMap<String, Register>()
        def operations = dataService.loadData()
        operations.each {
            add(it)
        }
        calculateIncome(filter)
        calculateVolume()
    }



    def calculateIncome(){
		incomeTotal = BigDecimal.ZERO
        registers.values().each { register ->
            calculationService.calculateIncome(register.calculations)
            register.calculations.each {
                register.income += it.income
				incomeTotal += it.income
            }
        }
    }

    def calculateIncome(CalculationsFilter filter){
		incomeTotal = BigDecimal.ZERO
        registers.values().each { register ->
            def calculationsFiltered = calculationService.filter(register.calculations, filter)
            calculationService.calculateIncome(calculationsFiltered)
            register.calculations.each {
                register.income += it.income
				incomeTotal += it.income
            }
        }
    }

    def calculateVolume(){
        registers.values().each { register ->
            register.calculations.findAll { it.sell == null }.each {register.volume += it.volume}
        }
    }
}
