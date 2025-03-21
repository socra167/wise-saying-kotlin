package com.domain.wisesaying.repository

import com.domain.wisesaying.entity.WiseSaying

class WiseSayingFileRepository : WiseSayingRepository {
    private var lastId: Int = 0
    private val wiseSayings = mutableListOf<WiseSaying>()

    override fun save(wiseSaying: WiseSaying): WiseSaying {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<WiseSaying> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): WiseSaying? {
        TODO("Not yet implemented")
    }

    override fun remove(wiseSaying: WiseSaying) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }
}