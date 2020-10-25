package com.roma.berendeev.leetCode

import org.junit.Test
import kotlin.math.abs

class IntDivision {

    @Test
    fun division() {
        val a = -15
        val b = 0x0F

//        divide(1, 1)
        divide2(Int.MIN_VALUE, Int.MIN_VALUE)
        divide2(1038925803, Int.MIN_VALUE)

        divide2(Int.MIN_VALUE, -1)
        divide2(Int.MIN_VALUE, -3)

        divide2(Int.MAX_VALUE, 1)
        divide2(Int.MAX_VALUE, 3)

        divide2(Int.MIN_VALUE, 1)
        divide2(Int.MIN_VALUE, 3)

        divide2(Int.MAX_VALUE, -1)
        divide2(Int.MAX_VALUE, -3)

        divide2(1, 3)
        divide2(1, -3)
        divide2(-1, 3)
        divide2(-1, -3)


        divide2(62, -3)
        divide2(-62, -3)
        divide2(62, 3)
        divide2(62, -3)
//        divide(8, 2)
    }

    private fun divide2(dividend: Int, divisor: Int): Int {
        val degree = calcDegree(dividend, divisor)
        val result = calcDivision(dividend, divisor, degree)
        println("$dividend / $divisor = $result (${dividend / divisor})")
        return result
    }

    private fun calcDivision(dividend: Int, divisor: Int, degree: Int): Int {
        var result = 0
        val positiveDivisor = abs(divisor)
        var tempDividend = dividend
        for (i in degree downTo 0) {
            if (dividend > 0) {
                if (positiveDivisor shl i <= tempDividend) {
                    result += (1 shl i)
                    tempDividend -= (positiveDivisor shl i)
                }
            } else {
                if (-(positiveDivisor shl i) >= tempDividend) {
                    result += (1 shl i)
                    tempDividend += (positiveDivisor shl i)
                }
            }
        }
        return if (dividend xor divisor >= 0) result else -result
    }

    private fun calcDegree(dividend: Int, divisor: Int): Int {
        if (dividend == divisor) return 0
        var degree = -1
        if (dividend > 0) {
            if (divisor > 0) {
                var calculation = (divisor shl (degree + 1))
                while (calculation <= dividend && calculation > 0 && calculation != Int.MIN_VALUE) {
                    degree += 1
                    calculation = (divisor shl (degree + 1))
                }
            } else {
                var calculation = -(divisor shl (degree + 1))
                while (calculation <= dividend && calculation > 0 && calculation != Int.MIN_VALUE) {
                    degree += 1
                    calculation = -(divisor shl (degree + 1))
                }
            }
        } else {
            if (divisor > 0) {
                var calculation = -(divisor shl (degree + 1))
                while (calculation >= dividend && calculation < 0 && calculation != Int.MIN_VALUE) {
                    degree += 1
                    calculation = -(divisor shl (degree + 1))
                }
                if (calculation == Int.MIN_VALUE) {
                    degree++
                }
            } else {
                var calculation = (divisor shl (degree + 1))
                while (calculation >= dividend && calculation < 0 && calculation != Int.MIN_VALUE) {
                    degree += 1
                    calculation = (divisor shl (degree + 1))
                }
            }
        }
        return degree
    }
}
