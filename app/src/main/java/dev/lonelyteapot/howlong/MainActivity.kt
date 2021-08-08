package dev.lonelyteapot.howlong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var tvAnswerDays: TextView
    lateinit var etDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tvAnswerDays = findViewById(R.id.answerDays)
        this.etDate = findViewById(R.id.editDate)

        this.etDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                updateDays()
            }

        })

        updateDays()
    }

    fun updateDays() {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")

        val dateStr = etDate.text.toString()
        val daysInt = try {
            val date = dateFormat.parse(dateStr)
            val today = Date()
            val elapsedMs = today.time - date.time
            val elapsedDays = elapsedMs / 1000 / 60 / 60 / 24
            elapsedDays
        } catch (exc: ParseException) {
            0
        }

        tvAnswerDays.text = daysInt.toString()
    }
}