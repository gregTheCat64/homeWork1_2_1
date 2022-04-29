package hw1_2

fun main() {
    val amountInRubles = 500
    val amount = amountInRubles * 100
    val minFee = 3500

    val fee = amount * 0.075
    val result = if (fee < minFee) minFee.toDouble() else fee
    println("комиссия равна ${result/100} рублей")
}