package com.pascal.simplefinanceapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pascal.simplefinanceapp.R
import com.pascal.simplefinanceapp.model.action.ResponseAction
import com.pascal.simplefinanceapp.model.expence.DataExpence
import com.pascal.simplefinanceapp.viewModel.ViewModelExpence
import kotlinx.android.synthetic.main.activity_input_expence.*

class InputExpenceActivity : AppCompatActivity() {
    
    private lateinit var viewModel : ViewModelExpence
    private var item: DataExpence? = null
    private var getDate: String? = null
    private var getMoney: String? = null
    private var getCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_expence)

        viewModel = ViewModelProviders.of(this).get(ViewModelExpence::class.java)
        getParcel()
        attachObserve()
        initView()
    }

    private fun initView() {
        btn_inputExpenceCancel.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getParcel() {
        item = intent.getParcelableExtra("data")
        getDate = item?.expenceDate
        getMoney = item?.expenceMoney
        getCategory = item?.categoryId

        if (item != null) {
            inputExpence_date.setText(getDate)
            inputExpence_money.setText(getMoney)
            inputExpence_category.setText(getCategory)

            btn_inputExpenceSave.text = "Update"
        }

        when (btn_inputExpenceSave.text) {
            "Update" -> {
                btn_inputExpenceSave.setOnClickListener {
                    item?.expenceId?.let { it1 ->
                        viewModel.updateExpenceView(
                            it1,
                            inputExpence_date.text.toString(),
                            inputExpence_money.text.toString(),
                            inputExpence_category.text.toString()
                        )
                    }
                }
            }
            else -> {
                btn_inputExpenceSave.setOnClickListener {
                    viewModel.inputExpenceView(
                        inputExpence_date.text.toString(),
                        inputExpence_money.text.toString(),
                        inputExpence_category.text.toString()
                    )
                }
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