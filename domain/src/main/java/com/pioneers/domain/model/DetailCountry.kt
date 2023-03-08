package com.pioneers.domain.model

import org.intellij.lang.annotations.Language
import java.util.Currency

data class DetailCountry(
    val code:String,
    val name: String,
    val emoji:String,
    val capital:String,
    val currency:String,
    val languages:List<String>,
    val continent:String
)