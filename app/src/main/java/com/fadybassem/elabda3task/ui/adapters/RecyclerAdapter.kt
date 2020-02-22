package com.fadybassem.elabda3task.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fadybassem.elabda3task.data.remote.pojo.DataModel
import com.fadybassem.elabda3task.databinding.ItemDataBinding
import com.fadybassem.elabda3task.databinding.ItemLoadingBinding
import kotlinx.android.synthetic.main.item_loading.view.*
import java.util.*

class RecyclerAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_NORMAL = 1
    private var isLoaderVisible = false

    private val list = ArrayList<DataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        var view: BaseViewHolder? = null
        when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val applicationBinding = ItemDataBinding.inflate(layoutInflater, parent, false)
                view = ViewHolder(applicationBinding)
            }
            VIEW_TYPE_LOADING -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val applicationBinding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                view = ProgressHolder(applicationBinding)
            }
        }
        return view!!
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)

        val data = list[position]
        holder.bind(data)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == list.size - 1) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: ArrayList<DataModel>) {
        this.list.addAll(list)
        //notifyItemRangeInserted(0, categoryModel.size)
        notifyDataSetChanged()
    }

    fun addItems(items: List<DataModel>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun addLoading() {
        isLoaderVisible = true
        //list.add(PostItem())
        notifyItemInserted(list.size - 1)
    }

    fun removeLoading() {
        isLoaderVisible = false
        val position: Int = list.size - 1
        val item: DataModel? = getItem(position)
        if (item != null) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): DataModel? {
        return list.get(position)
    }

    inner class ViewHolder(private var itemDataBinding: ItemDataBinding) :
        BaseViewHolder(itemDataBinding.root) {
        override fun clear() {}
        override fun bind(data: DataModel) {
            itemDataBinding.dataMdodel = data
        }
    }

    inner class ProgressHolder(private var itemLoadingBinding: ItemLoadingBinding) :
        BaseViewHolder(itemLoadingBinding.root) {
        override fun clear() {}
        override fun bind(data: DataModel) {
            itemLoadingBinding.root.loading.start()
        }
    }
}