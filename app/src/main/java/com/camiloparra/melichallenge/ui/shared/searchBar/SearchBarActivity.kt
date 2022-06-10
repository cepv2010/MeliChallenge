package com.camiloparra.melichallenge.ui.shared.searchBar

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.camiloparra.melichallenge.config.ConstArgs
import com.camiloparra.melichallenge.databinding.ActivitySearchBarBinding
import com.camiloparra.melichallenge.domain.dto.api.ItemResult
import com.camiloparra.melichallenge.domain.entity.Suggestion
import com.camiloparra.melichallenge.ui.itemSearch.ItemSearchActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchBarActivity : AppCompatActivity() {

    private val viewModel: SearchBarViewModel by viewModels()
    private lateinit var binding: ActivitySearchBarBinding
    private lateinit var rvAdapter: SuggestRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        handleTextListener()
        handleRv()
        observerSuggestionResult()
        if (savedInstanceState == null) getSuggestion()
        binding.ivBack.setOnClickListener { _ -> finish() }
        binding.etSearch.requestFocus()
        if (view.hasFocus()) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        }
    }


    private fun handleTextListener() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                rvAdapter.filterListItem(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    viewModel.insertSuggestion(binding.etSearch.text.toString())
                    startSearchResultActivity(binding.etSearch.text.toString())
                    true
                }
                else -> false
            }
        }
    }

    private fun handleRv() {
        rvAdapter = SuggestRvAdapter(this, listOf(), object : OnClickListener {
            override fun onItemClick(query: String, position: Int) {
                startSearchResultActivity(query)
            }
        })
        binding.rvResult.adapter = rvAdapter
        binding.rvResult.layoutManager = LinearLayoutManager(this)
    }

    private fun getSuggestion() {
        viewModel.getSuggestion()
    }

    private fun observerSuggestionResult() {
        viewModel.suggestionResult.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                rvAdapter.setItems(it)
            }
        })
    }

    private fun startSearchResultActivity(query: String) {
        val i = Intent(this@SearchBarActivity, ItemSearchActivity::class.java)
        val b = Bundle()
        b.putString(ConstArgs.QUERY, query)
        i.putExtras(b)
        startActivity(i)
        finish()
    }
}