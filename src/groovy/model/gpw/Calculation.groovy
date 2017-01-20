package model.gpw
/**
 * Created by Q1O1 on 06-12-2016.
 */
class Calculation {
    Operation buy
    Operation sell
    BigDecimal volume = BigDecimal.ZERO
    BigDecimal income = BigDecimal.ZERO

    Calculation(Operation buy){
        this.buy = buy
        this.volume = buy.volume
    }
}
