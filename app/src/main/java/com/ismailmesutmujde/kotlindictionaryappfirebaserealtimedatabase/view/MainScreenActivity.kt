package com.ismailmesutmujde.kotlindictionaryappfirebaserealtimedatabase.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ismailmesutmujde.kotlindictionaryappfirebaserealtimedatabase.R
import com.ismailmesutmujde.kotlindictionaryappfirebaserealtimedatabase.adapter.WordsRecyclerViewAdapter
import com.ismailmesutmujde.kotlindictionaryappfirebaserealtimedatabase.databinding.ActivityMainScreenBinding
import com.ismailmesutmujde.kotlindictionaryappfirebaserealtimedatabase.model.Words


class MainScreenActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var bindingMainScreen : ActivityMainScreenBinding
    private lateinit var wordsList: ArrayList<Words>
    private lateinit var adapter: WordsRecyclerViewAdapter
    private lateinit var refWords: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainScreen = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = bindingMainScreen.root
        setContentView(view)

        bindingMainScreen.toolbar.title = "Dictionary Application"
        setSupportActionBar(bindingMainScreen.toolbar)

        bindingMainScreen.recyclerView.setHasFixedSize(true)
        bindingMainScreen.recyclerView.layoutManager = LinearLayoutManager(this)

        val db = FirebaseDatabase.getInstance()
        refWords = db.getReference("words")

        wordsList = ArrayList()

        /*
        val w1 = Words(1, "Dog","Köpek")
        val w2 = Words(2, "Fish","Balık")
        val w3 = Words(3, "Table","Masa")

        wordsList.add(w1)
        wordsList.add(w2)
        wordsList.add(w3)*/

        adapter = WordsRecyclerViewAdapter(this, wordsList)
        bindingMainScreen.recyclerView.adapter = adapter

        allWords()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        searchWord(query)
        Log.e("Sent Search", query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        searchWord(newText)
        Log.e("As letters are entered", newText)
        return true
    }

    fun allWords() {
        refWords.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                wordsList.clear()

                for (c in snapshot.children) {
                    val word = c.getValue(Words::class.java)
                    if (word != null) {
                        word.word_id = c.key
                        wordsList.add(word)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun searchWord(searchingWord:String) {
        refWords.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                wordsList.clear()

                for (c in snapshot.children) {
                    val word = c.getValue(Words::class.java)
                    if (word != null) {

                        if(word.english!!.contains(searchingWord)) {
                            word.word_id = c.key
                            wordsList.add(word)
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}