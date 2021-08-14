package dev.lonelyteapot.howlong

import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit

class MainActivity : AppCompatActivity() {
    lateinit var textAnswer: TextView
    lateinit var textDate: TextView
    lateinit var datePickerBottom: DatePicker
    var selectedDate = LocalDate.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.textAnswer = findViewById(R.id.textAnswer)
        this.textDate = findViewById(R.id.textDate)
        this.datePickerBottom = findViewById(R.id.datePickerBottom)

        datePickerBottom.setOnDateChangedListener { view, year, month, day ->
            // DatePicker counts months from 0 for some reason
            selectedDate = LocalDate.of(year, month+1, day)
            updateDaysBetween()
        }

        updateDaysBetween()
    }

    fun updateDaysBetween() {
        val today = LocalDate.now()
        val daysBetween = ChronoUnit.DAYS.between(selectedDate, today)
        textDate.text = selectedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
        textAnswer.text = daysBetween.toString()
    }
}