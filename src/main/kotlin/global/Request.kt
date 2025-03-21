package com.global

class Request(
    input: String,
) {
    var actionName: String = ""
    val paramMap: Map<String, String>

    init {
        val inputBits = input.split("?", limit = 2)
        actionName = inputBits[0]

        paramMap = if (inputBits.size == 2) {
            inputBits[1].split("&").associate {
                // 동적으로 Map 을 생성해 Pair 를 생성
                val bits = it.split("=", limit = 2)
                bits[0] to bits[1]
            }.toMap()
        } else {
            emptyMap()
        }
    }

    fun getParam(key: String): String? {
        return paramMap[key]
    }
}