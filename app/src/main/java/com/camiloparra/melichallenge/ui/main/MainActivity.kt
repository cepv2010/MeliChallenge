package com.camiloparra.melichallenge.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.NavHostFragment
import com.camiloparra.melichallenge.R
import com.camiloparra.melichallenge.databinding.ActivityMainBinding
import com.camiloparra.melichallenge.ui.shared.searchBar.SearchBarActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        handleActionSearch()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    private fun handleActionSearch() {
        val toolbar = findViewById<Toolbar>(R.id.app_toolbar)
        val cvSearch = toolbar.findViewById<CardView>(R.id.cv_search)
        cvSearch.setOnClickListener { _ -> run{
            startActivity(Intent(this@MainActivity, SearchBarActivity::class.java))
        } }
    }

}