package com.example.quiz2.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object DateUtils {

    fun formatDate(isoDate: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date = inputFormat.parse(isoDate) ?: return isoDate
            val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            outputFormat.format(date)
        } catch (e: Exception) {
            isoDate
        }
    }

    fun cleanContent(content: String?): String {
        return content
            ?.replace(Regex("\\[\\d+ chars\\]"), "")
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
            ?: "Full content not available. Tap 'Read Full Article' for the complete story."
    }
}
