package com.ryoga.kotlintest


data class Counter(var count: Int = 0, var i: Int = 0)

data class Test(var c: Counter = Counter(0))

class test {
    var c: Counter? = null

    fun add() {

    }
}