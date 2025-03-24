package domain.wisesaying.repository

import com.domain.wisesaying.entity.WiseSaying
import com.global.SingletonScope.wiseSayingRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class WiseSayingFileRepositoryTest {

    @BeforeEach
    fun beforeEach() {
        wiseSayingRepository.clear()
        wiseSayingRepository.initTable()
    }

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
    fun `명언 2개 저장`() {
        wiseSayingRepository
            .save(WiseSaying(saying = "인생은 짧고, 예술은 길다.", author = "헨리 장"))
        wiseSayingRepository
            .save(WiseSaying(saying = "내 죽음을 적에게 알리지 말라", author = "이순신"))

        val lastId = wiseSayingRepository.loadLastId()

        assertThat(lastId).isEqualTo(3)
    }

    @Test
    fun findAll() {
        val wiseSaying1 = wiseSayingRepository
            .save(WiseSaying(saying = "인생은 짧고 예술은 길다.", author = "헨리 장"))
        val wiseSaying2 = wiseSayingRepository
            .save(WiseSaying(saying = "내 죽음을 적에게 알리지 말라", author = "이순신"))

        val result = wiseSayingRepository.findAll()
        val count = result.size

        assertThat(count).isEqualTo(2)
        assertThat(result).containsExactly(wiseSaying1, wiseSaying2)
    }

    @Test
    @DisplayName("display_name_value")
    fun findById() {
        val wiseSaying = wiseSayingRepository
            .save(WiseSaying(saying = "내 죽음을 적에게 알리지 말라", author = "이순신"))

        val result = wiseSayingRepository.findById(1)

        assertThat(result).isEqualTo(wiseSaying)
    }
}