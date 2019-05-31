package com.roma.berendeev.yandex

import com.roma.berendeev.contest.StringInputStream
import org.junit.Test
import java.io.InputStream

private const val MINUS = '-'.toInt()
private const val CR = '\n'
class RemoveDoubles {

    @Test
    fun remove_doubles() {
        val array = ArrayList<Int>()
        (1..100).forEach {
            array.add(it - 20)
            array.add(it - 20)
        }
        array.add(0, array.size)
        val inputStream = StringInputStream(array.joinToString(separator = CR.toString()) { it.toString() })

        charDoubles(inputStream)
    }
}

private fun removeDoubles(inputStream: InputStream) {
    val count = readInt(inputStream)
    if (count == 0) {
        return
    }

    var previous = readInt(inputStream)
    println(previous)

    var current: Int
    for (i in 1 until count) {
        current = readInt(inputStream)
        if (current != previous) {
            println(current)
            previous = current
        }
    }
}

private fun readInt(inputStream: InputStream): Int {
    var readResult = inputStream.read()
    if (readResult == -1) return -1
    var isNegative = false
    if (readResult == MINUS) {
        isNegative = true
        readResult = inputStream.read()
    }
    var sum = 0
    val zero = '0'.toInt()
    while (readResult != -1 && readResult != CR.toInt()) {
        sum = sum * 10 + readResult - zero
        readResult = inputStream.read()
    }
    return sum * if (isNegative) -1 else 1
}

private fun charDoubles(inputStream: InputStream) {
    val count = readInt(inputStream)
    if (count == 0) {
        return
    }

    val bufferMap = Array(12) { CharArray(it + 1) }
    val chars = CharArray(12)
    for (i in 0 until count) {
        printIfNotTheSame(inputStream, chars, bufferMap)
    }
}

private fun printIfNotTheSame(inputStream: InputStream, charArray: CharArray, bufferMap: Array<CharArray>) {
    var readResult = inputStream.read()
    var index = 0
    var isSame = true
    while (readResult != CR.toInt() && readResult != -1) {
        if (charArray[index] != readResult.toChar()) {
            charArray[index] = readResult.toChar()
            isSame = false
        }
        index++
        readResult = inputStream.read()
    }
    if (!isSame) {
        val buffer = bufferMap[index - 1]
        System.arraycopy(charArray, 0, buffer, 0, index)
        println(buffer)
    }
}