package services.tfi

import model.OperationType
import model.tfi.TFIOperation
import model.tfi.TFIRegister

class TFIRegisterService {
    static transactional = false

    def TFIOperationService

    def Map<String, TFIRegister> registers = new HashMap<String, TFIRegister>()


    def add(TFIOperation operation) {
        if (operation.type == OperationType.BUY){
            buy(operation)
        } else if (operation.type == OperationType.SELL){
            sell(operation)
        } else {
            convert(operation)
        }
    }

    def private buy(TFIOperation operation){
        def register = registers.get(operation.registerId)
        if (!register){
            register = addRegister(operation.registerId)
        }
        TFIOperationService.buy(register.operations, operation)
    }

    def private sell(TFIOperation operation){
        def register = registers.get(operation.registerId)
        if (register){
            TFIOperationService.sell(register.operations, operation)
        } else {
            throw new Exception("SELL: register not exists - ${operation.registerId}")
        }
    }

    def private convert(TFIOperation operation){
        def source = registers.get(operation.registerId)
        if (source){
            def income = source.income
            TFIOperationService.convertFrom(source.operations, operation)
            operation.convertIncome = source.income - income
            def dest = registers.get(operation.convertDestRegisterId)
            if (!dest){
                dest = addRegister(operation.convertDestRegisterId)
            }
            TFIOperationService.convertTo(dest.operations, operation)
        } else {
            throw new Exception("CONVERT: register not exists - ${operation.registerId}")
        }
    }

    def private TFIRegister addRegister(String registerId){
        def register = new TFIRegister(id: registerId)
        registers.put(registerId, register)
        return register
    }
}
