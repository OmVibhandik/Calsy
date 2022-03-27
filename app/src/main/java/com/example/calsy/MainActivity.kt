package com.example.calsy

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
//import kotlinx.android.synthetic.main.activity_main.*

import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var TVDate:TextView?=null
    private var TVMin:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TVMin = findViewById(R.id.TVMin)
        TVDate = findViewById(R.id.TVDate)
        val btndatepicker: Button = findViewById(R.id.btndatepicker)

        btndatepicker.setOnClickListener { view->
            clickDatepicker(view)
            Toast.makeText(this,"Button Works",Toast.LENGTH_LONG).show() }

    }

    private fun clickDatepicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                _, year, month, dayofmonth ->
            Toast.makeText(this, "Date Selected",Toast.LENGTH_LONG).show()
            val selecteddate=("$dayofmonth/${month+1}/$year")

            TVDate?.text=selecteddate

            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate= sdf.parse(selecteddate)

            theDate?.let {

                val SelectedDateInMin = theDate.time/60000

                val Currentdate = sdf.parse(sdf.format(System.currentTimeMillis()))

                Currentdate?.let {

                    val CurrentDateInMin = Currentdate.time/60000

                    val difference = CurrentDateInMin - SelectedDateInMin

                    TVMin?.text=difference.toString()
                }
            }
        }
            ,year
            ,month
            ,day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis()-86400000
        dpd.show()

    }
}