package com.functional.training

import arrow.effects.IO
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PrintStream


fun captureOutput(inputs: String, func: () -> IO<Any>): String  {
    val swapStreams = { inputStream: InputStream, printStream: PrintStream ->
        System.setIn(inputStream)
        System.setOut(printStream)
    }
    val initialOut = System.out
    val initialIn = System.`in`
    val byteArrayOutputStream = ByteArrayOutputStream()
    swapStreams(ByteArrayInputStream(inputs.toByteArray()), PrintStream(byteArrayOutputStream))

    func().unsafeRunSync()

    swapStreams(initialIn, initialOut)
    return byteArrayOutputStream.toString()
}

fun inputs(vararg value: String): String =
        value.joinToString(enter)


private val enter = System.getProperty("line.separator")

