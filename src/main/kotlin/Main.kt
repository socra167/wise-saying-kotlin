package com

fun main() {
    var lastId = 0;

    println("== 명언 앱 ==")
    while (true) {
        print("입력)")
        val input = readlnOrNull() ?: ""

        when (input) {
            "종료" -> break;
            "등록" -> {
                print("명언: ")
                val saying = readlnOrNull() ?: ""
                print("작가: ")
                val author = readlnOrNull() ?: ""

                println("${lastId + 1}번 $author : $saying 명언이 등록되었습니다.")
                lastId++
            }
        }
    }
}