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
import com.pascal.simplefinanceapp.model.expence.DataExpence
import com.pascal.simplefinanceapp.model.expence.ResponseExpence
import com.pascal.simplefinanceapp.view.adapter.AdapterCategory
import com.pascal.simplefinanceapp.view.adapter.AdapterExpence
import com.pascal.simplefinanceapp.viewModel.ViewModelExpence
import kotlinx.android.synthetic.main.fragment_expences.*

class ExpencesFragment : Fragment() {

    private lateinit var viewModel: ViewModelExpence
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expences, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ViewModelExpence::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        initView()
        attachObserve()
        viewModel.getExpenceView()
        viewModel.getCategoryExpence()
    }

    private fun initView() {
        btn_expenceAdd.setOnClickListener {
            navController.navigate(R.id.action_expencesFragment_to_inputExpenceActivity)
        }

        recycler_expence.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !btn_expenceAdd.isShown()) {
                    btn_expenceAdd.show()
                }
                else if (dy > 0 && btn_expenceAdd.isShown()) {
                    btn_expenceAdd.hide()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun attachObserve() {
        viewModel.responseExpence.observe(viewLifecycleOwner, Observer {showExpenceView(it)})
        viewModel.isError.observe(viewLifecycleOwner, Observer { showError(it) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { showLoading(it) })
        viewModel.responseDelete.observe(viewLifecycleOwner, Observer { showDelete(it) })
        viewModel.responseCategory.observe(viewLifecycleOwner, Observer { showCategoryView(it) })

    }

    private fun showCategoryView(it: ResponseCategory?) {
        val adapter = AdapterCategory(it?.data, object : AdapterCategory.OnClickListener {
            override fun click(item: DataCategory?) {
                val bundle = bundleOf("data" to item)
                navController.navigate(R.id.action_expenceFragment_to_categoryExpenceActivity, bundle)
            }
        })

        recycler_categoryExpence.adapter = adapter
        recycler_categoryExpence.setLayoutManager(
            LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,
                false)
        )
    }

    private fun showExpenceView(it: ResponseExpence?) {
        val adapter = AdapterExpence(it?.data, object : AdapterExpence.OnClickListener {

            override fun detail(item: DataExpence?) {
                showToast("Money : ${item?.expenceMoney}")
            }

            override fun update(item: DataExpence?) {
                val bundle = bundleOf("data" to item)
                navController.navigate(R.id.action_expencesFragment_to_inputExpenceActivity, bundle)
            }

            override fun delete(item: DataExpence?) {
                context?.let { it1 ->
                    AlertDialog.Builder(it1).apply {
                        setTitle(R.string.titleDelete)
                        setMessage(R.string.yakin)
                        setPositiveButton(R.string.yes) { dialogInterface, i ->

                            viewModel.deleteExpenceView(item?.expenceId ?: "")
                            dialogInterface.dismiss()
                        }
                        setNegativeButton(R.string.cancel) { dialogInterface, i ->
                            dialogInterface.dismiss()
                        }
                    }.show()
                }
            }
        })
        recycler_expence.adapter = adapter
    }

    private fun showDelete(it: ResponseAction) {
        showToast("Delete Completed")
    }

    private fun showError(it: Throwable?) {
        showToast(it.toString())
    }

    private fun showLoading(it: Boolean?) {
        if (it == true) {
            progress_expence.visibility = View.VISIBLE
        } else {
            progress_expence.visibility = View.GONE
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getExpenceView()
    }
}