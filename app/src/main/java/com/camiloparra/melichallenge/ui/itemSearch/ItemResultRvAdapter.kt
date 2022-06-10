package com.camiloparra.melichallenge.ui.itemSearch

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.camiloparra.melichallenge.R
import com.squareup.picasso.Picasso
import com.camiloparra.melichallenge.domain.dto.api.ItemResult
import com.camiloparra.melichallenge.util.Utils

/**
 * Created by Camilo Parra on 7/06/2022.
 */
class ItemResultRvAdapter internal constructor(
    val context: Context,
    private var items: MutableList<ItemResult>,
    private var listener: OnClickListener
) :
    RecyclerView.Adapter<ItemResultRvAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val utils = Utils()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.rv_item_result, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cPosition = items[position]
        val nPrice = utils.setFormatPrice(cPosition.price)
        holder.tvTitle.text = cPosition.title
        holder.tvPrice.text = nPrice

        if(!cPosition.shipping.freeShipping) {
            holder.cvFreeShipping.visibility = View.GONE
        }

        Picasso.get().load(cPosition.thumbnail)
            .into(holder.ivThumbnail);
        holder.bind(cPosition, position, listener)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    internal fun setItems(nItems: MutableList<ItemResult>) {
        items = nItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView
        val tvPrice: TextView
        val ivThumbnail: ImageView
        val cvFreeShipping: CardView

        init {
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvPrice = itemView.findViewById(R.id.tv_price)
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail)
            cvFreeShipping = itemView.findViewById(R.id.cv_free_shipping)
        }

        fun bind(item: ItemResult, position: Int, listener: OnClickListener) {
            itemView.setOnClickListener { listener.onItemClick(item, position) }
        }
    }
}