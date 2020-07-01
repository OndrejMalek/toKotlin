package eu.malek.tokotlin

// Author Ondrej Malek - o.malek.cz@gmail.com

import java.lang.RuntimeException
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashMap
import kotlin.collections.LinkedHashSet


inline fun <reified K, reified V> Map<K,V>.toKotlin(): String {
    val map = this
    val factoryFun = when (map) {
        is SortedMap<K,V> -> "sortedMapOf"
        is LinkedHashMap<K,V> -> "linkedMapOf"
        is HashMap<K, V> -> "hashMapOf"
        is MutableMap<K, V> -> "mapOf"
        else -> "mapOf"
    }
    return collectionToKotlin(map.entries, factoryFun)
}

inline fun <reified K, reified V> Map.Entry<K,V>.toKotlin(): String {
    val entry = this
    return when (entry) {
        else -> "${nullableToKotlin(entry.key)} to ${nullableToKotlin(entry.value)}"
    }
}

inline fun <reified E> Collection<E>.toKotlin(): String {
    return collectionToKotlin(this)
}

inline fun <reified E> collectionToKotlin(collection: Collection<E>): String {
    return when (collection) {
        is ArrayList -> collectionToKotlin(collection, "mutableListOf")
        is LinkedHashSet -> collectionToKotlin(collection, "linkedSetOf")
        is HashSet -> collectionToKotlin(collection, "hashSetOf")
        is TreeSet -> collectionToKotlin(collection, "sortedSetOf")
        is Set -> collectionToKotlin(collection, "setOf")
        else -> collectionToKotlin(collection, "listOf")
    }
}

val LIST_DIV = ", "

inline fun <reified E> collectionToKotlin(list: Collection<E>, factory: String): String {
    if (list.isEmpty()) return "$factory<${E::class.simpleName}>()"
    val factory2 = if (list.all { it == null }) "${factory}<${E::class.simpleName}?>" else factory

    val sb = StringBuilder("$factory2(")
    for (e in list) {
        sb.append(nullableToKotlin(e)).append(LIST_DIV)
    }

    sb.replace(sb.length - LIST_DIV.length, sb.length, "").append(")")
    return sb.toString()
}

inline fun <reified E> nullableToKotlin(e: E) = if (e == null) "null" else e!!.toKotlin()

inline fun <reified E> Array<E>.toKotlin(): String {
    return arrayToKotlin(this)
}

inline fun <reified E> arrayToKotlin(array: Array<E>): String {
    return when (array) {
        is ArrayList<*> -> collectionToKotlin(array, "arrayOf")
        else -> arrayToKotlin(array, "arrayOf")
    }
}


inline fun <reified E> arrayToKotlin(list: Array<E>, factory: String): String {
    if (list.isEmpty()) return "$factory<${E::class.simpleName}>()"
    if (list.all { it == null }) return "${factory}Nulls<${E::class.simpleName}>(${list.size})"

    val sb = StringBuilder("$factory(")
    for (i in 0 until list.lastIndex) {
        sb.append(nullableToKotlin(list[i])).append(LIST_DIV)
    }
    sb.append(nullableToKotlin(list.last())).append(")")
    return sb.toString()
}

fun String.toKotlin(): String {
    return "\"${this}\""
}

fun Number.toKotlin(): String {
    return this.toString()
}


fun Any.toKotlin(): String {
    return when (this) {
        is Number -> this.toKotlin()
        is String -> this.toKotlin()
        is Collection<*> -> this.toKotlin()
        is Array<*> -> (this as Array<Any>).toKotlin()
        is Map.Entry<*,*> -> this.toKotlin()

        else -> throw RuntimeException("Not implemented type + ${this.javaClass}")
    }
}