package com.roma.berendeev.hackerrank

import org.junit.Test

import org.junit.Assert.*
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HRMedium {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
//        val res = climbingLeaderboard(arrayOf(20,15,15,10,10,5, 3, 2), arrayOf(3, 10, 15, 20, 26))
//        res.forEach {
//            println(it)
//        }
//        println("subset length " + nonDivisibleSubset(4, arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
//        println("subset length " + nonDivisibleSubset(1, arrayOf(1, 2, 3, 4, 5)))
//        println("subset length " + nonDivisibleSubset(3, arrayOf(1, 7, 2, 4)))
//        println("subset length " + nonDivisibleSubset(7, arrayOf(278, 576, 496, 727, 410, 124, 338, 149, 209, 702, 282, 718, 771, 575, 436)))
        extraLongFactorials(25)
    }

    private fun climbingLeaderboard(scores: Array<Int>, alice: Array<Int>): Array<Int> {
        val aliceRanks = Array(alice.size, { 0 })
        val cleanedScores = scores.distinct()
        var rankIndex = cleanedScores.size - 1
        var aliceRankIndex = 0
        while (aliceRankIndex < aliceRanks.size) {
            val aliceScore = alice[aliceRankIndex]
            when {
                aliceScore < cleanedScores[rankIndex] -> {
                    aliceRanks[aliceRankIndex] = rankIndex + 1 + 1
                    aliceRankIndex++
                }
                aliceScore == cleanedScores[rankIndex] -> {
                    aliceRanks[aliceRankIndex] = rankIndex + 1
                    aliceRankIndex++
                }
                aliceScore > cleanedScores[rankIndex] && rankIndex > 0 && aliceScore < cleanedScores[rankIndex - 1] -> {
                    aliceRanks[aliceRankIndex] = rankIndex + 1
                    aliceRankIndex++
                }
                rankIndex == 0 && aliceScore >= cleanedScores[rankIndex] -> {
                    aliceRanks[aliceRankIndex] = rankIndex + 1
                    aliceRankIndex++
                }
                else -> rankIndex--
            }
        }
        return aliceRanks
    }

    private fun nonDivisibleSubset(k: Int, S: Array<Int>): Int {
        fun countBaskets(k: Int, S: Array<Int>): Array<Int> {
            val hasHalfBasket = k % 2 == 0
            val halfIndex = k / 2
            val baskets = Array(k, { 0 })
            S.forEach { sValue ->
                val index = sValue % k
                if (index == 0 || (hasHalfBasket && index == halfIndex)) {
                    if (baskets[index] == 0) {
                        baskets[index] = 1
                    }
                } else {
                    baskets[index]++
                }
            }
            return baskets
        }

        fun countSubsets(baskets: Array<Int>): Int {
            fun findMaxFromPair(index: Int): Int {
                return Math.max(baskets[index], baskets[baskets.size - index])
            }

            var result = baskets[0]
            if (k % 2 == 0) {
                result += baskets[k / 2]
                (1 until k / 2).forEach { result += findMaxFromPair(it) }
            } else {
                (1 until k / 2 + 1).forEach { result += findMaxFromPair(it) }
            }
            return result
        }

        val baskets = countBaskets(k, S)
        return countSubsets(baskets)
    }

    private fun extraLongFactorials(n: Int) {

        class MyBigDecimal(initValue: Int = 0) {
            private val partOverflowValue = 1_000_000_000
            private val parts = mutableListOf(initValue)

            fun multiply(value: Int) {
                var overflow = 0L
                var mult: Long

                val longValue = value.toLong()
                var partNumber = 0
                while (partNumber < parts.size) {
                    val part = parts[partNumber]
                    mult = part * longValue + overflow
                    if (mult >= partOverflowValue) {
                        overflow = mult / partOverflowValue
                        mult %= partOverflowValue
                    } else {
                        overflow = 0
                    }
                    parts[partNumber] = mult.toInt()
                    partNumber++
                    if (partNumber == parts.size && overflow > 0) {
                        parts.add(0)
                    }
                }
            }

            override fun toString(): String {
                return buildString {
                    parts.mapIndexed { index, value ->
                        if (index != parts.lastIndex) {
                            String.format("%09d", value)
                        } else {
                            "$value"
                        }
                    }.forEach { insert(0, it) }
                }
            }
        }

        val myBigInt = MyBigDecimal(1)
        (1..n).forEach { myBigInt.multiply(it) }
        println(myBigInt.toString())
    }
}
