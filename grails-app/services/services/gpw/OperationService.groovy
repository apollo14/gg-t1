package services.gpw

import model.gpw.Operation
import model.OperationType

class OperationService {
    static transactional = false

    def buy(List<Operation> operations, Operation operation) {
        operations.add(operation)
    }

    def sell(List<Operation> operations, Operation operation) {
        if (operation.volume <= getVolumeBalance(operations)) {
            operations.add(operation)
        } else {
            throw Exception("SELL: volume balance is less then sell volume - ${operation.registerId}")
        }
    }

    def private BigDecimal getVolumeBalance(List<Operation> operations){
        BigDecimal balance = BigDecimal.ZERO
        operations.findAll{it.type == OperationType.BUY}.each {
            balance += it.volume
        }
        operations.findAll{it.type == OperationType.SELL}.each{
            balance -= it.volume
        }

        return balance
    }
}
