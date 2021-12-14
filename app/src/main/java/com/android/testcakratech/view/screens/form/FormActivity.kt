package com.android.testcakratech.view.screens.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.testcakratech.databinding.ActivityFormBinding
import com.android.testcakratech.db.Form
import com.android.testcakratech.db.FormDao
import com.android.testcakratech.db.FormDatabase
import com.android.testcakratech.view.common.dialog.DialogNavigator
import com.android.testcakratech.view.common.navigator.ScreenNavigator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class FormActivity : AppCompatActivity() {

    private lateinit var dao: FormDao
    private lateinit var binding: ActivityFormBinding
    private lateinit var dialogNavigator: DialogNavigator
    private lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialogNavigator = DialogNavigator(this)
        screenNavigator = ScreenNavigator(this)

        dao = FormDatabase.getInstance(applicationContext).formDao()
        binding.btnSimpan.setOnClickListener {

            var nama = binding.edtNama.text.toString()
            var email = binding.edtEmail.text.toString()
            var alamat = binding.edtAlamat.text.toString()

            simpanForm(nama, email, alamat)
        }

        binding.fabBack.setOnClickListener {
            screenNavigator.onBackNavigation()
        }

    }

    fun simpanForm(nama: String, email: String, alamat: String) {

        showProgressBar()
        disableButton()
        if (nama.isNullOrEmpty()) {
            hideProgressBar()
            enableButton()
            dialogNavigator.showAlertDialog(
                "Pesan",
                "Nama tidak boleh kosong"
            )
        } else if (email.isNullOrEmpty()) {
            hideProgressBar()
            enableButton()
            dialogNavigator.showAlertDialog(
                "Pesan",
                "Email tidak boleh kosong"
            )
        } else if (alamat.isNullOrEmpty()) {
            hideProgressBar()
            enableButton()
            dialogNavigator.showAlertDialog(
                "Pesan",
                "Alamat tidak boleh kosong"
            )
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
                    hideProgressBar()
                    enableButton()
                }, {
                    hideProgressBar()
                    enableButton()
                })
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun enableButton() {
        binding.btnSimpan.isEnabled = true
    }

    private fun disableButton() {
        binding.btnSimpan.isEnabled = false
    }
}