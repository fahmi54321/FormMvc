package com.android.testcakratech.view.screens.form

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.android.testcakratech.databinding.ActivityFormBinding
import com.android.testcakratech.view.screens.main.MainMvcView

class FormMvcView(
    private val layoutInflater: LayoutInflater
) {

    interface Listener {
        fun onSaveForm(nama: String, email: String, alamat: String)
        fun onBackNavigation()
    }

    val binding = ActivityFormBinding.inflate(layoutInflater)
    private val context: Context get() = binding.root.context
    private val listeners = HashSet<Listener>()

    init {
        binding.btnSimpan.setOnClickListener {
            var nama = binding.edtNama.text.toString()
            var email = binding.edtEmail.text.toString()
            var alamat = binding.edtAlamat.text.toString()
            for (listener in listeners) {
                listener.onSaveForm(nama, email, alamat)
            }
        }

        binding.fabBack.setOnClickListener {
            for (listener in listeners) {
                listener.onBackNavigation()
            }
        }
    }

    fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    fun unRegisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    fun enableButton() {
        binding.btnSimpan.isEnabled = true
    }

    fun disableButton() {
        binding.btnSimpan.isEnabled = false
    }

}