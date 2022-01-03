package com.example.invoiceprize.prize

import com.example.invoiceprize.Prize

class SpecialPrize : Prize() {
    init {
        name = "特別獎"
        bonus = 10000000
    }
}