package model.gpw

import model.gpw.Calculation
import model.gpw.Operation

/**
 * Created by Q1O1 on 06-12-2016.
 */
class Register {
    String id
    List<Operation> operations = new ArrayList<Operation>()
    List<Calculation> calculations = new ArrayList<Calculation>()

    BigDecimal volume = BigDecimal.ZERO
    BigDecimal income = BigDecimal.ZERO
}
