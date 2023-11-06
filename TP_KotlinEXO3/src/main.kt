fun main(){
    val employees = listOf(
            SalariedEmployee(30000.0, "John","Week","900392010"),
            HourlyEmployee(15000.0, 15.0,"Jane","Merias","900392019"),
            CommissionEmployee(50000.0, 0.1,"Bob","Kadith","900392011"),
            BasePlusCommissionEmployee(2000.0,10000.0,0.05,"Alice","Borro","900392017")
    )

    for (employee in employees) {
        employee.printInformation()
        val pay = employee.getPaymentAmount()
        println("Payment amount: $pay")
    }
}