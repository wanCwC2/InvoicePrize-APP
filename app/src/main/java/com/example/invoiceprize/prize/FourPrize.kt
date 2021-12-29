package com.example.invoiceprize.prize

import android.R.attr
import android.R.attr.name
import com.example.invoiceprize.Prize

class FourPrize : Prize() {
    init {
        name = "四獎"
        bonus = 4000
    }
}