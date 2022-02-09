package ru.netology

/** ЗАДАЧА
 * Упростим задачу, чтобы было проще: за переводы с любых карт комиссия 0.75%, минимум 35 рублей.
Что нужно сделать: напишите небольшую программу, в которой в переменной amount хранится сумма перевода в копейках.
Ваше приложение должно высчитывать комиссию, которую заплатит пользователь при переводе — комиссия также должна быть в копейках.
 */

val money = 10_000 // сумма для перевода


fun main() {

    val stavkaDefault = 0.75 // %
    val stavkaMin = 35 // руб

    var tempAmount: Double = (money * stavkaDefault)/100
    if (tempAmount < stavkaMin) {
        tempAmount = stavkaMin.toDouble()
    }
    val amount: Int = tempAmount.toInt()

    println("Комиссия за перевод суммы " + money + " руб составит " + (amount * 100) + " копеек")
}