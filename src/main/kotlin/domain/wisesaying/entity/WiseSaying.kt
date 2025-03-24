package com.domain.wisesaying.entity

import com.standard.JsonUtil

data class WiseSaying(
    val id: Int = 0,
    val saying: String,
    val author: String,
) {
    fun isNew(): Boolean {
        return this.id == 0
    }

    val jsonString: String
        get() = """
            {
                "id": $id,
                "saying": "$saying",
                "author": "$author"
            }
        """.trimIndent()

    val map: Map<String, Any>
        get() = mapOf(
            "id" to id,
            "saying" to saying,
            "author" to author
        )

    companion object {
        fun fromJson(jsonStr: String): WiseSaying {
            val jsonMap = JsonUtil.jsonStrToMap(jsonStr)
            return WiseSaying(
                id = jsonMap["id"] as Int,
                saying = jsonMap["saying"] as String,
                author = jsonMap["author"] as String
            )
        }
    }
}