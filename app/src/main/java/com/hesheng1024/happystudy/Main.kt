package com.hesheng1024.happystudy

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
}
