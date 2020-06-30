package eu.malek.tokotlin

import kotlin.test.Test
import kotlin.test.*

class ToKotlinKtTest {

    @Test
    fun stringToKotlin() {
        assertEquals("\"Oleee, caramba!\"", "Oleee, caramba!".toKotlin())
        assertEquals("\"Oleee, \"caramba!\"", "Oleee, \"caramba!".toKotlin())
    }

    @Test
    fun collectionToKotlin() {
        assertEquals("mutableListOf<Int>()", mutableListOf<Int>().toKotlin())
        assertEquals("listOf(0, 2)", listOf(0, 2).toKotlin())
        assertEquals("listOf<Int>()", listOf<Int>().toKotlin())
        assertEquals("mutableListOf<Int?>(null, null)", mutableListOf<Int?>(null, null).toKotlin())
        assertEquals("setOf<Int>()", setOf<Int>().toKotlin())
        assertEquals("linkedSetOf<Int>()", linkedSetOf<Int>().toKotlin())
        assertEquals("hashSetOf<Int>()", hashSetOf<Int>().toKotlin())
        assertEquals("linkedSetOf<Int>()", mutableSetOf<Int>().toKotlin())
        assertEquals("sortedSetOf<Int>()", sortedSetOf<Int>().toKotlin())
        assertEquals("setOf<Int>()", setOf<Int>().toKotlin())

        assertEquals("listOf(listOf(1, 2, 3), listOf(4, 5, 6))", listOf(listOf(1, 2, 3), listOf(4, 5, 6)).toKotlin())
        assertEquals("listOf(listOf(1, 2, 3), mutableListOf(4, 5, 6))", listOf(listOf(1, 2, 3), mutableListOf(4, 5, 6)).toKotlin())

    }

    @Test
    fun mapsToKotlin() {
        var map = mapOf(1 to "one")
        for (entry in map) {

        }
        assertEquals("mapOf(1 to \"one\")", mapOf(1 to "one").toKotlin())
        assertEquals("hashMapOf(1 to \"one\")", hashMapOf(1 to "one").toKotlin())
        assertEquals("linkedMapOf(1 to \"one\")", linkedMapOf(1 to "one").toKotlin())
        assertEquals("sortedMapOf(1 to \"one\")", sortedMapOf(1 to "one").toKotlin())
    }

    @Test
    fun mapsToKotlin_DEFECTS() {
        assertEquals("linkedMapOf(1 to \"one\")", mutableMapOf(1 to "one").toKotlin())
    }

    @Test
    fun arrayToKotlin() {
        assertEquals("arrayOf<Int>()", arrayOf<Int>().toKotlin())
        assertEquals("arrayOf(1, 2)", arrayOf(1, 2).toKotlin())
        assertEquals("arrayOfNulls<Int>(2)", arrayOfNulls<Int>(2).toKotlin())
        assertEquals("arrayOf(arrayOf<Any>())", arrayOf(arrayOf<Any>()).toKotlin())
        assertEquals("arrayOf(arrayOf<Any>())", arrayOf(arrayOf<Int>()).toKotlin())
        assertEquals("arrayOf(arrayOf(1, 2))", arrayOf(arrayOf(1, 2)).toKotlin())
    }

    @Test
    fun arrayToKotlin_DEFECTS() {
        assertEquals("arrayOf(arrayOf<Any>())", arrayOf(arrayOf<Int>()).toKotlin())
    }
}