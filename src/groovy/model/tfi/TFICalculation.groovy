package model.tfi

/**
 * Created by Q1O1 on 28-12-2016.
 */
class TFICalculation {
    TFIOperation buy
    TFIOperation sell
    TFIOperation convertFrom
    TFIOperation convertTo

    BigDecimal price = BigDecimal.ZERO
    BigDecimal volume = BigDecimal.ZERO
}
