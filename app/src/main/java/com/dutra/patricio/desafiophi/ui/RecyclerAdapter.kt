package com.dutra.patricio.desafiophi.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.dutra.patricio.desafiophi.R
import com.dutra.patricio.desafiophi.model.ExtractModel
import com.dutra.patricio.desafiophi.util.DateUtil
import com.dutra.patricio.desafiophi.util.MoneyUtil


class RecyclerAdapter (private var list: ArrayList<ExtractModel>, var act: Activity): RecyclerView.Adapter<RecyclerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_statement,parent,false) as View
        return Holder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val extract = list[position]

        holder.txtTransferSent.text = extract.description
        holder.txtName.text = extract.to
        holder.txtDate.text = DateUtil.dateFormat(extract.createdAt)

        val isPix = extract.tType.equals(act.getString(R.string.PIXOUT), true)
                || extract.tType.equals(act.getString(R.string.PIXIN), true)

        if(isPix){
            holder.flPix.visibility = View.VISIBLE
            holder.viewBackground.setBackgroundColor(act.getResources().getColor(R.color.custom_white))
        }else{
            holder.flPix.visibility = View.INVISIBLE
            holder.viewBackground.setBackgroundColor(act.getResources().getColor(android.R.color.transparent))
        }


        if(extract.tType.contains("out",true)){
            val moneyAmount: String = MoneyUtil.moneyFormat(extract.amount)
            holder.txtValue.text = "- $moneyAmount"
        }else{
            val moneyAmount: String = MoneyUtil.moneyFormat(extract.amount)
            holder.txtValue.text = moneyAmount
        }


        holder.layoutStatement.setOnClickListener {
            val intent = Intent(act, DetailActivity::class.java)
            intent.putExtra("id", extract.id)
            act.startActivity(intent)
        }

    }

    class Holder(v: View) : RecyclerView.ViewHolder(v) {

        val txtTransferSent = v.findViewById<TextView>(R.id.txtTransferSent)
        val txtName = v.findViewById<TextView>(R.id.txtName)
        val txtValue = v.findViewById<TextView>(R.id.txtValue)
        val txtDate = v.findViewById<TextView>(R.id.txtDate)
        val flPix = v.findViewById<FrameLayout>(R.id.flPix)
        val viewBackground = v.findViewById<ConstraintLayout>(R.id.viewBackground)
        val layoutStatement = v.findViewById<ConstraintLayout>(R.id.layoutStatement)
    }

}



