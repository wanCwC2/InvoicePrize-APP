package com.example.invoiceprize.prize

import android.R.attr
import android.R.attr.name
import com.example.invoiceprize.Prize

class FivePrize : Prize() {
    init {
        name = "五獎"
        bonus = 1000
    }
}