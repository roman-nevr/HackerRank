package com.roma.berendeev.yandex

import org.junit.Test

class Contest {

    @Test
    fun generateGaps() {

        val count = 3
        if (count == 0) return

        openGap(StringBuilder(), 0, count)

        println("-----------------")
        handleGaps(StringBuilder().append('('), 1, count)
        println("-----------------")

        var level = 1
        val stack = arrayListOf<Int>()
        var openGaps = 1
        val gaps = StringBuilder().append('(')
        while (level < 2 * count && stack.isNotEmpty()) {
            if (level == 2 * count) {
                println(gaps)
                level = stack.removeAt(stack.lastIndex)
                gaps.delete(level, gaps.length)
            } else {
                if (openGaps > 0 && ) {
                    stack.add(level)
                }
                if (openGaps < count) {
                    gaps.append('(')
                    level++
                }
            }
        }
    }

    private fun handleGaps(gaps: StringBuilder, openGaps: Int, count: Int) {
        val start = gaps.length
        if (gaps.length == count * 2) {
            println(gaps)
            return
        }
        if (openGaps <= count && gaps.length + openGaps < count * 2) {
            handleGaps(gaps.append('('), openGaps + 1, count)
        }
        gaps.delete(start, gaps.length)
        if (openGaps > 0) {
            handleGaps(gaps.append(')'), openGaps - 1, count)
        }
    }

    private fun openGap(gaps: StringBuilder, openGaps: Int, count: Int) {
        gaps.append('(')
        val start = gaps.length
        if (openGaps + 1 <= count && gaps.length + openGaps + 1 < count * 2) {
            openGap(gaps, openGaps + 1, count)
        }
        gaps.delete(start, gaps.length)
        if (openGaps + 1 > 0) {
            closeGap(gaps, openGaps + 1, count)
        }
    }

    private fun closeGap(gaps: StringBuilder, openGaps: Int, count: Int) {
        gaps.append(')')
        val start = gaps.length
        if (gaps.length == count * 2) {
            println(gaps)
            if (gaps[gaps.length - 1] == '(') {
                System.gc()
            }
            return
        }
        if (openGaps - 1 <= count && gaps.length + openGaps - 1 < count * 2) {
            openGap(gaps, openGaps - 1, count)
        }
        if (start == count / 2) {
            System.gc()
        }
        gaps.delete(start, gaps.length)
        if (openGaps - 1 > 0) {
            closeGap(gaps, openGaps - 1, count)
        }
    }
}

