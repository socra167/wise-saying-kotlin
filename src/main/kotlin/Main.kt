package com

import com.domain.wisesaying.entity.WiseSaying

fun main() {

    val wiseSayings = mutableListOf<WiseSaying>()

    var lastId = 0

    println("== 명언 앱 ==")
    while (true) {
        print("입력)")
        val input = readlnOrNull() ?: ""

        when (input) {
            "종료" -> break
            "등록" -> {
                print("명언: ")
                val saying = readlnOrNull() ?: ""
                print("작가: ")
                val author = readlnOrNull() ?: ""

                wiseSayings.add(WiseSaying(lastId, saying, author))

                println("${lastId + 1}번 $author : $saying 명언이 등록되었습니다.")
                lastId++
            }
            "목록" -> {
                println("번호 / 작가 / 명언")
                println("-------------------")
                wiseSayings.reversed().forEach {
                    println("${it.id + 1} / ${it.author} / ${it.saying}")
                }
            }
        }
    }
}