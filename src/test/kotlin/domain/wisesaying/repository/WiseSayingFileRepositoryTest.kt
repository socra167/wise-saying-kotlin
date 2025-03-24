package domain.wisesaying.repository

import TestBot
import com.domain.wisesaying.entity.WiseSaying
import com.global.SingletonScope.wiseSayingRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
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
    fun findById() {
        val wiseSaying = wiseSayingRepository
            .save(WiseSaying(saying = "내 죽음을 적에게 알리지 말라", author = "이순신"))

        val result = wiseSayingRepository.findById(1)

        assertThat(result).isEqualTo(wiseSaying)
    }

    @Test
    fun `delete`() {
        val wiseSaying = wiseSayingRepository
            .save(WiseSaying(saying = "나의 죽음을 적들에게 알리지 말라.", author = "충무공 이순신"))

        wiseSayingRepository.delete(wiseSaying)

        assertThat(wiseSayingRepository.findById(wiseSaying.id)).isNull()
    }

    @Test
    fun `빌드`() {
        val result = TestBot.run(
            """
             등록
             나의 죽음을 적들에게 알리지 말라.
             충무공 이순신
             등록
             천재는 99%의 노력과 1%의 영감이다.
             에디슨
             빌드
         """
        )

        assertThat(result)
            .contains("data.json 파일의 내용이 갱신되었습니다.");
    }

    @Test
    fun `목록(검색)`() {
        val result = TestBot.run(
            """
             등록
             현재를 사랑하라.
             작자미상
             등록
             과거에 집착하지 마라.
             작자미상
             목록?keywordType=content&keyword=과거
         """
        )

        assertThat(result)
            .contains("----------------------")
            .contains("검색타입 : content")
            .contains("검색어 : 과거")

        assertThat(result)
            .doesNotContain("1 / 작자미상 / 현재를 사랑하라.")
            .contains("2 / 작자미상 / 과거에 집착하지 마라.")
    }
}