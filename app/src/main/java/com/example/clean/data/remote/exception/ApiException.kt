package com.example.clean.data.remote.exception

class ApiException(
    private val code: Int,
    private val msg: String
) : Exception("API Error: $code - $msg")

