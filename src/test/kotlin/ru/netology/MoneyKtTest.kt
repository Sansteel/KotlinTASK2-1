package ru.netology

import org.junit.Test

import org.junit.Assert.*

class MoneyKtTest {

    @Test
    fun calculationFee_Mastercard_70_000() {
        // arrange
        val cardType: String = "Mastercard"
        val limit: Int = 0
        val summa = 70_000
        val expectedFee = 0
        // act
        val actualFee = calculationFee(cardType, limit, summa)
        // assert
        assertEquals(expectedFee, actualFee)
    }

    @Test
    fun calculationFee_Mastercard_140_000() {
        // arrange
        val cardType: String = "Mastercard"
        val limit: Int = 0
        val money = 140_000
        val expectedFee = 860 * convRubKop
        // act
        val actualFee = calculationFee(cardType, limit, money)
        // assert
        assertEquals(expectedFee, actualFee)
    }

    @Test
    fun calculationFee_VISA_70_000() {
        // arrange
        val cardType: String = "Visa"
        val limit: Int = 0
        val summa = 70_000
        val expectedFee = 525 * convRubKop
        // act
        val actualFee = calculationFee(cardType, limit, summa)
        // assert
        assertEquals(expectedFee, actualFee)
    }

    @Test
    fun calculationFee_МИР_1_000() {
        // arrange
        val cardType: String = "Мир"
        val limit: Int = 0
        val summa = 1_000
        val expectedFee = 35 * convRubKop
        // act
        val actualFee = calculationFee(cardType, limit, summa)
        // assert
        assertEquals(expectedFee, actualFee)
    }

    @Test
    fun calculationFee_Тинькоф_1_000() {
        // arrange
        val cardType: String = "Тинькоф"
        val limit: Int = 0
        val summa = 1_000
        val expectedFee = 0 * convRubKop
        // act
        val actualFee = calculationFee(cardType, limit, summa)
        // assert
        assertEquals(expectedFee, actualFee)
    }

    @Test
    fun checkLimits_Mastercard_1000() {
        // arrange
        val cardType: String = "Mastercard"
        val summa = 1_000
        val expectedLimit = true
        // act
        val actualLimit = checkLimits(cardType, summa)
        // assert
        assertEquals(expectedLimit, actualLimit)
    }

    @Test
    fun checkLimits_Mastercard_200_000() {
        // arrange
        val cardType: String = "Mastercard"
        val summa = 200_000
        val expectedLimit = false
        // act
        val actualLimit = checkLimits(cardType, summa)
        // assert
        assertEquals(expectedLimit, actualLimit)
    }

    @Test
    fun checkLimits_VKPay_2_000() {
        // arrange
        val cardType: String = "VKPay"
        val summa = 2_000
        val expectedLimit = true
        // act
        val actualLimit = checkLimits(cardType, summa)
        // assert
        assertEquals(expectedLimit, actualLimit)
    }

    @Test
    fun checkLimits_VKPay_20_000() {
        // arrange
        val cardType: String = "VKPay"
        val summa = 20_000
        val expectedLimit = false
        // act
        val actualLimit = checkLimits(cardType, summa)
        // assert
        assertEquals(expectedLimit, actualLimit)
    }

    @Test
    fun checkLimits_VKPay_60_000() {
        // arrange
        val cardType: String = "VKPay"
        val summa = 60_000
        val expectedLimit = false
        // act
        val actualLimit = checkLimits(cardType, summa)
        // assert
        assertEquals(expectedLimit, actualLimit)
    }

    @Test
    fun checkLimits_VKPay_60_000_andSumLim() {
        // arrange
        val cardType: String = "VKPay"
        val summa = 60_000
        val expectedLimit = false
        val vkMonthSum = 100_000_000 // todo не знаю как перезаписать статическое поле в файле MoneyKt при тесте
        // act
        val actualLimit = checkLimits(cardType, summa)
        // assert
        assertEquals(expectedLimit, actualLimit)
    }

    @Test
    fun printResult_ON() {
        // arrange
        val cardType: String = "VKPay"
        val summa = 60_000
        val expectedResult = kotlin.Unit
        // act
        val actualResult = printResult(summa, cardType, 0, true)
        // assert
        assertEquals(expectedResult, actualResult)
    }
    @Test
    fun printResult_Off() {
        // arrange
        val cardType: String = "VKPay"
        val summa = 60_000
        val expectedResult = kotlin.Unit
        // act
        val actualResult = printResult(summa, cardType, 0, false)
        // assert
        assertEquals(expectedResult, actualResult)
    }
}
