class BasePlusCommissionEmployee(var baseSalary: Double,grossSales: Double, commissionRate: Double, firstname: String, lastName: String, socialSecurityNumber: String) : CommissionEmployee(grossSales, commissionRate, firstname, lastName, socialSecurityNumber) {

    override fun getPaymentAmount(): Double {
        return super.getPaymentAmount() + baseSalary
    }

}