package com.example.clean.data.remote.exception

class ApiException(
    code: Int,
    msg: String
) : Exception("API Error: $code - $msg")

