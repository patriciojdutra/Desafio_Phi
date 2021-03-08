package com.dutra.patricio.desafiophi.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Images
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dutra.patricio.desafiophi.R
import com.dutra.patricio.desafiophi.model.ExtractDetailModel
import com.dutra.patricio.desafiophi.model.ExtractModel
import com.dutra.patricio.desafiophi.util.Alerta
import com.dutra.patricio.desafiophi.util.DateUtil
import com.dutra.patricio.desafiophi.util.MoneyUtil
import com.dutra.patricio.desafiophi.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class DetailActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getExtractDetail()
        observes()
        viewBackOnclick()
        btnShareOnclick()


    }

    fun observes(){

        viewModel.extractDetail.observe(this, Observer {
            setDataExtractDetail(it)
        })

        viewModel.erro.observe(this, Observer {
            Alerta.aviso(it,this)
        })

        viewModel.loadinng.observe(this, Observer {
            viewLoadinDetail.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

    fun setDataExtractDetail(extractDetail: ExtractDetailModel){

        btnShare.isEnabled = true
        txtDriveType.text = extractDetail.description
        txtTo.text = extractDetail.to
        txtBankInstitution.text = extractDetail.bankName
        txtDateAndTime.text = DateUtil.dateAndTimeFormat(extractDetail.createdAt)
        txtAuthentication.text = extractDetail.authentication
        txtValue.text = MoneyUtil.moneyFormat(extractDetail.amount)

    }

    fun getExtractDetail(){

        try{
            val id = intent.getStringExtra("id")!!
            viewModel.getExtractDetail(id)
        }catch (e: Exception){
            Toast.makeText(this,getString(R.string.error_open_extract),Toast.LENGTH_LONG).show()
            finish()
        }
    }

    fun viewBackOnclick(){
        viewBack.setOnClickListener { finish() }
    }

    fun btnShareOnclick(){
        btnShare.setOnClickListener { screenShot(scrollView) }
    }

    fun screenShot(view: View) {

        val bitmap = Bitmap.createBitmap(
            view.getWidth(),
            view.getHeight(), Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        view.draw(canvas)

        val bitmapPath = Images.Media.insertImage(contentResolver, bitmap, "title", null)
        val bitmapUri = Uri.parse(bitmapPath)

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, bitmapUri)
            type = "image/png"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }
}