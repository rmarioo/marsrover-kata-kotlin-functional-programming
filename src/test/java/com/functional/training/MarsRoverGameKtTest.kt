package com.functional.training

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class MarsRoverGameKtTest {


    @Test
    fun acceptance() {
        val output = captureOutput(inputs("10","10",
                                          "2","2",
                                          "f","f","r","e")) { run() }

        assertThat(output,
                `is`(
                        """
                            welcome to mars rover kata
                            planet width ?
                            planet height ?
                            rover position x ?
                            rover position y ?
                            command ?
                            command ?
                            command ?
                            command ?
                            E:2,4

                            """.trimIndent()
                )
        )
    }

}