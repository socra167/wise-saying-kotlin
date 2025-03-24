package com.domain.wisesaying.repository

import com.domain.wisesaying.entity.WiseSaying
import com.standard.Page

interface WiseSayingRepository {
    fun save(wiseSaying: WiseSaying): WiseSaying
    fun findAll(): List<WiseSaying>
    fun findById(id: Int): WiseSaying?
    fun clear()
    fun delete(wiseSaying: WiseSaying)
    fun findBySayingLike(keyword: String): List<WiseSaying>
    fun findByAuthorLike(keyword: String): List<WiseSaying>
    fun findByAuthorLikePaged(keyword: String, page: Int, pageSize: Int): Page<WiseSaying>
    fun findBySayingLikePaged(keyword: String, page: Int, pageSize: Int): Page<WiseSaying>
    fun findAllPaged(page: Int, pageSize: Int): Page<WiseSaying>
}