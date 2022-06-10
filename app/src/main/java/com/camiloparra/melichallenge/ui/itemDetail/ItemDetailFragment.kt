package com.camiloparra.melichallenge.ui.itemDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.camiloparra.melichallenge.R
import com.camiloparra.melichallenge.databinding.FragmentItemDetailBinding
import com.squareup.picasso.Picasso

/**
 * View where you can show de item selected detail
 *
 * Created by Camilo Parra on 8/06/2022.
 */
class ItemDetailFragment : Fragment() {

    private val args: ItemDetailFragmentArgs by navArgs()
    private var initBinding: FragmentItemDetailBinding? = null
    private val binding get() = initBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        inflater.inflate(R.layout.fragment_item_detail, container, false)
        initBinding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataToView()
    }

    private fun setDataToView(){
        binding.tvTitle.text = args.detail.title
        binding.tvPrice.text = args.detail.price
        if(!args.detail.freeShipping) {
            binding.lyFreeShipping.visibility = View.GONE
        }
        Picasso.get().load(args.detail.thumbnail)
            .into(binding.ivThumbnail);
    }
}