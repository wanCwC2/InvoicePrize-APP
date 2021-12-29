package com.example.invoiceprize

import java.util.Scanner




class Website {
    var year: String? = null
    var month: String? = null

    fun Website() {
        super()
    }

    fun verified(): Boolean {
        print("輸入你想發票兌獎號碼的年月份: ")
        val scan = Scanner(System.`in`)
        val site = scan.nextInt()
        val cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.MONTH, -2)
        cal.add(Calendar.DATE, -25)
        val timeNowYear: String = SimpleDateFormat("yyyy").format(cal.getTime())
        val timeNowMonth: String = SimpleDateFormat("MM").format(cal.getTime())
        val yearNow = timeNowYear.toInt() - 1911
        val monthNow = timeNowMonth.toInt()
        return if (site / 100 > yearNow || site / 100 == yearNow && site % 100 > monthNow
            || site % 100 > 12 || site % 100 < 0 || (site % 100 + 1) % 2 != 0 || site / 100 < 101 || site / 100 == 101 && site % 100 < 5
        ) {
            false
        } else {
            year = Integer.toString(site / 100)
            month = if (site % 100 < 10) {
                "0" + Integer.toString(site % 100)
            } else {
                Integer.toString(site % 100)
            }
            true
        }
    }
}