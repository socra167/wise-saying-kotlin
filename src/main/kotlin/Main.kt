package com

fun main() {
    println("== 명언 앱 ==")
    while (true) {
        print("입력)")
        val input = readlnOrNull() ?: "" // null 이 들어올 수 있는 입력을 받는다. elvis 로 null 일 경우 값 지정
        if (input == "종료") {
            break
        }
    }
}