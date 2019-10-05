package com.baianat.floadingcellanimationsample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baianat.floadingcellanimationsample.R
import com.baianat.floadingcellanimationsample.model.Item
import com.ramotion.foldingcell.FoldingCell
import kotlinx.android.synthetic.main.cell_content_layout.view.*
import kotlinx.android.synthetic.main.cell_title_layout.view.*
import java.util.*


class FloadingAdapter(var items: MutableList<Item>, var listener: EventClick) :
    RecyclerView.Adapter<FloadingVH>() {
    private val unfoldedIndexes = HashSet<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FloadingVH {
        return FloadingVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cell, null)
        )
    }

    override fun onBindViewHolder(holder: FloadingVH, position: Int) {
        // if cell is exists - reuse it, if not - create the new one from resource
        var cell: FoldingCell? = holder.itemView as FoldingCell
        with(holder.itemView) {

            head_image_left_text.text = items[position].requestsCount.toString()
            head_image_center_text.text = items[position].pledgePrice
            content_delivery_time.text = items[position].time
            content_delivery_date.text = items[position].date
            content_from_address_2.text = items[position].fromAddress
            content_to_address_2.text = items[position].toAddress
            priceContentTV.text = items[position].price

            title_price.text = items[position].price
            title_requests_count.text = items[position].requestsCount.toString()
            title_pledge.text = items[position].pledgePrice
            title_time_label.text = items[position].time
            title_date_label.text = items[position].date
            title_from_address.text = items[position].fromAddress
            title_to_address.text = items[position].toAddress

            setOnClickListener {
                listener.onEventClickListener(items[position], position, holder.itemView)
            }
            content_request_btn.setOnClickListener {
                listener.onRequestItemClickListener(items[position], position)

            }
        }
    }

    // simple methods for register cell state changes
    fun registerToggle(position: Int) {
        if (unfoldedIndexes.contains(position))
            registerFold(position)
        else
            registerUnfold(position)
    }

    fun registerFold(position: Int) {
        unfoldedIndexes.remove(position)
    }

    fun registerUnfold(position: Int) {
        unfoldedIndexes.add(position)
    }

    interface EventClick {
        fun onEventClickListener(event: Item, position: Int, view: View)
        fun onRequestItemClickListener(event: Item, position: Int)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


