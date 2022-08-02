package com.camiloparra.melichallenge.ui.shared.searchBar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.camiloparra.melichallenge.R
import com.camiloparra.melichallenge.data.local.entity.Suggestion

/**
 * Created by Camilo Parra on 10/06/2022.
 */
class SuggestRvAdapter constructor(
    val context: Context,
    private var items: List<Suggestion>,
    private var listener: OnClickListener
) :
    RecyclerView.Adapter<SuggestRvAdapter.ViewHolder>() {

    private var nItems = mutableListOf<Suggestion>()
    init {
        nItems.addAll(items)
    }
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.rv_item_suggestion, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cPosition = nItems[position]
        holder.tvSuggestion.text = cPosition.suggest
        holder.bind(cPosition.suggest, position, listener)
    }

    override fun getItemCount() = nItems.size

    internal fun setItems(items: List<Suggestion>) {
        this.items = items
        nItems.addAll(items)
        notifyDataSetChanged()
    }

    fun filterListItem(query: String) {
        nItems.clear()
        val listFilter: List<Suggestion>
        when {
            query.isEmpty() -> nItems.addAll(items)
            else -> {
                listFilter = items.filter { item -> item.suggest.contains(query) }
                if (listFilter.isNotEmpty()) nItems.addAll(listFilter)
                if (listFilter.isEmpty()) nItems.addAll(items)

            }
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSuggestion: TextView

        init {
            tvSuggestion = itemView.findViewById(R.id.tv_suggestion)
        }

        fun bind(query: String, position: Int, listener: OnClickListener) {
            itemView.setOnClickListener { listener.onItemClick(query, position) }
        }
    }

}