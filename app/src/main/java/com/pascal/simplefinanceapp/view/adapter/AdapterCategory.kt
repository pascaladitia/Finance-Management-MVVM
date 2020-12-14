package com.pascal.simplefinanceapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pascal.simplefinanceapp.R
import com.pascal.simplefinanceapp.model.category.DataCategory
import kotlinx.android.synthetic.main.item_category.view.*

class AdapterCategory(
    private val data: List<DataCategory?>?,
    private val itemClick: OnClickListener
) : RecyclerView.Adapter<AdapterCategory.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterCategory.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: AdapterCategory.ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.bind(item)
    }

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: DataCategory?) {

        itemView.itemCategory_name.text = item?.categoryName

            view.setOnClickListener {
                itemClick.click(item)
            }
        }
    }

    interface OnClickListener {
        fun click(item: DataCategory?)
    }
}