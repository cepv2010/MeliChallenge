package com.camiloparra.melichallenge.ui.itemSearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.NavHostFragment
import com.camiloparra.melichallenge.R
import com.camiloparra.melichallenge.config.ConstArgs
import com.camiloparra.melichallenge.ui.shared.searchBar.SearchBarActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity where you can show all views relate resulting from the query search
 *
 * Created by Camilo Parra on 6/06/2022.
 */
@AndroidEntryPoint
class ItemSearchActivity : AppCompatActivity() {

    private var query: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_search)
        query = intent.getStringExtra(ConstArgs.QUERY)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_search) as NavHostFragment
        navHostFragment.navController
        handleActionSearch()
    }

    private fun handleActionSearch() {
        val toolbar = findViewById<Toolbar>(R.id.app_toolbar)
        val cvSearch = toolbar.findViewById<CardView>(R.id.cv_search)
        val tvQuery = toolbar.findViewById<TextView>(R.id.tv_query_search)
        tvQuery.text = query
        cvSearch.setOnClickListener { _ ->
            run {
                startActivity(Intent(this@ItemSearchActivity, SearchBarActivity::class.java))
            }
        }
    }
}