package com.example.invoiceprize.prize

import android.R.attr
import android.R.attr.name
import com.example.invoiceprize.Prize

class FirstPrize : Prize() {
    init {
        name = "頭獎"
        bonus = 200000
    }
}