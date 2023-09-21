package com.example.newsappforandroid.core.base.parser

import java.lang.reflect.Type

interface BaseParser {
    fun <T> fromJson(json: String, type: Type): T?
    fun <T> toJson(obj: T, type: Type): String?
}