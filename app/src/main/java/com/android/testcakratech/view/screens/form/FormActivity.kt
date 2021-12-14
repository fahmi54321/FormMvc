package com.android.testcakratech.view.screens.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.android.testcakratech.db.Form
import com.android.testcakratech.db.FormDao
import com.android.testcakratech.db.FormDatabase
import com.android.testcakratech.view.common.dialog.DialogNavigator
import com.android.testcakratech.view.common.navigator.ScreenNavigator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class FormActivity : AppCompatActivity(), FormMvcView.Listener {

    private lateinit var dao: FormDao

    private lateinit var dialogNavigator: DialogNavigator
    private lateinit var screenNavigator: ScreenNavigator
    private lateinit var viewMvcView: FormMvcView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvcView = FormMvcView(LayoutInflater.from(this))
        setContentView(viewMvcView.binding.root)

        dialogNavigator = DialogNavigator(this)
        screenNavigator = ScreenNavigator(this)

        dao = FormDatabase.getInstance(applicationContext).formDao()

    }

    override fun onSaveForm(nama: String, email: String, alamat: String) {
        simpanForm(nama, email, alamat)
    }

    override fun onBackNavigation() {
        screenNavigator.onBackNavigation()
    }

    fun simpanForm(nama: String, email: String, alamat: String) {
        viewMvcView.disableButton()
        viewMvcView.showProgressBar()
        if (nama.isNullOrEmpty()) {
            viewMvcView.hideProgressBar()
            viewMvcView.enableButton()
            dialogNavigator.showAlertDialog("Pesan", "Nama tidak boleh kosong")
        } else if (email.isNullOrEmpty()) {
            viewMvcView.hideProgressBar()
            viewMvcView.enableButton()
            dialogNavigator.showAlertDialog("Pesan", "Email tidak boleh kosong")
        } else if (alamat.isNullOrEmpty()) {
            viewMvcView.hideProgressBar()
            viewMvcView.enableButton()
            dialogNavigator.showAlertDialog("Pesan", "Alamat tidak boleh kosong")
        } else {
            dao.insertForm(Form(0, nama, email, alamat))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    dialogNavigator.dialogBerhasilMenyimpan(
                        "Pesan",
                        "Berhasil menyimpan",
                        screenNavigator
                    )
                    viewMvcView.hideProgressBar()
                    viewMvcView.enableButton()
                }, {
                    viewMvcView.hideProgressBar()
                    viewMvcView.enableButton()
                })
        }
    }

    override fun onStart() {
        super.onStart()
        viewMvcView.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        viewMvcView.unRegisterListener(this)
    }
}