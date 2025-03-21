package com.global

import WiseSayingController
import WiseSayingService
import com.domain.wisesaying.controller.SystemController
import com.domain.wisesaying.repository.WiseSayingRepository

object SingletonScope {
    val wiseSayingRepository by lazy { WiseSayingRepository() }
    val wiseSayingService by lazy { WiseSayingService() }
    val wiseSayingController by lazy { WiseSayingController() }
    val systemController by lazy { SystemController() }
}