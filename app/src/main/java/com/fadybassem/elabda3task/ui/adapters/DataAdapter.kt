package com.fadybassem.elabda3task.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fadybassem.elabda3task.data.remote.pojo.DataModel
import com.fadybassem.elabda3task.databinding.ItemDataBinding
import java.util.*

class DataAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<DataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val applicationBinding = ItemDataBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(applicationBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = list[position]
        (holder as ViewHolder).bind(data)
    }

    fun setList(list: ArrayList<DataModel>) {
        this.list.addAll(list)
        //notifyItemRangeInserted(0, categoryModel.size)
        notifyDataSetChanged()
    }

    fun addMore(arrayList: ArrayList<DataModel>) {
        this.list.addAll(arrayList)
    }

    fun add(arrayList: ArrayList<DataModel>){
        val initPosition = list.size
        list.addAll(arrayList)
        notifyItemRangeInserted(initPosition, list.size)

    }

    inner class ViewHolder(private var itemDataBinding: ItemDataBinding) :
        RecyclerView.ViewHolder(itemDataBinding.root) {

        fun bind(data: DataModel) {
            itemDataBinding.dataMdodel = data
        }
    }
}