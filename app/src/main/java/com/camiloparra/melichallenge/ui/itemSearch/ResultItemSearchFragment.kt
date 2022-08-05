package com.camiloparra.melichallenge.ui.itemSearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.camiloparra.melichallenge.R
import com.camiloparra.melichallenge.config.ConstArgs
import com.camiloparra.melichallenge.databinding.FragmentResultItemSearchBinding
import com.camiloparra.melichallenge.data.network.dto.ItemResultDto
import com.camiloparra.melichallenge.domain.model.ItemResult
import com.camiloparra.melichallenge.util.Utils
import dagger.hilt.android.AndroidEntryPoint

/**
 * View where yo can show the list resulting from the query search
 *
 * Created by Camilo Parra on 6/06/2022.
 */
@AndroidEntryPoint
class ResultItemSearchFragment : Fragment() {

    private val util = Utils()
    private val viewModel: ResultItemSearchViewModel by viewModels()

    private var initBinding: FragmentResultItemSearchBinding? = null
    private val binding get() = initBinding!!
    private lateinit var rvAdapter: ItemResultRvAdapter
    private var count: Int = 0
    private var query: String? = null
    private var incNotConnListener = View.OnClickListener {
        binding.rlLoading.visibility = View.VISIBLE
        binding.incNotConn.lyNotConn.visibility = View.GONE
        binding.nvItemResult.visibility = View.GONE
        viewModel.enableSearch()
        getSearchItemResult()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflater.inflate(R.layout.fragment_result_item_search, container, false)
        initBinding = FragmentResultItemSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        query = activity?.intent?.extras?.getString(ConstArgs.QUERY)
        initRv()
        handleScrollRv()
        observerItemResult()
        observerConn()
        observerNotFound()
        binding.incNotConn.tvTryAgain.setOnClickListener(incNotConnListener)
        if (savedInstanceState == null) {
            getSearchItemResult()
        }
    }

    private fun getSearchItemResult() {
        if (viewModel.isEnableSearch())
            viewModel.getSearch(query!!)
    }

    private fun observerItemResult() {
        viewModel.itemResult.observe(viewLifecycleOwner, Observer {
            binding.rlLoading.visibility = View.GONE
            binding.incNotConn.lyNotConn.visibility = View.GONE
            binding.incNotFound.lyNotFound.visibility = View.GONE
            binding.nvItemResult.visibility = View.VISIBLE
            rvAdapter.setItems(it)
        })
    }

    private fun observerConn() {
        viewModel.notConn.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.rlLoading.visibility = View.GONE
                binding.nvItemResult.visibility = View.GONE
                binding.incNotFound.lyNotFound.visibility = View.GONE
                binding.incNotConn.lyNotConn.visibility = View.VISIBLE
            }
        })
    }

    private fun observerNotFound() {
        viewModel.notFound.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.rlLoading.visibility = View.GONE
                binding.nvItemResult.visibility = View.GONE
                binding.incNotConn.lyNotConn.visibility = View.GONE
                binding.incNotFound.lyNotFound.visibility = View.VISIBLE
            }
        })
    }

    private fun initRv() {
        rvAdapter = ItemResultRvAdapter(requireContext(), mutableListOf(), object : OnClickListener {
            override fun onItemClick(item: ItemResult, position: Int) {
                val args = item.transformToArgs()
                val action = ResultItemSearchFragmentDirections
                    .actionResultItemSearchFragmentToItemDetailFragment(args)
                requireView().findNavController().navigate(action)
            }

        })
        binding.rvResult.adapter = rvAdapter
        binding.rvResult.layoutManager = LinearLayoutManager(context)
    }

    private fun handleScrollRv() {
        binding.nvItemResult.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, _ ->
            run {
                val height = v.getChildAt(0).measuredHeight - v.measuredHeight
                if (scrollY == height) {
                    count++
                    if (count < ConstArgs.MAX_SECTION) {
                        viewModel.enableSearch()
                        getSearchItemResult()
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.disableSearch()
    }

}