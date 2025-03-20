package com.global

class Request(
    input: String,
) {
    var actionName: String = ""
    val params = mutableMapOf<String, String>()
    init {

    }
}