package com.pascal.simplefinanceapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pascal.simplefinanceapp.R
import com.pascal.simplefinanceapp.model.income.DataIncome
import kotlinx.android.synthetic.main.item_databycategory.view.*
import kotlinx.android.synthetic.main.item_list.view.*
import kotlinx.android.synthetic.main.item_list.view.item_delete
import kotlinx.android.synthetic.main.item_list.view.item_update

class AdapterIncomebyCategory(
    private val data: List<DataIncome?>?,
    private val itemClick: OnClickListener
) : RecyclerView.Adapter<AdapterIncomebyCategory.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterIncomebyCategory.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_databycategory, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.bind(item)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: DataIncome?) {
            view.item_databymoney.text = "Rp. ${item?.incomeMoney}"
            view.item_databydate.text = item?.incomeDate
            view.item_databyid.text = item?.incomeId

            view.setOnClickListener {
                itemClick.detail(item)
            }
        }
    }

    interface OnClickListener {
        fun detail(item: DataIncome?)
    }
}