package services.gpw

import model.gpw.Calculation
import model.CalculationsFilter
import model.gpw.Operation

class CalculationService {
    static transactional = false

    def buy(List<Calculation> calculations, Operation operation) {
        calculations.add(new Calculation(operation))
    }

    def sell(List<Calculation> calculations, Operation operation){

        def notSold = calculations.findAll{ it.sell == null }
        BigDecimal volume = operation.volume

        notSold.each {
            if (volume > 0){
                if (it.volume <= volume){
                    // buy <= sell
                    it.sell = operation
                    volume -= it.volume
                } else {
                    // buy > sell
                    /*List<Calculation> newCalculations = new ArrayList<Calculation>()
                    Calculation first = new Calculation(it.buy)
                    first.sell = operation
                    first.volume = volume
                    newCalculations.add(first)
                    Calculation second = new Calculation(it.buy)
                    second.volume = it.volume - volume
                    newCalculations.add(second)*/
                    def newCalculations = divideBuyCalculation(it, operation, volume)
                    def index = calculations.indexOf(it)
                    calculations.remove(index)
                    calculations.addAll(index, newCalculations)
                    volume = 0
                    return true
                }
            }
        }
    }

    def List<Calculation> divideBuyCalculation(Calculation calculation, Operation sell, BigDecimal volume){
        List<Calculation> newCalculations = new ArrayList<Calculation>()
        Calculation first = new Calculation(calculation.buy)
        first.sell = sell
        first.volume = volume
        newCalculations.add(first)
        Calculation second = new Calculation(calculation.buy)
        second.volume = calculation.volume - volume
        newCalculations.add(second)
        return newCalculations
    }

    def calculateIncome(List<Calculation> calculations){
        calculations.each {
            if (it.sell) {
                it.income = (it.sell.price - it.buy.price) * it.volume
            }
        }
    }

    def filter(List<Calculation> calculations, CalculationsFilter filter){
        return calculations.findAll{
            it.sell != null &&
            it.sell.dateCreated.year >= Integer.parseInt(filter.fromYear) &&
            it.sell.dateCreated.year <= Integer.parseInt(filter.toYear)
        }
    }
}
