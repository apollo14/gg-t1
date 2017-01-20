package services.gpw

import model.gpw.Operation
import model.OperationType
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class DataService {
    static transactional = false

    def grailsApplication

    def List<Operation> loadData() {
        def result = new ArrayList<Operation>()
        def filesLocationDir = new File("${grailsApplication.config.files.location}")
        filesLocationDir.eachFileMatch(~/.*.csv/) {
            result.addAll(loadFile(it.absolutePath))
        }
        return result
    }

    def private List<Operation> loadFile(String fileName){
        def result = new ArrayList<Operation>()

        new File(fileName).splitEachLine(';'){ fields ->
            def operation = new Operation(
                    registerId: fields[1],
                    dateCreated: DateTime.parse(fields[0], DateTimeFormat.forPattern("yyyy-MM-dd-HH.mm.ss")),
                    volume: new BigDecimal(fields[3].replace(',', '.')),
                    price: new BigDecimal(fields[4].replace(',', '.')),
                    type: "KUPNO".equals(fields[2]) ? OperationType.BUY : OperationType.SELL
            )
            result.add(operation)
        }
        return result.reverse()
    }
}
