package com.domain.wisesaying.controller

import com.domain.wisesaying.entity.WiseSaying
import com.global.Request

class WiseSayingController(
    var lastId: Int = 0,
) {
    val wiseSayings = mutableListOf<WiseSaying>()

    fun write() {
        print("명언: ")
        val saying = readlnOrNull() ?: ""
        print("작가: ")
        val author = readlnOrNull() ?: ""

        wiseSayings.add(WiseSaying(lastId, saying, author))

        println("${lastId}번 $author : $saying 명언이 등록되었습니다.")
        lastId++
    }

    fun list() {
        println("번호 / 작가 / 명언")
        println("-------------------")
        wiseSayings.reversed().forEach {
            println("${it.id} / ${it.author} / ${it.saying}")
        }
    }

    fun delete(rq: Request) {
        val id = rq.getParam("id")?.toIntOrNull()

        if (id == null) {
            println("삭제할 명언의 번호를 입력해주세요.")
            return
        }

        val rst = wiseSayings.removeIf { saying -> saying.id == id }

        if (rst) {
            println("${id}번 명언을 삭제했습니다.")
        } else {
            println("${id}번 명언은 존재하지 않습니다.")
        }
    }
}
