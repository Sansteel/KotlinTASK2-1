package ru.netology

/** ПРИМЕЧАНИЕ
 * так как в задании нет указаний на объявление типа перевода: входящий/исходящий,
 * принимаю допущение что все переводы в рамках данного ДЗ являются ВХОДЯЩИМИ
 */

//fixme test string

val cardType = listOf("Mastercard", "Maestro", "Visa", "Мир", "VKPay")

val money = 10_000 // руб сумма для перевода
val cardT = cardType[4] // с какого счета делаем перевод


var currDaySum = 0
var currMonthSumOut = 0
var currMonthSumIn = 0
var vkMonthSum = 0

const val convRubKop: Int = 100 //конвертер рублей в копейки


fun main() {

    printResult(money, cardT, calculationFee(cardType = cardT, summa = money), checkLimits(cardT, money))

}

fun calculationFee(
    cardType: String,
    limit: Int = 0,
    summa: Int
): Int {

    when (cardType) {
        "Visa", "Мир" -> {
            val stavkaDefault = 0.75 // %
            val stavkaMin = 35 * convRubKop // коп

            var fee: Double = (summa * convRubKop * stavkaDefault) / 100
            if (fee < stavkaMin) fee = stavkaMin.toDouble()
            return fee.toInt()
        }
        "Mastercard", "Maestro" -> {
            return if (summa < 75_000) 0
            else {
                (summa * convRubKop * 0.6 / 100 + 20 * convRubKop).toInt()
            }
        }
        else -> return 0
    }
}

fun checkLimits(cardType: String, summa: Int): Boolean {


    val dayLimit = 150_000 * convRubKop //коп
    val monthLimitIn = 600_000 * convRubKop //коп
    val monthLimitOut = 600_000 * convRubKop //коп

    val vkLimit = 15_000 * convRubKop
    val vkLimitMonth = 40_000 * convRubKop

    var ostatok: Int = summa * convRubKop //коп

    if (cardType != "VKPay") {
        val dayOstatok: Int = dayLimit - currDaySum
        val monthOstatokIn: Int = monthLimitIn - currMonthSumIn
        val monthOstatokOut: Int = monthLimitOut - currMonthSumOut

        return if (dayOstatok < summa * convRubKop || monthOstatokIn < summa * convRubKop || monthOstatokOut < summa * convRubKop) {
            ostatok = 0
            println("Превышен лимит операций #1")
            false
        } else {
            currDaySum += summa * convRubKop
            currMonthSumIn += summa * convRubKop
            true
        }
    } else {
        return if (summa * convRubKop >= vkLimit || summa * convRubKop >= vkLimitMonth) {
            println("Превышен лимит операций #2")
            false
        } else {
            val vkMonthOstatok = vkLimitMonth - vkMonthSum
            if (summa * convRubKop > vkMonthOstatok) {
                println("Превышен лимит операций #3")
                false
            } else {
                vkMonthSum += summa * convRubKop
                true
            }
        }
    }
}

fun printResult(summa: Int, cardType: String, amount: Int, limit: Boolean) {
    if (limit) println(
        "Комиссия за перевод суммы " + summa + " руб со счета " +
                cardType + " составит " + (amount / convRubKop) + " руб"
    )
}