package com.roma.berendeev.yandex

import org.junit.Test
import java.io.InputStream

private const val CR = '\n'.toByte()
private const val SPACE = ' '.toByte()
private const val ZERO = '0'.toByte()
private const val END = (-1).toByte()
class Merge {

    @Test
    fun merge() {
        val arrayList1 = arrayListOf(1,3,4,8)
        val arrayList2 = arrayListOf(2,5,14,18)
        val arrayList3 = arrayListOf(6,7,9,28)
        val arrayList4 = arrayListOf(10,33,41,81)
        val arraysList = arrayListOf(arrayList1, arrayList2, arrayList3, arrayList4)
        val arraysString = arraysList.map { arrayList ->
            arrayList.joinToString(" ") { it.toString() }
        }.joinToString("\n") { it }
        val inputStream = Contest.StringStream("${arraysList.size}\n$arraysString")
        val count = readArray(inputStream, 1)[0].toInt()
        if (count == 0) {
            return
        }

        val arrays = ArrayList<ByteArray>(count)
        for (i in 1..count) {
            arrays.add(readArray(inputStream, count))
        }
    }

    private fun readArray(inputStream: InputStream, count: Int): ByteArray {
        val intArray = ByteArray(10*count)
        var readResult = inputStream.read().toByte()
        var index = 0
        var sum = 0.toByte()
        while (readResult != CR && readResult != END) {
           if (readResult == SPACE) {
               intArray[index] = sum
               index++
               sum = 0
           } else {
               sum = (sum * 10 + readResult - ZERO).toByte()
           }
            readResult = inputStream.read().toByte()
        }
        intArray[index + 1] = -1
        return intArray
    }
}
