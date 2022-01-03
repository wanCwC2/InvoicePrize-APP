package com.example.invoiceprize.prize

import com.example.invoiceprize.Prize

class NotPrize : Prize() {
    init {
        name = "沒中獎"
        bonus = 0
    }
}