package com.functional.training
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class CaptureIoTests {

    @Test
    fun `capture just output `() {
        val output = captureOutput(inputs()) { puts("Welcome to mars rover kata!") }

        assertThat(output,`is`("Welcome to mars rover kata!\n"))
    }

    @Test
    fun `capture input and output`() {
        val output = captureOutput(inputs("10:20"))
        {
                    reads()
         .flatMap { input: String -> puts("sample output $input") }
        }

        assertThat(output,`is`("sample output 10:20\n"))
    }


}