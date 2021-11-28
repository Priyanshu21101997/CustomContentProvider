package com.example.customcontentprovider

import android.content.ContentValues
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rs = contentResolver.query(AcronymProvider.CONTENT_URI, arrayOf(AcronymProvider._ID,AcronymProvider.NAME,AcronymProvider.MEANING),null,null,null)

        val editTextName = findViewById<TextView>(R.id.Name)
        val editTextMeaning = findViewById<TextView>(R.id.Meaning)
        val btnNext = findViewById<Button>(R.id.button)
        val btnPrev = findViewById<Button>(R.id.button2)
        val btnInsert = findViewById<Button>(R.id.button3)
        val btnUpdate = findViewById<Button>(R.id.button4)
        val btnDelete = findViewById<Button>(R.id.button5)
        val btnClear = findViewById<Button>(R.id.button6)

        btnNext.setOnClickListener {
            if (rs?.moveToNext()!!) {
                editTextName.setText(rs.getString(1))
                editTextMeaning.setText(rs.getString(2))
            }
        }

        btnPrev.setOnClickListener {
            if (rs?.moveToPrevious()!!) {
                editTextName.setText(rs.getString(1))
                editTextMeaning.setText(rs.getString(2))
            }
        }

        btnInsert.setOnClickListener{
            var cv = ContentValues();
            cv.put(AcronymProvider.NAME,editTextName.text.toString())
            cv.put(AcronymProvider.MEANING,editTextMeaning.text.toString())
            contentResolver.insert(AcronymProvider.CONTENT_URI,cv)
            rs?.requery()

        }

        btnUpdate.setOnClickListener{
            var cv = ContentValues();
            cv.put(AcronymProvider.MEANING,editTextMeaning.text.toString())
            contentResolver.update(AcronymProvider.CONTENT_URI,cv,"NAME = ?",arrayOf(editTextName.text.toString()))
            rs?.requery()

        }

        btnDelete.setOnClickListener{
            contentResolver.delete(AcronymProvider.CONTENT_URI,"NAME = ?",arrayOf(editTextName.text.toString()))
            rs?.requery()

        }

        btnClear.setOnClickListener {
            editTextName.setText("")
            editTextMeaning.setText("")
            editTextName.requestFocus()
        }


    }
}