package com.pascal.simplefinanceapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pascal.simplefinanceapp.R
import com.pascal.simplefinanceapp.model.income.DataIncome
import kotlinx.android.synthetic.main.item_list.view.*

class AdapterIncome(
    private val data: List<DataIncome?>?,
    private val itemClick: OnClickListener
) : RecyclerView.Adapter<AdapterIncome.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterIncome.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.bind(item)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: DataIncome?) {
            view.item_money.text = "Rp. ${item?.incomeMoney}"
            view.item_date.text = item?.incomeDate
            view.item_id.text = item?.incomeId

            view.setOnClickListener {
                itemClick.detail(item)
            }

            view.item_delete.setOnClickListener {
                itemClick.delete(item)
            }

            view.item_update.setOnClickListener {
                itemClick.update(item)
            }
        }
    }

    interface OnClickListener {
        fun detail(item: DataIncome?)
        fun update(item: DataIncome?)
        fun delete(item: DataIncome?)
    }
}