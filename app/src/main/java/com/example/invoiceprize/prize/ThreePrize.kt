package com.example.invoiceprize.prize

import android.R.attr
import android.R.attr.name
import com.example.invoiceprize.Prize

class ThreePrize : Prize() {
    init {
        name = "三獎"
        bonus = 10000
    }
}