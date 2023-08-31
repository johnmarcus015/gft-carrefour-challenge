package com.ztx.skeleton.utils

object JsonReaderUtils {

    fun readJsonFromResources(fileName: String): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
            ?: throw IllegalArgumentException("File not found!")

        return inputStream.bufferedReader().use { it.readText() }
    }
}
