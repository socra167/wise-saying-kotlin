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

    fun listToJson(list: List<Map<String, Any>>): String {
        // Collectors.joining() 과 유사하다, 구분자로 문자열로 합치기
        return list.joinToString(
            prefix = "[\n", postfix = "\n]", separator = ",\n"
        ) {
            mapToJson(it).prependIndent("    ")
        }
    }

    private fun mapToJson(map: Map<String, Any>): String {
        // .entries: key, value Set
        return map.entries.joinToString(
            prefix = "{\n", postfix = "\n}", separator = ",\n"
        ) { (key, value) ->
            val formattedKey = "\"$key\""
            // instance of (Java) -> is (Kotlin)
            val formattedValue = when (value) {
                is String -> "\"$value\""
                else -> value
            }
            "    $formattedKey: $formattedValue"
        }
    }
}