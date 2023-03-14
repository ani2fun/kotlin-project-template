package com.example.mypackage.testing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SimpleJunitTest {

    @Test
    fun testAdd() {
        assertEquals("One", "One")
    }
}
