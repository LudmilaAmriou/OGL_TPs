abstract class Employee(val firstname: String, val lastName: String, val socialSecurityNumber: String) : Payable {

    abstract override fun getPaymentAmount(): Double

    fun printInformation() {
        println("\n")
        println("Hourly employee: $lastName $firstname")
        println("Social Security Number: $socialSecurityNumber")
    }

}