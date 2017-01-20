package model.tfi

import model.OperationType
import org.joda.time.DateTime

/**
 * Created by Q1O1 on 28-12-2016.
 */
class TFIOperation {
    String registerId
    DateTime dateCreated = DateTime.now()
    BigDecimal value = BigDecimal.ZERO
    BigDecimal price = BigDecimal.ZERO

    OperationType type

    BigDecimal convertIncome = BigDecimal.ZERO
    String convertDestRegisterId
}
