package com.ismailmesutmujde.kotlindictionaryappfirebaserealtimedatabase.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ismailmesutmujde.kotlindictionaryappfirebaserealtimedatabase.R
import com.ismailmesutmujde.kotlindictionaryappfirebaserealtimedatabase.databinding.ActivityDetailScreenBinding
import com.ismailmesutmujde.kotlindictionaryappfirebaserealtimedatabase.model.Words

class DetailScreenActivity : AppCompatActivity() {

    private lateinit var bindingDetailScreen : ActivityDetailScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingDetailScreen = ActivityDetailScreenBinding.inflate(layoutInflater)
        val view = bindingDetailScreen.root
        setContentView(view)

        val word = intent.getSerializableExtra("obj") as Words

        bindingDetailScreen.textViewEnglish2.text = word.english
        bindingDetailScreen.textViewTurkish2.text = word.turkish

    }
}