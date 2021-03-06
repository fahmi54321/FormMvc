package com.android.testcakratech.views.form

import android.os.Bundle
import com.android.testcakratech.common.activity.BaseActivity
import com.android.testcakratech.common.dialog.DialogNavigator
import com.android.testcakratech.common.navigator.ScreenNavigator
import kotlinx.coroutines.*

class FormActivity : BaseActivity(), FormMvcView.Listener {

    private lateinit var dialogNavigator: DialogNavigator
    private lateinit var screenNavigator: ScreenNavigator
    private lateinit var viewMvcView: FormMvcView
    private lateinit var formUseCase: FormUseCase
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewMvcView = compositionRoot.viewMvcFactory.newFormActivity()
        setContentView(viewMvcView.binding.root)

        dialogNavigator = compositionRoot.dialogNavigator
        screenNavigator = compositionRoot.screenNavigator
        formUseCase = compositionRoot.formUserCase

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
            coroutineScope.launch {
                try {
                    val result = formUseCase.registerForm(nama, email, alamat)
                    when (result) {
                        is FormUseCase.Result.Success -> {
                            dialogNavigator.dialogBerhasilMenyimpan(
                                "Pesan",
                                "Berhasil menyimpan",
                                screenNavigator
                            )
                            viewMvcView.hideProgressBar()
                            viewMvcView.enableButton()
                        }
                        is FormUseCase.Result.Failure -> {
                            viewMvcView.hideProgressBar()
                            viewMvcView.enableButton()
                        }
                    }
                } finally {
                    viewMvcView.hideProgressBar()
                    viewMvcView.enableButton()
                }
            }
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