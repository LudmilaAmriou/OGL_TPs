open class CommissionEmployee(var grossSales : Double, var commissionRate : Double, firstname: String, lastName: String, socialSecurityNumber: String): Employee(firstname, lastName, socialSecurityNumber) {

    override fun getPaymentAmount(): Double {
        return grossSales*commissionRate
    }

}