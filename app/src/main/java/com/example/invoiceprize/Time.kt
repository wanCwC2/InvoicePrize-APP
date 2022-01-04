package com.example.invoiceprize

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar

class Time {
    var timeNowYear: String = "0"
    var timeNowMonth: String = "0"
    var timeNow: String = "0"
    var timeNowMonth1: String = "0"
    var timeBefYear: String = "0"
    var timeBefMonth: String = "0"
    var timeBef: String = "0"
    var timeBefMonth1: String = "0"
    fun run(){
        //前一期
        val cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.MONTH, -2)
        cal.add(Calendar.DATE, -25)
        timeNowMonth = SimpleDateFormat("MM").format(cal.getTime())
        if (timeNowMonth.toInt()%2 == 0) {
            cal.add(Calendar.MONTH, -1)
        }
        timeNowYear = SimpleDateFormat("yyyy").format(cal.getTime())
        timeNowYear = (timeNowYear.toInt()-1911).toString()
        timeNowMonth = SimpleDateFormat("MM").format(cal.getTime())
        timeNow = timeNowYear+timeNowMonth
        timeNowMonth1 = (timeNowMonth.toInt()+1).toString()

        //前兩期
        cal.add(Calendar.MONTH, -2)
        timeBefYear = SimpleDateFormat("yyyy").format(cal.getTime())
        timeBefYear = (timeBefYear.toInt()-1911).toString()
        timeBefMonth = SimpleDateFormat("MM").format(cal.getTime())
        timeBef = timeBefYear+timeBefMonth
        timeBefMonth1 = (timeBefMonth.toInt()+1).toString()
    }
}