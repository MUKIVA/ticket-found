package com.github.mukiva.ticketfound.uikit

import kotlin.math.abs

fun Int.formatAsCurrency(): String {
    val stringBuilder = StringBuilder()
    val requireMinus = this < 0
    var current = abs(this)
    var blockCount = 0
    while (current != 0) {
        if (blockCount == 3) {
            blockCount = 0
            stringBuilder.insert(0," ")
        } else {
            val digit = current % 10
            current /= 10
            stringBuilder.insert(0, digit)
            blockCount++
        }
    }
    if (requireMinus) {
        stringBuilder.insert(0, "-")
    }
    return stringBuilder.toString()
}