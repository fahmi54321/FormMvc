package com.android.testcakratech.view.screens.main

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.testcakratech.databinding.ActivityMainBinding
import com.android.testcakratech.db.Form
import com.android.testcakratech.view.common.navigator.ScreenNavigator
import com.android.testcakratech.view.screens.adapter.DataAdapter
import com.android.testcakratech.view.screens.common.BaseViewMvc

class MainMvcView(
    private val layoutInflater: LayoutInflater
) : BaseViewMvc<MainMvcView.Listener, ActivityMainBinding>(
    layoutInflater
) {

    interface Listener {
        fun onRefreshClicked()
        fun onToFormActivity()
    }

    private var dataAdapter: DataAdapter
    private var dataLoding = false

    init {

        //recycler view
        binding.rvData.layoutManager = LinearLayoutManager(context)
        dataAdapter = DataAdapter()
        binding.rvData.adapter = dataAdapter

        //swipe refresh
        binding.swipeRefresh.setOnRefreshListener {
            for (listener in listeners) {
                listener.onRefreshClicked()
            }
        }

        binding.fabNext.setOnClickListener {
            for (lisneter in listeners) {
                lisneter.onToFormActivity()
            }
        }
    }

    fun hideProgressBar() {
        dataLoding = false
        if (binding.swipeRefresh.isRefreshing) {
            binding.swipeRefresh.isRefreshing = false
        }
    }

    fun showProgressBar() {
        dataLoding = true
        binding.swipeRefresh.isRefreshing = true
    }

    fun bindData(it: List<Form>?) {
        dataAdapter.setList(it)
        dataAdapter.notifyDataSetChanged()
    }

    override val bind: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

}