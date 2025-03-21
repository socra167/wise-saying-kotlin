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
        val target = if (wiseSaying.isNew()) wiseSaying.copy(id = getNextId()) else wiseSaying

        return target.also {
            saveOnDisk(target)
        }
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
        tableDirPath.toFile().deleteRecursively()
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
        return tableDirPath.resolve("lastId.txt").toFile().run {
            if (!exists()) {
                return 1
            }
            return readText().toInt()
        }
    }

    fun getNextId(): Int {
        // also 는 람다의 파라미터 it 으로 넘어온다. 반환은 람다값이 아니라, 객체 자신이다
        // 부수작업을 끝내고 loadLastId의 가중값을 바로 반환하려면 also 가 적합하다
        return loadLastId().also {
            saveLastId(it + 1)
        }
    }
}