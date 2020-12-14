package com.pascal.simplefinanceapp.view.home.expence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pascal.simplefinanceapp.R
import com.pascal.simplefinanceapp.model.category.DataCategory
import com.pascal.simplefinanceapp.model.expence.DataExpence
import com.pascal.simplefinanceapp.model.expence.ResponseExpence
import com.pascal.simplefinanceapp.view.adapter.AdapterExpencebyCategory
import com.pascal.simplefinanceapp.viewModel.ViewModelExpence
import kotlinx.android.synthetic.main.activity_category_expence.*

class CategoryExpenceActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModelExpence
    private var item: DataCategory? = null
    private var getId: String? = null
    private var getCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_expence)

        viewModel = ViewModelProviders.of(this).get(ViewModelExpence::class.java)

        attachObserve()
        getParcel()
    }

    private fun getParcel() {
        item = intent?.getParcelableExtra("data")
        getId = item?.categoryId
        getCategory = item?.categoryName

        title_expencecategory.setText("List Of $getCategory")

        if (item != null) {
            viewModel.getExpenceByCategory(getId!!)
        }
    }

    private fun attachObserve() {
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
        viewModel.isError.observe(this, Observer { showError(it) })
        viewModel.responseExpencebyCategory.observe(this, Observer { showCategoryView(it) })
    }

    private fun showCategoryView(it: ResponseExpence) {
        val adapter = AdapterExpencebyCategory(it?.data, object : AdapterExpencebyCategory.OnClickListener {
                override fun detail(item: DataExpence?) {
                    showToast("Expence ${item?.expenceMoney}")
                }
            })
        recycler_expenceCategory.adapter = adapter
    }

    private fun showError(it: Throwable?) {
        showToast(it.toString())
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {
            progress_expenceCategory.visibility = View.VISIBLE
        } else {
            progress_expenceCategory.visibility = View.GONE
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