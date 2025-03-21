package com.domain.wisesaying.repository

import com.domain.wisesaying.entity.WiseSaying

interface WiseSayingRepository {
    fun save(wiseSaying: WiseSaying): WiseSaying
    fun findAll(): List<WiseSaying>
    fun findById(id: Int): WiseSaying?
    fun remove(wiseSaying: WiseSaying)
    fun clear()
}