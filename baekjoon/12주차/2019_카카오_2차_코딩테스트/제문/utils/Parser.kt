package utils

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Parser(private val baseUrl: String) {
    // GET
    fun sendGet(route: String,
                headers: MutableList<Pair<String, String>> = mutableListOf(),
                params: MutableList<Any> = mutableListOf(),
                endContainsSlash: Boolean = false): String {

        val sb = StringBuilder()
        for (param in params) {
            sb.append("/$param")
        }

        val mURL = if (endContainsSlash) URL("$baseUrl$route$sb/") else URL("$baseUrl$route$sb")

        with(mURL.openConnection() as HttpURLConnection) {
            // default is GET
            requestMethod = "GET"

            for ( (type, value) in headers) {
                setRequestProperty(type, value)
            }

            Utils.log("URL", url)
            Utils.log("Response Code", responseCode)

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuilder()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.appendLine(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                Utils.log("Response", response)
                return response.toString()
            }
        }
        return ""
    }

    // POST
    fun sendPost(route: String,
                 headers: MutableList<Pair<String, String>> = mutableListOf(),
                 params: MutableList<Any> = mutableListOf(),
                 jsonBody: String = "",
                 endContainsSlash: Boolean = false): String {

        val sb = StringBuilder()
        for (param in params) {
            sb.append("/$param")
        }

        val mURL = if (endContainsSlash) URL("$baseUrl$route$sb/") else URL("$baseUrl$route$sb")

        with(mURL.openConnection() as HttpURLConnection) {
            requestMethod = "POST"

            for ( (type, value) in headers) {
                setRequestProperty(type, value)
            }

            if (jsonBody.isNotEmpty()) {
                Utils.log("Body", jsonBody)
                doOutput = true
                outputStream.write(jsonBody.toByteArray())
                outputStream.flush()
            }

            Utils.log("URL", url)
            Utils.log("Response Code", responseCode)

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuilder()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.appendLine(inputLine)
                    inputLine = it.readLine()
                }
                it.close()
                Utils.log("Response", response)
                return response.toString()
            }
        }
        return ""
    }
}