package domain.wisesaying.repository

import com.domain.wisesaying.entity.WiseSaying
import com.global.SingletonScope.wiseSayingRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WiseSayingFileRepositoryTest {

    @Test
    fun `save`() {
        val wiseSaying = wiseSayingRepository
            .save(WiseSaying(saying = "인생은 짧고, 예술은 길다.", author = "헨리 장"))

        val filePath = wiseSayingRepository
            .tableDirPath
            .toFile()
            .listFiles()
            ?.find { it.name == "${wiseSaying.id}.json" }

        assertThat(filePath).isNotNull
    }

    @Test
    fun `saveLastId, loadLastId`() {
        wiseSayingRepository.saveLastId(10)
        assertThat(wiseSayingRepository.loadLastId()).isEqualTo(10)
    }

    @Test
    fun findAll() {

    }

    @Test
    fun findById() {

    }

    @Test
    fun remove() {

    }

    @Test
    fun clear() {

    }
}