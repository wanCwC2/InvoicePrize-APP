package com.example.invoiceprize.prize

import android.R.attr
import android.R.attr.name
import com.example.invoiceprize.Prize

class NotPrize : Prize() {
    init {
        name = "沒中獎"
        bonus = 0
    }
}