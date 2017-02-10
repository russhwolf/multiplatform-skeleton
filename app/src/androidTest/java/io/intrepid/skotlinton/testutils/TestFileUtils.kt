package io.intrepid.skotlinton.testutils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object TestFileUtils {
    fun convertStreamToString(`is`: InputStream): String? {
        val reader = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()
        try {
            var line: String? = reader.readLine()
            while (line != null) {
                sb.append(line).append("\n")
                line = reader.readLine()
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return sb.toString()
    }
}
