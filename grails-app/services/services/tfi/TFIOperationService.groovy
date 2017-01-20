package services.tfi

import grails.transaction.Transactional
import model.OperationType
import model.tfi.TFIOperation

class TFIOperationService {
    static transactional = false

    def buy(List<TFIOperation> operations, TFIOperation operation) {
        operations.add(operation)
    }

    def sell(List<TFIOperation> operations, TFIOperation operation) {
        if( operation.value <= getValueBalance(operations)){
            operations.add(operation)
        } else {
            throw Exception("SELL: volume balance is less then sell volume - ${operation.registerId}")
        }
    }

    def convertFrom(List<TFIOperation> operations, TFIOperation operation) {
        if (operation.value <= getValueBalance()){
            sell(operations, operation)
        } else {
            throw Exception("CONVERT_FROM: volume balance is less then sell volume - ${operation.registerId}")
        }
    }

    def convertTo(List<TFIOperation> operations, TFIOperation operation) {
        def buyOperation = new TFIOperation(
                registerId: operation.convertDestRegisterId,
                dateCreated: operation.dateCreated,
                value: operation.value,
                convertIncome: operation.convertIncome
        )
        buy(operations, operation)
    }

    def private BigDecimal getValueBalance(List<TFIOperation> operations){
        BigDecimal balance = BigDecimal.ZERO
        operations.findAll{it.type == OperationType.BUY}.each {
            balance += it.value
        }
        operations.findAll{it.type == OperationType.SELL || it.type == OperationType.CONVERT}.each{
            balance -= it.value
        }

        return balance
    }

}
