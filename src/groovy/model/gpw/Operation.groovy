package model.gpw

import model.OperationType
import org.joda.time.DateTime

/**
 * Created by Q1O1 on 06-12-2016.
 */
class Operation {

    String registerId
    DateTime dateCreated = DateTime.now()
    BigDecimal price
    BigDecimal volume
    OperationType type
    boolean manual = false
}
