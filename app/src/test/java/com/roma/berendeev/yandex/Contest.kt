package com.roma.berendeev.yandex

import org.junit.Test
import java.util.LinkedList

class Contest {

    @Test
    fun generateGaps() {

        val count = 4
        if (count == 0) return

        openGap(StringBuilder(), 0, count)

        println("-----------------")
        handleGaps(StringBuilder().append('('), 1, count)
        println("-----------------")

        var level = 1
        val levelStack = LinkedList<Int>()
        var openGaps = 1
        val gapsStack = LinkedList<Int>()
        val gaps = CharArray(count * 2) { '(' }
        while (level < 2 * count || levelStack.isNotEmpty()) {
            if (level == 2 * count) {
                println(gaps)
                if (levelStack.isNotEmpty()) {
                    level = levelStack.pop()
                    openGaps = gapsStack.pop()
                    gaps[level] = ')'
                    openGaps--
                    level++
                }
            } else {
                val canAddCloseGap = openGaps > 0
                val canAddOpenGap = openGaps + level < count * 2
                if (canAddCloseGap && canAddOpenGap) {
                    levelStack.push(level)
                    gapsStack.push(openGaps)
                    gaps[level] = '('
                    openGaps++
                } else {
                    if (canAddOpenGap) {
                        gaps[level] = '('
                        openGaps++
                    } else {
                        gaps[level] = ')'
                        openGaps--
                    }
                }
                level++
            }
        }
        println(gaps)
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

