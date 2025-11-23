package com.example.noponto

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.Calendar

object Mask {
    fun mask(format: String, editText: EditText): TextWatcher {
        return object : TextWatcher {
            var isUpdating = false
            var old = ""

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val str = s.toString().replace("[^\\d]".toRegex(), "")
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }

                var i = 0
                for (m in format.toCharArray()) {
                    if (m != '#' && str.length > old.length) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }

                // Date and Time validation
                var clean = mascara.replace("[^\\d]".toRegex(), "")

                if (format == "##/##/####") {
                    val calendar = Calendar.getInstance()
                    val currentYear = calendar.get(Calendar.YEAR)

                    if (clean.length >= 2) {
                        val day = clean.substring(0, 2).toIntOrNull()
                        if (day != null && day > 31) {
                            clean = "31" + if (clean.length > 2) clean.substring(2) else ""
                        }
                    }
                    if (clean.length >= 4) {
                        val month = clean.substring(2, 4).toIntOrNull()
                        if (month != null && month > 12) {
                            clean = clean.substring(0, 2) + "12" + if (clean.length > 4) clean.substring(4) else ""
                        }
                    }
                    if (clean.length == 8) {
                        val year = clean.substring(4, 8).toIntOrNull()
                        if (year != null && year > currentYear) {
                            clean = clean.substring(0, 4) + currentYear.toString()
                        }
                    }
                    val len = clean.length
                    var newText = ""
                    if (len > 0) newText = clean.substring(0, Math.min(2, len))
                    if (len > 2) newText += "/" + clean.substring(2, Math.min(4, len))
                    if (len > 4) newText += "/" + clean.substring(4, Math.min(8, len))
                    mascara = newText
                }

                if (format == "##:##") {
                    if (clean.length >= 2) {
                        val hours = clean.substring(0, 2).toIntOrNull()
                        if (hours != null && hours > 23) {
                            clean = "23" + if (clean.length > 2) clean.substring(2) else ""
                        }
                    }
                    if (clean.length >= 4) {
                        val minutes = clean.substring(2, 4).toIntOrNull()
                        if (minutes != null && minutes > 59) {
                            clean = clean.substring(0, 2) + "59"
                        }
                    }
                    val len = clean.length
                    var newText = ""
                    if (len > 0) newText = clean.substring(0, Math.min(2, len))
                    if (len > 2) newText += ":" + clean.substring(2, Math.min(4, len))
                    mascara = newText
                }

                isUpdating = true
                editText.setText(mascara)
                editText.setSelection(mascara.length)
            }

            override fun afterTextChanged(s: Editable) {}
        }
    }
}