package com

import com.domain.wisesaying.controller.SystemController
import com.domain.wisesaying.controller.WiseSayingController
import com.global.Request

class App {
    fun run() {
        val wiseSayingController = WiseSayingController()
        val systemController = SystemController()

        println("== 명언 앱 ==")
        while (true) {
            print("입력)")
            val input = readlnOrNull() ?: ""

            val rq = Request(input)

            when (rq.actionName) {
                "종료" -> {
                    systemController.exit()
                    break
                }

                "등록" -> wiseSayingController.write()
                "목록" -> wiseSayingController.list()
                "삭제" -> wiseSayingController.delete(rq)
                else -> {
                    println("알 수 없는 명령입니다.")
                }
            }
        }
    }
}