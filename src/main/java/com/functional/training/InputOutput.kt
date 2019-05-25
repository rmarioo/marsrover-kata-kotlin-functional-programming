package com.functional.training

import arrow.effects.IO

fun puts(message: String): IO<Unit> = IO { println(message) }

fun reads(): IO<String> = IO.just(readLine()!!)

fun askInt(message: String) =
        ask(message).map { s -> s.toInt() }
fun ask(message: String) =
        puts(message).flatMap { reads() }

