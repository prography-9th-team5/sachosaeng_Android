package com.example.sachosaeng.core.util.manager

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DateManager {
    fun getWeekDateRange(): String {
        val locale = Locale("ko", "KR")  // 한국어 로케일 설정
        val dateFormat = SimpleDateFormat("M월 d일", locale)

        val calendar = Calendar.getInstance()

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
        val startDate = dateFormat.format(calendar.time)

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
        val endDate = dateFormat.format(calendar.time)

        return "$startDate ~ $endDate"
    }
}