package com.android.testcakratech.view.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.testcakratech.R
import com.android.testcakratech.databinding.ActivityMainBinding
import com.android.testcakratech.db.Form
import com.android.testcakratech.db.FormDao
import com.android.testcakratech.db.FormDatabase
import com.android.testcakratech.view.common.navigator.ScreenNavigator
import com.android.testcakratech.view.screens.adapter.DataAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var dao: FormDao
    private lateinit var binding: ActivityMainBinding
    private var dataLoding = false
    private lateinit var dataAdapter: DataAdapter
    private lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //screen navigator
        screenNavigator = ScreenNavigator(this)

        //room
        dao = FormDatabase.getInstance(applicationContext).formDao()

        //recycler view
        binding.rvData.layoutManager = LinearLayoutManager(this)
        dataAdapter = DataAdapter()
        binding.rvData.adapter = dataAdapter

        //swipe refresh
        binding.swipeRefresh.setOnRefreshListener {
            getData()
        }

        binding.fabBack.setOnClickListener {
            screenNavigator.toFormActivity()
        }

    }

    private fun getData() {
        showProgressBar()
        dao.getAllForm()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                hideProgressBar()
                bindData(it)
            }, {
                hideProgressBar()
            })
    }

    private fun bindData(it: List<Form>?) {
        dataAdapter.setList(it)
        dataAdapter.notifyDataSetChanged()
    }

    private fun hideProgressBar() {
        dataLoding = false
        if (binding.swipeRefresh.isRefreshing) {
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun showProgressBar() {
        dataLoding = true
        binding.swipeRefresh.isRefreshing = true
    }

    override fun onStart() {
        super.onStart()
        if (!dataLoding) {
            getData()
        }
    }
}