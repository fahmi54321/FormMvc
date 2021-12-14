package com.android.testcakratech.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.testcakratech.databinding.ItemDataBinding
import com.android.testcakratech.room.Form

class DataAdapter() : RecyclerView.Adapter<DataAdapter.CartPrelovedViewHolder>() {

    private val data = ArrayList<Form>()

    class CartPrelovedViewHolder(val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Form?) {
            with(binding) {
                binding.txtNama.text = item?.nama
                binding.txtAlamat.text = item?.alamat
                binding.txtEmail.text = item?.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartPrelovedViewHolder {
        val view = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartPrelovedViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartPrelovedViewHolder, position: Int) {
        var item = data?.get(position)
        holder.bind(item)
    }

    fun setList(item: List<Form>?){
        data.clear()
        data.addAll(item!!)
    }

    override fun getItemCount(): Int = data?.size ?: 0
}