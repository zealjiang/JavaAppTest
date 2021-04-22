package com.example.javaapptest

open class E {

    fun Runnable.toBar(): E = E();

    fun <T, O> Set<T>.map(func: (T) -> O): List<O> = ArrayList()// …

    fun <T> joinToString(
            separator: CharSequence = ", ",
            prefix: CharSequence = "",
            postfix: CharSequence = ""
    ): String {
        // …
        return ""
    }
}