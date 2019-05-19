package com.roma.berendeev.yandex

import org.junit.Test

private const val CR = '\n'.toInt()

class Anagrams {

    @Test
    fun anagrams() {
        val inputStream = Contest.StringStream("\n")

        val intArray = IntArray(256)

        var readResult = inputStream.read()
        var isFirstString = true
        while (readResult != -1) {
            if (readResult == CR) {
                isFirstString = false
            } else {
                if (isFirstString) {
                    intArray[readResult]++
                } else {
                    intArray[readResult]--
                }
            }
            readResult = inputStream.read()
        }
        var isAllElementsAreZero = true
        intArray.forEach { element ->
            if (element != 0) {
                isAllElementsAreZero = false
            }
        }
        if (isAllElementsAreZero) {
            println(1)
        } else {
            println(0)
        }
    }

    fun isAnagramm() {
        val inputStream = System.`in`

        val intArray = IntArray(256)
        var readResult = inputStream.read()
        var isFirstString = true
        while (readResult != -1) {
            if (readResult == CR) {
                isFirstString = false
            } else {
                if (isFirstString) {
                    intArray[readResult]++
                } else {
                    intArray[readResult]--
                }
            }
            readResult = inputStream.read()
        }
        var isAllElementsAreZero = true
        intArray.forEach { element ->
            if (element != 0) {
                isAllElementsAreZero = false
            }
        }
        if (isAllElementsAreZero) {
            println(1)
        } else {
            println(0)
        }
    }
}
