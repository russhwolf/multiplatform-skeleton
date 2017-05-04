package io.intrepid.skotlinton.testutils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

object TestFileUtils {
    fun convertStreamToString(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        try {
            var line = reader.readLine()
            while (line != null) {
                sb.append(line).append("\n")
                line = reader.readLine()
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }

        return sb.toString()
    }
}
