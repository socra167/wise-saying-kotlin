package com.global

import WiseSayingController
import WiseSayingService
import com.domain.wisesaying.controller.SystemController
import com.domain.wisesaying.repository.WiseSayingFileRepository
import com.domain.wisesaying.repository.WiseSayingMemRepository

object SingletonScope {
    val wiseSayingRepository by lazy { WiseSayingFileRepository() }
    val wiseSayingService by lazy { WiseSayingService() }
    val wiseSayingController by lazy { WiseSayingController() }
    val systemController by lazy { SystemController() }
}