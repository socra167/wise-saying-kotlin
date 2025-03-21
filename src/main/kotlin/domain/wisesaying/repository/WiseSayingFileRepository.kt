package com.domain.wisesaying.repository

import com.domain.wisesaying.entity.WiseSaying
import com.global.AppConfig
import java.nio.file.Path

class WiseSayingFileRepository : WiseSayingRepository {

    init {
        initTable()
    }

    private var lastId = 0

    val tableDirPath: Path
        get() = AppConfig.tableDirPath.resolve("wiseSaying")

    override fun save(wiseSaying: WiseSaying): WiseSaying {
        if (wiseSaying.isNew()) {
            val new = wiseSaying.copy(id = ++lastId)
            saveOnDisk(new)
            return new
        }

        saveOnDisk(wiseSaying)
        return wiseSaying
    }

    private fun saveOnDisk(wiseSaying: WiseSaying) {
        tableDirPath.resolve("${wiseSaying.id}.json").toFile().writeText(wiseSaying.jsonString)
    }

    override fun findAll(): List<WiseSaying> {
        return emptyList()
    }

    override fun findById(id: Int): WiseSaying? {
        return findById(id)
    }

    override fun remove(wiseSaying: WiseSaying) {
    }

    override fun clear() {
    }

    fun initTable() {
        // scope 함수 중 run 은 매개변수를 객체로 받는다
        tableDirPath.toFile().run {
            if (!exists()) {
                mkdirs() // 경로에 필요한 디렉토리를 만든다
            }
        }
    }

    fun saveLastId(id: Int) {
        tableDirPath.resolve("lastId.txt").toFile().writeText(id.toString())
    }

    fun loadLastId(): Int {
        return tableDirPath.resolve("lastId.txt").toFile().readText().toIntOrNull() ?: 0
    }
}