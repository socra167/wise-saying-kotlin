package com.standard

object JsonUtil {
    fun jsonStrToMap(jsonStr: String): Map<String, Any> {

        val replacedJsonStr = jsonStr.replace("{", "")
            .replace("}", "")
            .replace("\n", "")
            .replace(" : ", ":")

        return replacedJsonStr.split(",").associate { pair ->
            val bits = pair.split(":", limit = 2)
            val key = bits[0].trim().replace("\"", "")
            val valueStr = if (bits[1].trim().startsWith("\"")) {
                bits[1].trim().removeSurrounding("\"")
            } else {
                bits[1].trim().toInt()
            }
            key to valueStr
        }
    }
}