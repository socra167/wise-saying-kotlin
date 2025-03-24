package com.domain.wisesaying.repository

import com.domain.wisesaying.entity.WiseSaying
import com.standard.Page

class WiseSayingMemRepository : WiseSayingRepository {
    private var lastId: Int = 0
    private val wiseSayings = mutableListOf<WiseSaying>()

    override fun save(wiseSaying: WiseSaying): WiseSaying {
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

    override fun findAll(): List<WiseSaying> {
        return wiseSayings.toList()
    }

    override fun delete(wiseSaying: WiseSaying) {
        wiseSayings.remove(wiseSaying)
    }

    override fun findBySayingLike(keyword: String): List<WiseSaying> {
        TODO("Not yet implemented")
    }

    override fun findByAuthorLike(keyword: String): List<WiseSaying> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): WiseSaying? {
        return wiseSayings.find { it.id == id }
    }

    override fun findByAuthorLikePaged(keyword: String, page: Int, pageSize: Int): Page<WiseSaying> {
        TODO("Not yet implemented")
    }

    override fun findBySayingLikePaged(keyword: String, page: Int, pageSize: Int): Page<WiseSaying> {
        TODO("Not yet implemented")
    }

    override fun findAllPaged(page: Int, pageSize: Int): Page<WiseSaying> {
        TODO("Not yet implemented")
    }

    override fun clear() {
        wiseSayings.clear()
        lastId = 0
    }
}