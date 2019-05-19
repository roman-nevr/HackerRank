package com.roma.berendeev.yandex

import com.roma.berendeev.contest.StringInputStream
import org.junit.Test
import java.io.InputStream

private const val MINUS = '-'
class RemoveDoubles {

    @Test
    fun remove_doubles() {
        val array = ArrayList<Int>()
        (1..10).forEach {
            array.add(it - 20)
            array.add(it - 20)
        }
        array.add(0, array.size)
        val inputStream = StringInputStream(array.joinToString(separator = '\n'.toString()) { it.toString() })

        val count = inputStream.readInt()
        if (count == 0) {
            return
        }

        val charMap = ('0'..'9').map { it to it.toString() }

        var previous = inputStream.readInt()
        println(previous)

        var current: Int
        for (i in 1 until count) {
            current = inputStream.readInt()
            if (current != previous) {
                println(current)
                previous = current
            }
        }
    }
}

private fun InputStream.readInt(): Int {
    var readResult = read()
    if (readResult == -1) return -1
    var isNegative = false
    if (readResult.toChar() == MINUS) {
        isNegative = true
        readResult = read()
    }
    var sum = 0
    val cr = '\n'.toInt()
    val zero = '0'.toInt()
    while (readResult != -1 && readResult != cr) {
        sum = sum * 10 + readResult - zero
        readResult = read()
    }
    return sum * if (isNegative) -1 else 1
}