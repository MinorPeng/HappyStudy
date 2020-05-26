package com.hesheng1024.happystudy

import java.util.*
import kotlin.collections.HashMap

/**
 *
 * @author hesheng1024
 * @date 2020/4/18 10:42
 */

interface A

class AImpl : A

class AImpl1 : A

fun main(args: Array<String>) {
    val str: A? = AImpl()
    val str1 = "str"
    str?.takeIf {
        println("take if:$it")
        it is AImpl
    }.let { println("$it") }

    val map = HashMap<String, Float>()

    var value: Float
    value = map.getOrElse("") { 1f }
    println(value)

    println()

    println(Calendar.getInstance().get(Calendar.HOUR))
    println(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
    println(Calendar.getInstance().get(Calendar.MINUTE))
    println(Calendar.getInstance().get(Calendar.SECOND))
    println(Calendar.getInstance().get(Calendar.YEAR))
    println(Calendar.getInstance().get(Calendar.MONTH))
    println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
    println(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
    println(Calendar.getInstance().get(Calendar.DAY_OF_WEEK_IN_MONTH))
}
