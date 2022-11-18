package com.workmanagerwithroomdb.modal.quoteResponse

data class QuoteResponse(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
    )