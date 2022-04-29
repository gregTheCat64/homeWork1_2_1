package hw1_2

fun main() {
    val previousPurchases = 10001//сумма предыдущих покупок
    val purchasePrice = 1000.0   //цена за покупаемый товар

    //уровень скидки для покупателя:
    val discountFirstLevel = 1000
    val discountSecondLevel = 10_000
    val discountRegular = true  //статус постоянного покупателя

    //размер скидки в завис. от уровня:
    val discountFirstSize = 100.0
    val discountSecondSize = 0.05
    val discountRegularSize = 0.01

    //проверяем первые два уровня скидок
    var finalPrice: Double =
        if (previousPurchases in (discountFirstLevel + 1) until discountSecondLevel + 1) (purchasePrice - discountFirstSize) else
            if (previousPurchases > discountSecondLevel) (purchasePrice - purchasePrice * discountSecondSize) else purchasePrice

    //проверяем на меломанство
    finalPrice = if (discountRegular) finalPrice - finalPrice * discountRegularSize else finalPrice
    println("Ваша цена: $finalPrice рублей")
}