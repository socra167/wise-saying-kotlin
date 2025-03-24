package com.standard

data class Page<T> (
    val content: List<T>,
    val totalCount: Int,
    val page: Int,
    val pageSize: Int
) {
    val totalPages: Int
        get() = Math.ceil(totalCount.toDouble() / pageSize).toInt()
}