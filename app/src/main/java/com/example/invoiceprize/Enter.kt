package com.example.invoiceprize

import java.util.Scanner




class Enter {
    var string: String = "0"
    var integer = 0

    fun vertified(number: Int): Boolean {
        val scan = Scanner(System.`in`)
        val temp = scan.next()
        return if (temp.toInt() == 0 && number == 3) {
            string = temp
            integer = temp.toInt()
            true
        } else if (temp.length != number) {
            false
        } else {
            string = temp
            integer = temp.toInt()
            true
        }
    }
}