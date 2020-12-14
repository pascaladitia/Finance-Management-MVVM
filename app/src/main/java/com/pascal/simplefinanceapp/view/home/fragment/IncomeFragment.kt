package com.pascal.simplefinanceapp.view.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pascal.simplefinanceapp.R
import com.pascal.simplefinanceapp.model.action.ResponseAction
import com.pascal.simplefinanceapp.model.category.DataCategory
import com.pascal.simplefinanceapp.model.category.ResponseCategory
import com.pascal.simplefinanceapp.model.income.DataIncome
import com.pascal.simplefinanceapp.model.income.ResponseIncome
import com.pascal.simplefinanceapp.view.adapter.AdapterCategory
import com.pascal.simplefinanceapp.view.adapter.AdapterIncome
import com.pascal.simplefinanceapp.viewModel.ViewModelIncome
import kotlinx.android.synthetic.main.fragment_income.*

class IncomeFragment : Fragment() {

    private lateinit var viewModel: ViewModelIncome
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelIncome::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initView()
        attachObserve()
        viewModel.getIncomeView()
        viewModel.getCategoryIncome()
    }

    private fun initView() {
        btn_incomeAdd.setOnClickListener {
            navController.navigate(R.id.action_incomeFragment_to_inputIncomeActivity)
        }

        recycler_income.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !btn_incomeAdd.isShown()) {
                    btn_incomeAdd.show()
                }
                else if (dy > 0 && btn_incomeAdd.isShown()) {
                    btn_incomeAdd.hide()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun attachObserve() {
        viewModel.responseIncome.observe(viewLifecycleOwner, Observer {showIncomeView(it)})
        viewModel.isError.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { showLoading(it) })
        viewModel.responseDelete.observe(viewLifecycleOwner, Observer { showDelete(it) })
        viewModel.responseCategory.observe(viewLifecycleOwner, Observer { showCategoryView(it) })
    }

    private fun showCategoryView(it: ResponseCategory?) {
        val adapter = AdapterCategory(it?.data, object : AdapterCategory.OnClickListener {
            override fun click(item: DataCategory?) {
                val bundle = bundleOf("data" to item)
                navController.navigate(R.id.action_incomeFragment_to_categoryIncomeActivity, bundle)
            }
        })

        recycler_categoryIncome.adapter = adapter
        recycler_categoryIncome.setLayoutManager(
            LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,
                false)
        )
    }

    private fun showIncomeView(it: ResponseIncome?) {
        val adapter = AdapterIncome(it?.data, object : AdapterIncome.OnClickListener {

            override fun detail(item: DataIncome?) {
               showToast("Money : ${item?.incomeMoney}")
            }

            override fun update(item: DataIncome?) {
                val bundle = bundleOf("data" to item)
                navController.navigate(R.id.action_incomeFragment_to_inputIncomeActivity, bundle)
            }

            override fun delete(item: DataIncome?) {
                context?.let { it1 ->
                    AlertDialog.Builder(it1).apply {
                        setTitle(R.string.titleDelete)
                        setMessage(R.string.yakin)
                        setPositiveButton(R.string.yes) { dialogInterface, i ->

                            viewModel.deleteIncomeView(item?.incomeId ?: "")
                            dialogInterface.dismiss()
                        }
                        setNegativeButton(R.string.cancel) { dialogInterface, i ->
                            dialogInterface.dismiss()
                        }
                    }.show()
                }
            }
        })
        recycler_income.adapter = adapter
    }

    private fun showDelete(it: ResponseAction) {
        showToast("Delete Completed")
    }

    private fun showError(it: Throwable?) {
        showToast(it.toString())
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {
            progress_income.visibility = View.VISIBLE
        } else {
            progress_income.visibility = View.GONE
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getIncomeView()
        viewModel.getCategoryIncome()
    }
}