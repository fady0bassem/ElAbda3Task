package com.fadybassem.elabda3task.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fadybassem.elabda3task.data.remote.pojo.DataModel


abstract class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
    var currentPosition = 0
        private set

    protected abstract fun clear()

    open fun onBind(position: Int) {
        currentPosition = position
        clear()
    }

    abstract fun bind(data: DataModel)

}