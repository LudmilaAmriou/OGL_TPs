class SalariedEmployee(var monthlySalary : Double,firstname: String, lastName: String, socialSecurityNumber: String): Employee(firstname, lastName, socialSecurityNumber) {

    override fun getPaymentAmount(): Double {
        return monthlySalary
    }

}