package com.pascal.simplefinanceapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pascal.simplefinanceapp.R
import com.pascal.simplefinanceapp.model.action.ResponseAction
import com.pascal.simplefinanceapp.model.income.DataIncome
import com.pascal.simplefinanceapp.viewModel.ViewModelIncome
import kotlinx.android.synthetic.main.activity_input_income.*

class InputIncomeActivity : AppCompatActivity() {

    private lateinit var viewModel : ViewModelIncome
    private var item: DataIncome? = null
    private var getDate: String? = null
    private var getMoney: String? = null
    private var getCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_income)

        viewModel = ViewModelProviders.of(this).get(ViewModelIncome::class.java)
        getParcel()
        attachObserve()
        initView()
    }

    private fun initView() {
        btn_inputIncomeCancel.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getParcel() {
        item = intent.getParcelableExtra("data")
        getDate = item?.incomeDate
        getMoney = item?.incomeMoney
        getCategory = item?.categoryId.toString()

        if (item != null) {
            inputIncome_date.setText(getDate)
            inputIncome_money.setText(getMoney)
            inputIncome_category.setText(getCategory)

            btn_inputIncomeSave.text = "Update"
        }

        when (btn_inputIncomeSave.text) {
            "Update" -> {
                btn_inputIncomeSave.setOnClickListener { item?.incomeId?.let { it1 ->
                    viewModel.updateIncomeView(it1,
                        inputIncome_date.text.toString(),
                        inputIncome_money.text.toString(),
                        inputIncome_category.text.toString()) }
                }
            }
            else -> {
                btn_inputIncomeSave.setOnClickListener { viewModel.inputIncomeView(
                    inputIncome_date.text.toString(),
                    inputIncome_money.text.toString(),
                    inputIncome_category.text.toString()
                ) }
            }
        }

    }

    private fun attachObserve() {
        viewModel.responseInput.observe(this, Observer { inputData(it) })
        viewModel.responseUpdate.observe(this, Observer { updateData(it) })
        viewModel.isError.observe(this, Observer { showError(it) })
    }

    private fun showError(it: Throwable?) {
        showToast(it?.message.toString())
    }

    private fun updateData(it: ResponseAction?) {
        showToast("Update Completed")
        finish()
    }

    private fun inputData(it: ResponseAction?) {
        showToast("Input Completed")
        finish()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}