package com.domain.wisesaying.repository

import com.domain.wisesaying.entity.WiseSaying

class WiseSayingRepository {
    private var lastId: Int = 0
    private val wiseSayings = mutableListOf<WiseSaying>()

    fun save(wiseSaying: WiseSaying): WiseSaying {
        if (wiseSaying.isNew()) {
            val new = wiseSaying.copy(id = ++lastId)
            wiseSayings.add(new)
            return new
        }

        wiseSayings.indexOfFirst { it.id == wiseSaying.id }.let {
            wiseSayings[it] = wiseSaying
        }
        return wiseSaying
    }

    fun findAll(): List<WiseSaying> {
        return wiseSayings.toList()
    }

    fun remove(wiseSaying: WiseSaying) {
        wiseSayings.remove(wiseSaying)
    }

    fun findById(id: Int): WiseSaying? {
        return wiseSayings.find { it.id == id }
    }
}