package com.global

import WiseSayingController
import WiseSayingService
import com.domain.wisesaying.controller.SystemController
import com.domain.wisesaying.repository.WiseSayingRepository

object SingletonScope {
    val wiseSayingRepository = WiseSayingRepository()
    val wiseSayingService = WiseSayingService()
    val wiseSayingController = WiseSayingController()
    val systemController = SystemController()
}