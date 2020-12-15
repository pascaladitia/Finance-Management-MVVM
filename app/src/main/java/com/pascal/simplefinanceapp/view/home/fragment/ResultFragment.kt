package com.pascal.simplefinanceapp.view.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.pascal.simplefinanceapp.R
import com.pascal.simplefinanceapp.model.expence.ResponseExpence
import com.pascal.simplefinanceapp.model.income.ResponseIncome
import com.pascal.simplefinanceapp.viewModel.ViewModelExpence
import com.pascal.simplefinanceapp.viewModel.ViewModelIncome
import kotlinx.android.synthetic.main.fragment_result.*


class ResultFragment : Fragment() {

    private lateinit var viewModelIncome: ViewModelIncome
    private lateinit var viewModelExpence: ViewModelExpence
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelIncome = ViewModelProviders.of(this).get(ViewModelIncome::class.java)
        viewModelExpence = ViewModelProviders.of(this).get(ViewModelExpence::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        attachObserve()
        viewModelIncome.getIncomeView()
        viewModelExpence.getExpenceView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun attachObserve() {
        viewModelIncome.responseIncome.observe(viewLifecycleOwner, Observer {showIncomeView(it)})
       // viewModelExpence.responseExpence.observe(viewLifecycleOwner, Observer {showExpenceView(it)})
        viewModelIncome.isError.observe(viewLifecycleOwner, Observer { showError(it) })
    }

    private fun showIncomeView(it: ResponseIncome?) {
        val item = it?.data

        var income = 0
        for (num in item!!) {
            income = income!! + num?.incomeMoney!!.toInt()
        }

        viewModelExpence.responseExpence.observe(viewLifecycleOwner, Observer {
            val item = it?.data
            var expence = 0
            for (num in item!!) {
                expence = expence!! + num?.expenceMoney!!.toInt()
            }

            resultFinance(income, expence)
        })
    }

    private fun resultFinance(income: Int, expence: Int) {
        val pie = AnyChart.pie()

        pie.setOnClickListener(object :
            ListenersInterface.OnClickListener(arrayOf("x", "value")) {
            override fun onClick(event: Event) {
                Toast.makeText(
                    context,
                    event.getData().get("x").toString() + ":" + event.getData().get("value"),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Income", income))
        data.add(ValueDataEntry("Expence", expence))

        pie.data(data)
        pie.title("Total Earning in Chart")
        pie.labels().position("money management")

        pie.legend().title().enabled(true)
        pie.legend().title()
            .text("Finance App")
            .padding(0.0, 0.0, 10.0, 0.0)

        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)

        any_chart_view.setChart(pie)

        val resultData = income - expence
        result.setText("Total Earning : Rp.${resultData.toString()}")
    }

    private fun showError(it: Throwable?) {
        showToast(it.toString())
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        viewModelIncome.getIncomeView()
        viewModelExpence.getExpenceView()
    }
}