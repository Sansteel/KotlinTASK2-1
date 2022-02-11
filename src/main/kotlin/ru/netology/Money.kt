package ru.netology

import kotlin.math.min

/** ПРИМЕЧАНИЕ
 * так как в задании нет указаний на объявление типа перевода: входящий/исходящий,
 * принимаю допущение что все переводы в рамках данного ДЗ являются ВХОДЯЩИМИ
 */

val cardType = listOf("Mastercard", "Maestro", "Visa", "Мир", "VKPay")

val money = 10_000 // руб сумма для перевода
val cardT = cardType[2] // с какого счета делаем перевод


var currDaySum = 0
var currMonthSumOut = 0
var currMonthSumIn = 0
var vkMonthSum = 0

const val convRubKop: Int = 100 //конвертер рублей в копейки


fun main() {

    printResult(calculationFee(cardType = cardT, summa = money), checkLimits(cardT))

}

fun calculationFee(
    cardType: String,
    limit: Int = 0,
    summa: Int
): Double {

    when {
        cardType == "Visa" || cardType == "Мир" -> {
            val stavkaDefault = 0.75 // %
            val stavkaMin = 35 * convRubKop // коп

            var fee: Double = (money * convRubKop * stavkaDefault) / 100
            if (fee < stavkaMin) fee = stavkaMin.toDouble()
            return fee
        }
        cardType == "Mastercard" || cardType == "Maestro" -> {
            if (money < 75_000) return 0.0 else {
                return money * convRubKop * 0.6 / 100 + 20 * convRubKop
            }
        }
        else -> return 0.0
    }
}

fun checkLimits(cardType: String): Boolean {


    val dayLimit = 150_000 * convRubKop //коп
    val monthLimitIn = 600_000 * convRubKop //коп
    val monthLimitOut = 600_000 * convRubKop //коп

    val VkLimit = 15_000 * convRubKop
    val VkLimitMonth = 40_000 * convRubKop

    var ostatok: Int = money * convRubKop //коп

    if (cardType != "VKPay") {
        val dayOstatok: Int = dayLimit - currDaySum
        val monthOstatokIn: Int = monthLimitIn - currMonthSumIn
        val monthOstatokOut: Int = monthLimitOut - currMonthSumOut

        if (dayOstatok < money * convRubKop || monthOstatokIn < money * convRubKop || monthOstatokOut < money * convRubKop) {
            ostatok = 0
            println("Превышен лимит операций #1")
            return false
        } else {
            currDaySum += money * convRubKop
            currMonthSumIn += money * convRubKop
            return true
        }
    } else {
        if (money * convRubKop >= VkLimit || money * convRubKop >= VkLimitMonth) {
            println("Превышен лимит операций #2")
            return false
        } else {
            val vkMonthOstatok = VkLimitMonth - vkMonthSum
            if (money * convRubKop > vkMonthOstatok) {
                println("Превышен лимит операций #3")
                return false
            } else {
                vkMonthSum += money * convRubKop
                return true
            }
        }
    }
}

fun printResult(amount: Double, limit: Boolean) {
    if (limit) println(
        "Комиссия за перевод суммы " + money + " руб со счета " +
                cardT + " составит " + (amount / convRubKop) + " руб"
    )
}