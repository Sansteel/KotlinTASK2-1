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
        val expectedFee = 0.0
        // act
        val actualFee = calculationFee(cardType, limit, summa)
        // assert
        assertEquals(expectedFee, actualFee, 0.1)
    }

    @Test
    fun calculationFee_Mastercard_140_000() {
        // arrange
        val cardType: String = "Mastercard"
        val limit: Int = 0
        val summa = 140_000
        val expectedFee = 860.0 * convRubKop
        // act
        val actualFee = calculationFee(cardType, limit, summa)
        // assert
        assertEquals(expectedFee, actualFee, 0.1)
    }

//    @Test
//    fun calculationFee_VISA_70_000() {
//        // arrange
//        val cardType: String = "VISA"
//        val limit: Int = 0
//        val summa = 70_000
//        val expectedFee = 525.0
//        // act
//        val actualFee = calculationFee(cardType, limit, summa)
//        // assert
//        assertEquals(expectedFee, actualFee, 0.1)
//    }
//
//    @Test
//    fun calculationFee_МИР_1_000() {
//        // arrange
//        val cardType: String = "МИР"
//        val limit: Int = 0
//        val summa = 1_000
//        val expectedFee = 35.0
//        // act
//        val actualFee = calculationFee(cardType, limit, summa)
//        // assert
//        assertEquals(expectedFee, actualFee, 0.1)
//    }
}