package com.example.clean.utils

import java.text.SimpleDateFormat
import java.util.*

class DateConverter {

    private val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val outputDateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())

    fun convertDate(inputDate: String): String {
        val date: Date = inputDateFormat.parse(inputDate) as Date
        return outputDateFormat.format(date)
    }

}