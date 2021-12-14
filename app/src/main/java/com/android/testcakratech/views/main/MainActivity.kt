package com.android.testcakratech.views.main

import android.os.Bundle
import android.util.Log
import com.android.testcakratech.common.activity.BaseActivity
import com.android.testcakratech.common.navigator.ScreenNavigator

class MainActivity : BaseActivity(), MainMvcView.Listener {

    private lateinit var viewMvc: MainMvcView
    private val dataLoding = false
    private lateinit var screenNavigator: ScreenNavigator
    private lateinit var mainUseCase: MainUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = compositionRoot.viewMvcFactory.newMainActivity()
        setContentView(viewMvc.binding.root)

        //main use case
        mainUseCase = compositionRoot.mainUseCase

        //screen nav
        screenNavigator = compositionRoot.screenNavigator

    }

    private fun getData() {
        viewMvc.showProgressBar()
        mainUseCase.fetchData({
            viewMvc.hideProgressBar()
            viewMvc.bindData(it)
            Log.i("main",it.toString())
        }, {
            viewMvc.hideProgressBar()
            Log.e("main", it.message ?: "")
        })
    }

    override fun onRefreshClicked() {
        getData()
    }

    override fun onToFormActivity() {
        screenNavigator.toFormActivity()
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        if (!dataLoding) {
            getData()
        }
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unRegisterListener(this)
    }
}