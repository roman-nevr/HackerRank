package com.roma.berendeev.leetCode

import org.junit.Test

class IntReplacement {

    @Test
    fun run() {
        test(Int.MAX_VALUE)
        test(3)
        test(65535)
        test(1234)
        test(7)
        test(8)
    }

    private fun test(n: Int) {
        println("result of $n: ${integerReplacement(n)}")
    }

    fun integerReplacement(n: Int): Int {
        var result = n.toLong()
        var step = 0
        while (result != 1L) {
            result = operate(result)
            step++
        }
        return step
    }

    private fun operate(n: Long): Long {
        return when {
            n % 2 == 0L -> n/2
            ((n - 1)/2) % 2 == 0L || (n - 1) == 2L -> n - 1
            else -> n + 1
        }
    }
}
