package com.roma.berendeev.yandex

import org.junit.Test
import java.io.InputStream
import java.util.LinkedList


private const val CR = '\n'.toInt()
private const val ZERO = '0'.toInt()
private const val MINUS = '-'.toInt()

class Contest {

    @Test
    fun generateGaps() {

        val count = 10
        if (count == 0) return

//        openGap(StringBuilder(), 0, count)

        println("-----------------")
        handleGaps(CharArray(count * 2) { '(' }, 1, count, 1)
        println("-----------------")
        looperGapGenerator(count)
    }

    @Test
    fun removeDoubles() {
        val array = arrayListOf(-12,-12,-10,1,1,2,4,5,5,6,7,7,8,8,8,9)
        val inputStream = StringStream("${array.size}\n"+array.joinToString("\n") { it.toString() })
        val count = inputStream.readInt()

        if (count == 0) {
            return
        }

        for (number in 0 until count) {
            var previous = inputStream.readInt()
            println(previous)

            var current = previous
            for (i in 1 until count) {
                current = inputStream.readInt()
                if (current != previous) {
                    println(current)
                    previous = current
                }
            }
        }
    }

//    private fun fillCharArray

    private fun handleGaps(gaps: CharArray, openGaps: Int, count: Int, level: Int) {
        if (level == count * 2) {
            println(gaps)
            return
        }
        if (openGaps <= count && level + openGaps < count * 2) {
            gaps[level] = '('
            handleGaps(gaps, openGaps + 1, count, level + 1)
        }
        if (openGaps > 0) {
            gaps[level] = ')'
            handleGaps(gaps, openGaps - 1, count, level + 1)
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

    class StringStream(private val text: String): InputStream() {

        private var index = 0

        override fun read(): Int {
            if (index >= text.length) {
                return -1
            }
            return text[index++].toInt()
        }
    }
}

private fun handleGaps(gaps: CharArray, openGaps: Int, count: Int, level: Int) {
    if (level == count * 2) {
        println(gaps)
        return
    }
    if (openGaps <= count && level + openGaps < count * 2) {
        gaps[level] = '('
        handleGaps(gaps, openGaps + 1, count, level + 1)
    }
    if (openGaps > 0) {
        gaps[level] = ')'
        handleGaps(gaps, openGaps - 1, count, level + 1)
    }
}

private fun looperGapGenerator(count: Int) {
    var level = 1
    val stack = IntArray(count * 4)
    var stackSize = 0
    var openGaps = 1
    val gaps = CharArray(count * 2) { '(' }
    while (level < 2 * count || stackSize > 0) {
        if (level == 2 * count) {
            println(gaps)
            if (stackSize > 0) {
                level = stack[--stackSize]
                openGaps = stack[--stackSize]
                delete(gaps, level)
                gaps[level] = ')'
                openGaps--
                level++
            }
        } else {
            val canAddCloseGap = openGaps > 0
            val canAddOpenGap = openGaps + level < count * 2
            if (canAddCloseGap && canAddOpenGap) {
                stack[stackSize++] = openGaps
                stack[stackSize++] = level
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

private fun delete(charArray: CharArray, start: Int) {
    (start until charArray.size).forEach {
        charArray[it] = ' '
    }
}

private fun InputStream.printIfNotTheSame(intArray: IntArray): IntArray {
    var readResult = read()
    if (readResult == -1) return intArray
    var index = 0
    var isTheSame = true
    while (readResult != -1 && readResult != CR) {
        if (intArray[index] != readResult) {
            isTheSame = false
        }
        intArray[index] = readResult
        readResult = read()
        index++

    }
    if (!isTheSame) {
        for (i in 0 until index) {
            print(intArray[i].toChar())
        }
        println()
    }
    return intArray
}

private fun InputStream.readInt(): Int {
    var readResult = read()
    if (readResult == -1) return -1
    var isNegative = false
    if (readResult == MINUS) {
        isNegative = true
        readResult = read()
    }
    var sum = 0
    while (readResult != -1 && readResult != CR) {
        sum = sum * 10 + readResult - ZERO
        readResult = read()
    }
    return if (isNegative) {
        return -sum
    } else {
        return sum
    }
}
