package com.example.invoiceprize.prize

import com.example.invoiceprize.Prize

class GrandPrize : Prize() {
    init {
        name = "特獎"
        bonus = 2000000
    }
}