package com.domain.wisesaying.repository

import com.domain.wisesaying.entity.WiseSaying
import com.global.AppConfig
import com.standard.JsonUtil
import com.standard.Page
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
        // listFiles(): Path 에 속해 있는 모든 파일 목록을 가져온다
        // endsWith() 로 해도 되지만, extension 으로 확장자명을 불러올 수 있다
        return tableDirPath.toFile()
            .listFiles()
            ?.filter { it.extension == "json" }
            ?.map { WiseSaying.fromJson(it.readText()) }
            ?.sortedByDescending { it.id }
            .orEmpty()
    }

    override fun findById(id: Int): WiseSaying? {
        return tableDirPath.resolve("${id}.json").toFile()
            .takeIf { it.exists() } // 없으면 null 반환
            ?.let { return WiseSaying.fromJson(it.readText()) }
    }

    override fun delete(wiseSaying: WiseSaying) {
        tableDirPath.resolve("${wiseSaying.id}.json").toFile().delete()
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

    private fun getNextId(): Int {
        // also 는 람다의 파라미터 it 으로 넘어온다. 반환은 람다값이 아니라, 객체 자신이다
        // 부수작업을 끝내고 loadLastId의 가중값을 바로 반환하려면 also 가 적합하다
        return loadLastId().also {
            saveLastId(it + 1)
        }
    }

    fun build() {
        val mapList = findAll().map {
            it.map
        }

        JsonUtil.listToJson(mapList).also {
            tableDirPath.resolve("data.json").toFile().writeText(it)
        }
    }

    override fun findByAuthorLikePaged(keyword: String, page: Int, pageSize: Int): Page<WiseSaying> {
        val searched = findAll()
            .filter { it.author.contains(keyword) }
        val totalCount = searched.size

        if (keyword.isBlank()) return findAllPaged(page, pageSize)
        val searchedWiseSayings = searched
            .drop((page - 1) * pageSize)
            .take(pageSize)

        return Page(searchedWiseSayings, totalCount, page, pageSize)
    }

    override fun findAllPaged(page: Int, pageSize: Int): Page<WiseSaying> {
        val totalCount = findAll().size
        return findAll()
            .drop((page - 1) * pageSize)
            .take(pageSize)
            .let { Page(it, totalCount, page, pageSize) }
    }

    override fun findBySayingLikePaged(keyword: String, page: Int, pageSize: Int): Page<WiseSaying> {
        val searched = findAll()
            .filter { it.saying.contains(keyword) }
        val totalCount = searched.size

        if (keyword.isBlank()) return findAllPaged(page, pageSize)
        val searchedWiseSayings = searched
            .drop((page - 1) * pageSize)
            .take(pageSize)

        return Page(searchedWiseSayings, totalCount, page, pageSize)
    }

    override fun findByAuthorLike(keyword: String): List<WiseSaying> {
        if (keyword.isBlank()) return findAll()
        return findAll().filter { it.author.contains(keyword) }
    }

    override fun findBySayingLike(keyword: String): List<WiseSaying> {
        if (keyword.isBlank()) return findAll()
        return findAll().filter { it.saying.contains(keyword) }
    }
}