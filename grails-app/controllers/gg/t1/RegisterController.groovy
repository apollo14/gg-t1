package gg.t1

import model.CalculationsFilter

import java.text.SimpleDateFormat

class RegisterController {

    def registerService
    def calculationService
    def filter

    RegisterController(registerService){
        registerService.loadData()
    }

    def index() {
        [registers: registerService.registers.values(), incomeTotal: registerService.incomeTotal, filter: filter]
    }

    def show(String id){
        def register = registerService.registers.get(id)
        def calculations = register.calculations

        if (filter){
            calculations = calculationService.filter(calculations, filter)
        }

        [register: register, calculations: calculations]
    }

    def reload() {
        if (filter){
            registerService.loadData(filter)
        } else {
            registerService.loadData()
        }

        redirect(action: "index")
    }

    def filter(String id){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy")
        def from =  params["fromDate_year"]
        def to = params["toDate_year"]
        def fromDate = sd.parse(from)
        def toDate = sd.parse(to)
        filter = new CalculationsFilter(fromYear: from, toYear: to, fromDate: fromDate, toDate: toDate)
        reload()
    }

    def clearFilter(){
        def from = String.valueOf(Calendar.instance.get(Calendar.YEAR))
        filter = new CalculationsFilter(fromYear: from, toYear: from)
        reload()
    }

    def fullDataSet(){
        filter = null
        reload()
    }
}
