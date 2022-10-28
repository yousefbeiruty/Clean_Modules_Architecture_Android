package com.pioneers.cleanmodulesarchitecture.model

data class ListState<T>(
    val isLoading: Boolean = false,
    val data: List<T> = emptyList(),
    val error:String=""
)