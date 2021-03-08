package com.dutra.patricio.desafiophi.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dutra.patricio.desafiophi.R
import com.dutra.patricio.desafiophi.data.repository.webApi
import com.dutra.patricio.desafiophi.model.ExtractModel
import com.dutra.patricio.desafiophi.util.Alerta
import com.dutra.patricio.desafiophi.util.Constants
import com.dutra.patricio.desafiophi.util.MoneyUtil
import com.dutra.patricio.desafiophi.util.PreferencesUtil
import com.dutra.patricio.desafiophi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private var balanceIsHidden = false
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observes()
        listScrollEnd()
        statusBalance()
        balanceOnClick()

        viewModel.getMyBalance()
        viewModel.getMoreExtract()
    }

    fun observes(){

        viewModel.balance.observe(this, Observer {
            txtBalance.text = MoneyUtil.moneyFormat(it)
        })

        viewModel.erro.observe(this, Observer {
            Alerta.aviso(it,this)
        })

        viewModel.listExtractLiveData.observe(this, Observer {
            if(it.size <= viewModel.limit) loadList(it) else updateList()
        })

        viewModel.loadinng.observe(this, Observer {
            viewLoading.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

    fun loadList(list: ArrayList<ExtractModel>){

        val linearLayoutManager = LinearLayoutManager ( this )
        recyclerView.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(list, this)
        recyclerView.adapter = adapter
    }

    fun updateList(){
        adapter.notifyItemRangeInserted(viewModel.limit * viewModel.offset, viewModel.listExtrac.size)
    }

    fun listScrollEnd(){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getMoreExtract()
                }
            }
        })
    }

    fun balanceOnClick(){

        linearBalance.setOnClickListener {
            balanceIsHidden = !balanceIsHidden
            changeStatusBalance()
        }
    }

    fun statusBalance(){
        balanceIsHidden = PreferencesUtil.getBoolean(this,Constants.BALANCESHAREDNAME, true)
        changeStatusBalance()
    }

    fun changeStatusBalance(){
        imgIcon.setImageDrawable(if(balanceIsHidden) getDrawable(R.drawable.round_visibility_24)else getDrawable(R.drawable.round_visibility_off_24))
        visibleBalance.visibility = if(balanceIsHidden) View.GONE else View.VISIBLE
        hiddenBalance.visibility = if(balanceIsHidden) View.VISIBLE else View.GONE
        PreferencesUtil.putBoolean(this,Constants.BALANCESHAREDNAME, balanceIsHidden)
    }

}