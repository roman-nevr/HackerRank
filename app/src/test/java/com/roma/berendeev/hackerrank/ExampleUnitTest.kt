package com.roma.berendeev.hackerrank

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        val ar1 = arrayOf(11, 2, 4)
        val ar2 = arrayOf(4, 5, 6)
        val ar3 = arrayOf(10, 8, -12)
        val arr = arrayOf(ar1, ar2, ar3)
        val result = diagonalDifference(arr)
        println(result)
        val ar4 = arrayOf(2, 6, 4, 6, 3)
        birthdayCakeCandles(ar4)
        println(timeConversion("07:05:45PM"))
    }

    fun compareTriplets(a: Array<Int>, b: Array<Int>): Array<Int> {
        val result = Array<Int>(2, init = { 0 })
        a.forEachIndexed { index, alice ->
            val bob = b[index]
            if (alice > bob) {
                result[0]++
            } else if (alice < bob) {
                result[1]++
            }
        }
        return result
    }

    fun aVeryBigSum(ar: Array<Long>): Long {
        return if (ar.isNotEmpty()) {
            ar.reduce { acc, l -> acc + l }
        } else {
            0
        }
    }

    private fun diagonalDifference(arr: Array<Array<Int>>): Int {
        var result = 0
        arr.forEachIndexed { index, ints ->
            result += ints[index] - ints[ints.size - 1 - index]
        }
        return Math.abs(result)
    }

    private fun plusMinus(arr: Array<Int>): Unit {
        val results = Array(3, { 0 })
        arr.forEach {
            when {
                it > 0 -> results[0]++
                it < 0 -> results[1]++
                it == 0 -> results[2]++
            }
        }
        println(results[0].toFloat() / arr.size)
        println(results[1].toFloat() / arr.size)
        println(results[2].toFloat() / arr.size)
    }

    private fun simpleArraySum(ar: Array<Int>): Int {
        return if (ar.isNotEmpty()) {
            ar.reduce { acc, value -> acc + value }
        } else {
            0
        }
    }

    private fun staircase(n: Int): Unit {
        (0 until n).forEach { row ->
            (0 until n - row - 1).forEach { print(" ") }
            (n - row - 1 until n).forEach { print("#") }
            println()
        }
    }

    private fun miniMaxSum(arr: Array<Int>): Unit {
        val sortedArr = arr.copyOf()
        sortedArr.sort()
        var total = 0L
        arr.forEach { value -> total += value }
        val minValue = total - sortedArr.last()
        val maxValue = total - sortedArr.first()
        println("$minValue $maxValue")
    }

    private fun birthdayCakeCandles(ar: Array<Int>): Int {
        val max = ar.max()
        var countOfMax = 0
        ar.forEach { value ->
            if (value == max) countOfMax++
        }
        return countOfMax
    }

    private val pattern = "hh:ss:mmxM"

    private fun timeConversion(s: String): String {
        val period = s.substring(s.length - 2, s.length)
        var hours = s.substring(0, 2).toInt()
        if (period == "AM") {
            if (hours == 12) {
                hours = 0
            }
        }
        if (period == "PM") {
            if (hours < 12) {
                hours +=12
            }
        }
        val minutesAndSeconds = s.substring(3, s.length - 2)
        return String.format("%02d:%s", hours, minutesAndSeconds)
    }
}
