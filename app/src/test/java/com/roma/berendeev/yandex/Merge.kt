package com.roma.berendeev.yandex

import org.junit.Test
import java.io.InputStream

private const val CR = '\n'.toByte()
private const val SPACE = ' '.toByte()
private const val ZERO = '0'.toByte()
private const val END = (-1).toByte()
private const val STRING_SPACE = " "

class Merge {

    @Test
    fun merge_sorted_arrays() {
        val arrayList1 = arrayListOf(6, 2, 26, 64, 88, 96, 96)
        val arrayList2 = arrayListOf(4, 8, 20, 65, 86)
        val arrayList20 = arrayListOf(0)
        val arrayList3 = arrayListOf(7, 1, 4, 16, 42, 58, 61, 69)
        val arrayList4 = arrayListOf(1, 84)
        val arraysList = arrayListOf(arrayList1, arrayList2, arrayList20, arrayList3, arrayList4)
        val arraysString = arraysList.map { arrayList ->
            arrayList.joinToString(" ") { it.toString() }
        }.joinToString("\n") { it }
        val inputStream = Contest.StringStream("${arraysList.size}\n$arraysString")
        mergeArrays(inputStream)
    }
}

private fun mergeArrays(inputStream: InputStream) {
    val count = readInt(inputStream)
    if (count == 0) {
        return
    }

    val arrays = Array(count) { readArray(inputStream) }
    val indexArray = IntArray(count)
    var isAllMerged = false
    var minIndex = 0
    while (!isAllMerged) {
        var minNumber = Byte.MAX_VALUE
        isAllMerged = true
        for (index in 0 until count) {
            val number = arrays[index][indexArray[index]]
            if (number < minNumber) {
                minNumber = number
                minIndex = index
                isAllMerged = false
            }
        }
        var number = arrays[minIndex][indexArray[minIndex]]
        while (number == minNumber && !isAllMerged) {
            print(number)
            print(STRING_SPACE)
            indexArray[minIndex]++
            number = arrays[minIndex][indexArray[minIndex]]
        }
    }
    println()
    val result = "1 2 4 8 16 20 26 42 58 61 64 65 69 84 86 88 96 96 "
    println(result)
}

private fun readArray(inputStream: InputStream): ByteArray {
    val arrayLength = readInt(inputStream) + 1
    if (arrayLength == 1) return ByteArray(1) { Byte.MAX_VALUE }
    val intArray = ByteArray(arrayLength)
    var lineRead = false
    var index = 0
    var sum = 0.toByte()
    while (!lineRead) {
        val readResult = inputStream.read().toByte()
        lineRead = readResult == END || readResult == CR
        if (readResult == SPACE || lineRead) {
            intArray[index] = sum
            index++
            sum = 0
        } else {
            sum = (sum * 10 + readResult - ZERO).toByte()
        }
    }
    intArray[index] = Byte.MAX_VALUE
    return intArray
}

private fun readInt(inputStream: InputStream): Int {
    var readResult = inputStream.read().toByte()
    var sum = 0
    while (readResult != SPACE && readResult != CR) {
        sum = sum * 10 + readResult - ZERO
        readResult = inputStream.read().toByte()
    }
    return sum
}