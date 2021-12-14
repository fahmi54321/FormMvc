package com.android.testcakratech.view.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.android.testcakratech.db.FormDao
import com.android.testcakratech.db.FormDatabase
import com.android.testcakratech.view.common.navigator.ScreenNavigator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity(), MainMvcView.Listener {

    private lateinit var dao: FormDao
    private lateinit var viewMvc: MainMvcView
    private val dataLoding = false
    private lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvc = MainMvcView(LayoutInflater.from(this))
        setContentView(viewMvc.binding.root)

        //room
        dao = FormDatabase.getInstance(applicationContext).formDao()

        //screen nav
        screenNavigator = ScreenNavigator(this)

    }

    private fun getData() {
        viewMvc.showProgressBar()
        dao.getAllForm()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewMvc.hideProgressBar()
                viewMvc.bindData(it)
            }, {
                viewMvc.hideProgressBar()
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