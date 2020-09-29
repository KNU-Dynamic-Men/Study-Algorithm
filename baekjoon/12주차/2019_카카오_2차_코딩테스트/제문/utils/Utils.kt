package utils

class Utils {
    companion object {
        val BASE_URL = "http://3.35.80.226/"

        fun log(tag: String, message: Any) {
            println("$tag : $message")
        }
    }
}