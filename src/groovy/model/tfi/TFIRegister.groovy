package model.tfi

/**
 * Created by Q1O1 on 28-12-2016.
 */
class TFIRegister {
    String id
    List<TFIOperation> operations = new ArrayList<TFIOperation>()

    BigDecimal income = BigDecimal.ZERO
}
