class HourlyEmployee(var wage: Double,var hours: Double,firstname: String, lastName: String, socialSecurityNumber: String) : Employee(firstname, lastName, socialSecurityNumber) {

    override fun getPaymentAmount(): Double {
        return hours*wage
    }

}