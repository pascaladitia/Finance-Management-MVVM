package com.pascal.simplefinanceapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pascal.simplefinanceapp.R
import com.pascal.simplefinanceapp.model.category.DataCategory
import com.pascal.simplefinanceapp.model.income.DataIncome
import com.pascal.simplefinanceapp.model.income.ResponseIncome
import com.pascal.simplefinanceapp.view.adapter.AdapterIncomebyCategory
import com.pascal.simplefinanceapp.viewModel.ViewModelIncome
import kotlinx.android.synthetic.main.activity_income_category.*

class CategoryIncomeActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModelIncome
    private var item: DataCategory? = null
    private var getId: String? = null
    private var getCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_category)

        viewModel = ViewModelProviders.of(this).get(ViewModelIncome::class.java)

        attachObserve()
        getParcel()
    }

    private fun getParcel() {
        item = intent?.getParcelableExtra("data")
        getId = item?.categoryId
        getCategory = item?.categoryName

        title_category.setText("List Of $getCategory")

        if (item != null) {
            viewModel.getIncomeByCategory(getId!!)
        }
    }

    private fun attachObserve() {
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
        viewModel.isError.observe(this, Observer { showError(it) })
        viewModel.responseIncomebyCategory.observe(this, Observer { showCategoryView(it) })
    }

    private fun showCategoryView(it: ResponseIncome?) {
        val adapter = AdapterIncomebyCategory(it?.data, object : AdapterIncomebyCategory.OnClickListener {
            override fun detail(item: DataIncome?) {
                showToast("Income ${item?.incomeMoney}")
            }
        })
        recycler_detailCategory.adapter = adapter
    }

    private fun showError(it: Throwable?) {
        showToast(it.toString())
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {
            progress_detailCategory.visibility = View.VISIBLE
        } else {
            progress_detailCategory.visibility = View.GONE
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        attachObserve()
    }
}