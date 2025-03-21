package com.global

class Request(
    val input: String,
) {
    var actionName: String = ""
    val paramMap = mutableMapOf<String, String>()

    init {
        val inputBits = input.split("?", limit = 2)
        actionName = inputBits[0]

        if (inputBits.size == 2) {
            val params = inputBits[1].split("&")
            params.forEach {
                val paramBits = it.split("=", limit = 2)
                if (paramBits.size == 2) {
                    paramMap[paramBits.get(0)] = paramBits.get(1)
                }
            }
        }
    }

    fun getParam(key: String): String? {
        return paramMap[key]
    }
}