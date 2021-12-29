package com.example.invoiceprize

import java.util.ArrayList




open class Prize {
    var data: MutableList<String> = ArrayList()
    var name: String? = null
    var bonus = 0
    var list: Array<String> = emptyArray()
    var total = 0
    var enter: MutableList<String> = ArrayList()
    var winName: MutableList<String> = ArrayList()
    var winBonus: MutableList<Int> = ArrayList()

//    fun Prize() {
//        super()
//    }

    fun content(year: String, month: String) {
        val cd = CatchData()
        data = cd.get(year, month)
        val nameData = arrayOf("特別獎", "特獎", "頭獎A", "頭獎B", "頭獎C", "增開六獎", "增開六獎2")
        for (i in data!!.indices) {
            System.out.printf("%-7s %8s", nameData[i], data!![i])
            println()
        }
    }

    fun compare(index: Int) {
        val cpd = CompareData()
        list = cpd.compare(index, data.get(index).toInt())
        if (list!![0].toInt() != 9) {
            total += list!![3].toInt()
            enter.add(list!![1])
            winName.add(list!![2])
            winBonus.add(list!![3].toInt())
        }
    }

    fun print() {
        println("-------以下是你的中獎發票-------")
        if (enter.size == 0) {
            println("完全沒中獎喔！")
        } else {
            for (i in enter.indices) {
                println(
                    "你的發票號碼" + enter[i] +
                            " 中" + winName[i] +
                            " 得到獎金" + winBonus[i] + "元"
                )
            }
        }
        if (total != 0) {
            println("這次兌換發票的中獎總金額為" + total + "元")
        }
    }
}