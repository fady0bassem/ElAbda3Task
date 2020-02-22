package com.fadybassem.elabda3task.ui.activities.main

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fadybassem.elabda3task.R
import com.fadybassem.elabda3task.data.PreferencesHelper
import com.fadybassem.elabda3task.data.room.Table
import com.fadybassem.elabda3task.databinding.ActivityMainBinding
import com.fadybassem.elabda3task.ui.activities.base.BaseActivity
import com.fadybassem.elabda3task.ui.adapters.RecyclerAdapter
import com.fadybassem.elabda3task.ui.dialouges.CustomAlertDialog
import com.fadybassem.elabda3task.ui.dialouges.CustomProgressDialog
import com.fadybassem.elabda3task.ui.interfaces.DialogClickInterface
import com.fadybassem.elabda3task.utils.ErrorCodes
import com.fadybassem.elabda3task.utils.PaginationListener
import com.fadybassem.elabda3task.utils.PaginationListener.Companion.PAGE_START
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : BaseActivity(), DialogClickInterface, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    private var dataList: ArrayList<Table> = ArrayList()

    private lateinit var adapter: RecyclerAdapter
    private lateinit var dialog: Dialog

    private val layoutManager = LinearLayoutManager(this)

    private var currentPage: Int = PreferencesHelper.getPage()!!
    private var isLastPage = false
    private var isLoading = false
    private var loadMore = false
    private var itemCount = 0

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onViewReady(savedInstanceState, intent)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        binding.mainModelHome = viewModel

        binding.swiperefreshlayout.setOnRefreshListener(this)

        setRecyclerView(dataList)   //send empty list initially
        dialog = CustomProgressDialog.loadingIndicatorView(this, true)
        dataResult()
        getData()
    }

    override fun onClickPositiveButton(pDialog: DialogInterface?, pDialogIntefier: Int) {
        when (pDialogIntefier) {
            0 ->
                CustomAlertDialog.getInstance()!!.onClickNegativeButton(
                    pDialog,
                    0
                )
        }
    }

    override fun onClickNegativeButton(pDialog: DialogInterface?, pDialogIntefier: Int) {
        when (pDialogIntefier) {
            0 ->
                CustomAlertDialog.getInstance()!!.onClickNegativeButton(
                    pDialog,
                    0
                )
        }
    }

    override fun onRefresh() {
        viewModel.reload()
    }

    fun setRecyclerView(dataList: ArrayList<Table>) {
        adapter = RecyclerAdapter()
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.root.recyclerview.layoutManager = layoutManager
        adapter.setList(dataList)
        binding.root.recyclerview.adapter = adapter
        binding.root.recyclerview.addOnScrollListener(object : PaginationListener(layoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage++
                PreferencesHelper.setPage(currentPage)
                getData()
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })

    }

    private fun getData() {
        viewModel.getDataList(currentPage)
    }

    private fun dataResult() {
        viewModel.loadMoreMutableData.observe(this, Observer<Boolean> {
            loadMore = it
        })

        viewModel.errorMutableData.observe(this, Observer<String> { error ->
            when (error) {
                ErrorCodes.ERR_RESPONSE_ERROR, ErrorCodes.ERR_NETWORK_FAILURE, ErrorCodes.ERR_CONVERSION_ISSUE -> {
                    CustomAlertDialog.getInstance()?.showErrDialog(this, error, 0, false)
                }
                else ->
                    CustomAlertDialog.getInstance()!!.showInfoDialog(
                        getString(R.string.error),
                        error,
                        getString(R.string.close),
                        this,
                        0,
                        false
                    )

            }
            dialog.dismiss()
            binding.nodataTextview.visibility = View.VISIBLE
            binding.swiperefreshlayout.isRefreshing = false
        })

        viewModel.mutableDataList.observe(this, Observer<List<Table>> {
            if (it != null) {
                binding.nodataTextview.visibility = View.GONE
                //if (adapter.itemCount != 0) {
                    if (currentPage != PAGE_START)
                        adapter.removeLoading()
                    adapter.addItems(it)
                    if (loadMore) {
                        adapter.addLoading()
                    } else {
                        isLastPage = true
                   // }
                }

                isLoading = false
            }
            dialog.dismiss()
            binding.swiperefreshlayout.isRefreshing = false
        })

        viewModel.reloadMutableData.observe(this, Observer<Boolean> {
            reload()
        })
    }

    private fun reload() {
        itemCount = 0
        isLastPage = false
        adapter.clear()
        getData()
    }
}