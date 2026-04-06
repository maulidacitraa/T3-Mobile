package com.example.tugasmobile3

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNama = findViewById<EditText>(R.id.etNama)
        val rgGender = findViewById<RadioGroup>(R.id.rgGender)

        val cbMembaca = findViewById<CheckBox>(R.id.cbMembaca)
        val cbCoding = findViewById<CheckBox>(R.id.cbCoding)
        val cbOlahraga = findViewById<CheckBox>(R.id.cbOlahraga)

        val btnTampil = findViewById<Button>(R.id.btnTampil)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        btnTampil.setOnClickListener {

            val nama = etNama.text.toString()

            // VALIDASI
            if (nama.isEmpty()) {
                etNama.error = "Nama tidak boleh kosong"
                Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // GENDER
            val gender = when (rgGender.checkedRadioButtonId) {
                R.id.rbLaki -> "Laki-laki"
                R.id.rbPerempuan -> "Perempuan"
                else -> "Belum dipilih"
            }

            // HOBI
            val hobiList = mutableListOf<String>()
            if (cbMembaca.isChecked) hobiList.add("Membaca")
            if (cbCoding.isChecked) hobiList.add("Coding")
            if (cbOlahraga.isChecked) hobiList.add("Olahraga")

            val hobi = if (hobiList.isEmpty()) "-" else hobiList.joinToString(", ")

            val resultText = "Nama : $nama\nKelamin : $gender\nHobi : $hobi"

            val spannable = SpannableString(resultText)

// Bold hanya labelnya
            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                resultText.indexOf("Nama"),
                resultText.indexOf(":") + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                resultText.indexOf("Kelamin"),
                resultText.indexOf(":", resultText.indexOf("Kelamin")) + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                resultText.indexOf("Hobi"),
                resultText.indexOf(":", resultText.indexOf("Hobi")) + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            tvHasil.text = spannable
        }
    }
}