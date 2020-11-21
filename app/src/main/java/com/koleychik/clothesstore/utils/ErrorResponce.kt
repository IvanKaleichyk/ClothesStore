package com.koleychik.clothesstore.utils

import com.koleychik.clothesstore.R

fun errorResponse(code: Int): Int {

//    return "error $code"

    when (code) {
        400 -> return R.string.error_400
        403 -> return R.string.error_403
        404 -> return R.string.error_404

        500 -> return R.string.error_500
        502 -> return R.string.error_502
        503 -> return R.string.error_503
    }

    return R.string.error

//    400 -> "400 «Bad Request»"
//    403 -> "403 «Доступ запрещен»"
//    404 -> "404 – файл не найден"
//
//    500 -> "500 – ошибка сервера"
//    502 -> "502 – Bad Gateaway"
//    503 -> "503 – Service Temporarily Unavailable"

}